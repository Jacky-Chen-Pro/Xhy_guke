package cn.incongress.xhy_guke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.adapter.NoScrollGridViewLocalPathAdapter;
import cn.incongress.xhy_guke.api.XhyGo;
import cn.incongress.xhy_guke.base.BaseFragment;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.base.XhyApplication;
import cn.incongress.xhy_guke.utils.KeyBoardUtils;
import cn.incongress.xhy_guke.utils.LogUtils;
import cn.incongress.xhy_guke.utils.PicUtils;
import cn.incongress.xhy_guke.utils.StringUtils;
import cn.incongress.xhy_guke.utils.ToastUtils;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Jacky on 2016/4/12.
 * 发布V说界面
 */
public class MakePostVVFragment extends BaseFragment implements View.OnClickListener, GalleryFinal.OnHanlderResultCallback {
    private AppCompatCheckBox mCheckbox;
    private ImageView mIvCloseKeyboard, mIvAlbum, mIvTakePhoto;
    private RecyclerView mRcvUploadPhotos;
    private NoScrollGridViewLocalPathAdapter mGridAdapter;
    private EditText mEtPost;

    private String mIsNickName = "0";//是否昵称发布 0非昵称发布，1昵称发布
    private String mDataId = "-1";//发帖一开始传-1,先发图片，后发文字
    private ArrayList<String> mPhotosPath = new ArrayList<>();

    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;

    public static MakePostVVFragment getInstance() {
        MakePostVVFragment fragment = new MakePostVVFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_post_vv, null);
        initView(view);
        return view;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mCheckbox = (AppCompatCheckBox) view.findViewById(R.id.checkBox);
        mIvAlbum = (ImageView) view.findViewById(R.id.iv_album);
        mIvTakePhoto = (ImageView) view.findViewById(R.id.iv_take_photo);
        mIvCloseKeyboard = (ImageView) view.findViewById(R.id.iv_close_keyboard);
        mRcvUploadPhotos = (RecyclerView) view.findViewById(R.id.rcv_photos);
        mEtPost = (EditText) view.findViewById(R.id.et_post);

        mRcvUploadPhotos.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mGridAdapter = new NoScrollGridViewLocalPathAdapter(getActivity(), mPhotosPath);
        mRcvUploadPhotos.setAdapter(mGridAdapter);
        mRcvUploadPhotos.setItemAnimator(new DefaultItemAnimator());

        mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    mIsNickName = "1";
                }else {
                    mIsNickName = "0";
                }
            }
        });

        mGridAdapter.setDeleteClickListener(new NoScrollGridViewLocalPathAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View view, String path) {
                mGridAdapter.removeData(Integer.parseInt((String) view.getTag()));
            }
        });

        mIvAlbum.setOnClickListener(this);
        mIvTakePhoto.setOnClickListener(this);
        mIvCloseKeyboard.setOnClickListener(this);

        initData();
        dismissProgressDialog();
    }

    @Override
    public void initData() {
        super.initData();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_album:
                if (mPhotosPath.size() < 9) {
                    FunctionConfig config = new FunctionConfig.Builder().setMutiSelectMaxSize(9 - mPhotosPath.size()).setFilter(mPhotosPath).build();
                    GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, config, MakePostVVFragment.this);
                } else {
                    ToastUtils.showShorToast(getString(R.string.post_photo_more_than_max), getActivity());
                }
                break;
            case R.id.iv_take_photo:
                if (mPhotosPath.size() < 9) {
                    GalleryFinal.openCamera(REQUEST_CODE_CAMERA, MakePostVVFragment.this);
                } else {
                    ToastUtils.showShorToast(getString(R.string.post_photo_more_than_max), getActivity());
                }
                break;
            case R.id.iv_close_keyboard:
                KeyBoardUtils.closeKeybord(mEtPost, getActivity());
                break;
        }
    }

    @Override
    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
        if (reqeustCode == REQUEST_CODE_GALLERY) {
            for (int i = 0; i < resultList.size(); i++) {
                mPhotosPath.add(resultList.get(i).getPhotoPath());
            }
            mGridAdapter.notifyDataSetChanged();
        } else if (reqeustCode == REQUEST_CODE_CAMERA) {
            mPhotosPath.add(resultList.get(0).getPhotoPath());
            mGridAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onHanlderFailure(int requestCode, String errorMsg) {
        ToastUtils.showShorToast(getString(R.string.fail_reason, errorMsg), getActivity());
    }

    /**
     * 发帖文字
     */
    public void createPost(String content) {
        try {
            content = URLEncoder.encode(content, Constants.ENCODDING_UTF8);
            XhyGo.goCreatePost(getActivity(), XhyApplication.userId, content, mDataId, mIsNickName, new StringCallback() {

                @Override
                public void onBefore(Request request) {
                    super.onBefore(request);
                }

                @Override
                public void onError(Call call, Exception e) {
                }

                @Override
                public void onAfter() {
                    super.onAfter();
                    mDataId = "-1";
                    dismissProgressDialog();
                    ToastUtils.showShorToast(getString(R.string.post_success), getActivity());
                    getActivity().finish();
                }

                @Override
                public void onResponse(String response) {
                    LogUtils.println("createPost:" + response);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showShorToast(getString(R.string.decode_error), getActivity());
        }
    }

    public int mUploadImgPosition = 0;

    //发图片
    public void createPostImag() {
        if (mPhotosPath.size() > 0) {
            //图片进行压缩
            String filePhth = "";
            try {
                filePhth = PicUtils.saveFile(PicUtils.getSmallBitmap(mPhotosPath.get(mUploadImgPosition)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            XhyGo.goCreatePostImg(getActivity(), XhyApplication.userId, mDataId, new File(filePhth), mPhotosPath.get(mUploadImgPosition), mIsNickName, new StringCallback() {
                @Override
                public void onBefore(Request request) {
                    super.onBefore(request);
                    showProgressDialog();
                }

                @Override
                public void onAfter() {
                    super.onAfter();
                    if (mPhotosPath.size() - 1 == mUploadImgPosition) {
                        mUploadImgPosition = 0;
                        //判断是否有问题，有文字则继续发送文字
                        String content = mEtPost.getText().toString().trim();
                        if (StringUtils.isEmpty(content)) {
                            mDataId = "-1";
                            dismissProgressDialog();
                            ToastUtils.showShorToast(getString(R.string.post_success), getActivity());
                            getActivity().finish();
                        } else {
                            //发送文字
                            createPost(content);
                        }
                    } else {
                        mUploadImgPosition++;
                        createPostImag();
                    }
                }

                @Override
                public void onError(Call call, Exception e) {
                }

                @Override
                public void onResponse(String response) {
                    LogUtils.println("createPostImg:" + response);
                    try {
                        JSONObject obj = new JSONObject(response);
                        int state = obj.getInt("state");
                        if (state == 1) {
                            mDataId = obj.getString("dataId");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }else {
            //只发送文字
            String content = mEtPost.getText().toString().trim();
            if(StringUtils.isEmpty(content)) {
                ToastUtils.showShorToast(getString(R.string.post_write_something), getActivity());
            }else {
                createPost(content);
            }
        }
    }
}
