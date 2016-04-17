package cn.incongress.xhy_guke.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.adapter.NoScrollGridViewAdapter;
import cn.incongress.xhy_guke.adapter.NoScrollGridViewLocalPathAdapter;
import cn.incongress.xhy_guke.api.XhyGo;
import cn.incongress.xhy_guke.base.BaseFragment;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.base.XhyApplication;
import cn.incongress.xhy_guke.uis.FullyGridLayoutManager;
import cn.incongress.xhy_guke.uis.NoScrollGridView;
import cn.incongress.xhy_guke.utils.KeyBoardUtils;
import cn.incongress.xhy_guke.utils.LogUtils;
import cn.incongress.xhy_guke.utils.PicassoImageLoader;
import cn.incongress.xhy_guke.utils.StringUtils;
import cn.incongress.xhy_guke.utils.ToastUtils;
import okhttp3.Call;

/**
 * Created by Jacky on 2016/4/12.
 * 发布V说界面
 */
public class MakePostVVFragment extends BaseFragment implements View.OnClickListener, GalleryFinal.OnHanlderResultCallback{
    private AppCompatCheckBox mCheckbox;
    private ImageView mIvCloseKeyboard,mIvAlbum,mIvTakePhoto;
    private RecyclerView mRcvUploadPhotos;
    private NoScrollGridViewLocalPathAdapter mGridAdapter;
    private EditText mEtPost;

    private boolean mIsNickName = false;//是否昵称发布
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
        mGridAdapter = new NoScrollGridViewLocalPathAdapter(getActivity(),mPhotosPath);
        mRcvUploadPhotos.setAdapter(mGridAdapter);
        mRcvUploadPhotos.setItemAnimator(new DefaultItemAnimator());

        mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mIsNickName = isChecked;
            }
        });

        mGridAdapter.setDeleteClickListener(new NoScrollGridViewLocalPathAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View view, String path) {
                mGridAdapter.removeData(Integer.parseInt((String)view.getTag()));
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
                if(mPhotosPath.size() < 9) {
                    FunctionConfig config = new FunctionConfig.Builder().setMutiSelectMaxSize(9-mPhotosPath.size()).setFilter(mPhotosPath).build();
                    GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, config, MakePostVVFragment.this);
                }else {
                    ToastUtils.showShorToast(getString(R.string.post_photo_more_than_max), getActivity());
                }
                break;
            case R.id.iv_take_photo:
                if(mPhotosPath.size() < 9) {
                    GalleryFinal.openCamera(REQUEST_CODE_CAMERA, MakePostVVFragment.this);
                }else {
                    ToastUtils.showShorToast(getString(R.string.post_photo_more_than_max), getActivity());
                }
                break;
            case R.id.iv_close_keyboard:
                KeyBoardUtils.closeKeybord(mEtPost,getActivity());
                break;
        }
    }

    @Override
    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
        if(reqeustCode == REQUEST_CODE_GALLERY) {
            for(int i=0; i<resultList.size(); i++) {
                mPhotosPath.add(resultList.get(i).getPhotoPath());
            }
            mGridAdapter.notifyDataSetChanged();
        }else if(reqeustCode == REQUEST_CODE_CAMERA) {
            mPhotosPath.add(resultList.get(0).getPhotoPath());
            mGridAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onHanlderFailure(int requestCode, String errorMsg) {
        ToastUtils.showShorToast(getString(R.string.fail_reason, errorMsg), getActivity());
    }

    /**
     * 发帖
     */
    public void createPost() {
        String content = mEtPost.getText().toString().trim();
        if(StringUtils.isEmpty(content)){
            ToastUtils.showShorToast(getString(R.string.post_content_empty), getActivity());
        }else{
            try {
                content = URLEncoder.encode(content, Constants.ENCODDING_UTF8);

                XhyGo.goCreatePost(getActivity(), XhyApplication.userId, content, "1", new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        getActivity().finish();
                    }

                    @Override
                    public void onResponse(String response) {
                        LogUtils.println("createPost:"+response);
                    }
                });
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                ToastUtils.showShorToast(getString(R.string.decode_error), getActivity());
            }

        }
    }
}
