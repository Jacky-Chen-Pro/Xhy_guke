package cn.incongress.xhy_guke.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.api.XhyGo;
import cn.incongress.xhy_guke.base.BaseActivity;
import cn.incongress.xhy_guke.base.XhyApplication;
import cn.incongress.xhy_guke.bean.VVTalkDetailBean;
import cn.incongress.xhy_guke.fragment.VVTalkDetailAttachFragment;
import cn.incongress.xhy_guke.fragment.VVTalkDetailCommentFragment;
import cn.incongress.xhy_guke.fragment.VVTalkDetailMakePostFragment;
import cn.incongress.xhy_guke.fragment.WebViewDetailFragment;
import cn.incongress.xhy_guke.uis.popup.BasePopupWindow;
import cn.incongress.xhy_guke.uis.popup.CommentPopupWindow;
import cn.incongress.xhy_guke.uis.popup.InputMethodUtils;
import cn.incongress.xhy_guke.utils.LogUtils;
import cn.incongress.xhy_guke.utils.ToastUtils;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Jacky Chen on 2016/3/29 0029.
 * V言V语详情页
 */
public class VVTalkDetailActivity extends BaseActivity {
    private static final String EXTRA_TYPE = "extra_type";
    private static final String EXTRA_DATA_ID = "data_id";
    private static final String EXTRA_WHERE_STATE = "where_state";

    /** V言V语 详情类型 **/
    public static final int DETAIL_TYPE_NEWS = 1;//新闻
    public static final int DETAIL_TYPE_CASE = 2; //病例
    public static final int DETAIL_TYPE_POST = 3;//发帖
    public static final int DETAIL_TYPE_ATTACH = 4;//课件
    public static final int DETAIL_TYPE_VIDEO = 5;//视频

    /**  从V言V语跳转或者动态跳转 **/
    public static final int WHERE_STATE_VVTALK = 1;
    public static final int WHERE_STATE_DYNAMIC = 2;

    private int mCurrentType;//当前详情类型
    private int mDataId;      //详情ID
    private int mCurrentWhereState; //跳转来源

    private VVTalkDetailBean mDetailBean;

    private TextView mTvMakeComment;
    private CommentPopupWindow mCommentPop;
    private ImageView mIvPraise,mIvCollect,mIvShare;


    public static final void startVVTalkDetailActivity(Context context, int type, int dataId, int whereState) {
        Intent intent = new Intent();
        intent.setClass(context, VVTalkDetailActivity.class);
        intent.putExtra(EXTRA_TYPE, type);
        intent.putExtra(EXTRA_DATA_ID, dataId);
        intent.putExtra(EXTRA_WHERE_STATE, whereState);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vvtalk_detail);
        initToolbar(getString(R.string.vvtalk_detail_title), true, false, -1, null, false, -1, null);

        mTvMakeComment = getViewById(R.id.tv_make_comment);
        mIvPraise = getViewById(R.id.iv_praise);
        mIvCollect = getViewById(R.id.iv_collect);
        mIvShare = getViewById(R.id.iv_share);

        mCurrentType = getIntent().getIntExtra(EXTRA_TYPE, -1);
        mDataId = getIntent().getIntExtra(EXTRA_DATA_ID, -1);
        mCurrentWhereState = getIntent().getIntExtra(EXTRA_WHERE_STATE, -1);

        if(mCurrentType ==-1 || mDataId == -1 || mCurrentWhereState == -1) {
            ToastUtils.showShorToast(getString(R.string.error_happen_back_to_home),this);
            this.finish();
        }

        int result = XhyGo.getDataById(this, mDataId +"", XhyApplication.userId, mCurrentWhereState +"", new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                LogUtils.println(response);
                Gson gson = new Gson();
                mDetailBean = gson.fromJson(response, VVTalkDetailBean.class);

                fillContainer();
            }
        });


    }

    private void initEvents() {
        mTvMakeComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCommentPop = new CommentPopupWindow(VVTalkDetailActivity.this, XhyApplication.userId, "-1", "", mDataId + "", new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            int state = obj.getInt("state");

                            if(state == 1) {
                                ToastUtils.showShorToast(getString(R.string.comment_success),VVTalkDetailActivity.this);
                            }else {
                                ToastUtils.showShorToast(obj.getString("msg"), VVTalkDetailActivity.this);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                mCommentPop.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        InputMethodUtils.showInputMethod(VVTalkDetailActivity.this);
                    }
                });

                mCommentPop.showPopupWindow();
            }
        });

        mIvPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDetailBean.getIsLaud() == 0) {
                    XhyGo.goDataLaud(VVTalkDetailActivity.this, mDataId+"", XhyApplication.userId, new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e) {

                        }

                        @Override
                        public void onBefore(Request request) {
                            super.onBefore(request);

                            //放大动画
                            final ScaleAnimation animation =new ScaleAnimation(1.0f, 1.3f, 1.0f, 1.3f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                            animation.setDuration(500);//设置动画持续时间
                            mIvPraise.startAnimation(animation);
                        }

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject json = new JSONObject(response);
                                int state = json.getInt("state");
                                String msg = json.getString("msg");
                                int laudCount = json.getInt("laudCount");
                                if(state == 0) {
                                    ToastUtils.showShorToast(msg, VVTalkDetailActivity.this);
                                }else {
                                    mIvPraise.setImageResource(R.mipmap.vvtalk_detail_praise_done);
                                    mDetailBean.setIsLaud(1);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });

        mIvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mIvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 页面布局
     */
    private void fillContainer() {
        if(mCurrentType == DETAIL_TYPE_NEWS || mCurrentType == DETAIL_TYPE_CASE) {
            getSupportFragmentManager().beginTransaction().
                    add(R.id.fl_detail_area, WebViewDetailFragment.getInstance(mDetailBean.getHtmlUrl()))
                    .add(R.id.fl_comment_area, VVTalkDetailCommentFragment.getInstance(mDetailBean.getLaudList(), mDetailBean.getDataId() + "")).commit();
        }else if(mCurrentType == DETAIL_TYPE_POST) {
            ToastUtils.showShorToast("Post", VVTalkDetailActivity.this);
            getSupportFragmentManager().beginTransaction().
                    add(R.id.fl_detail_area, VVTalkDetailMakePostFragment.getInstance(mDetailBean.getAuthorPic(), mDetailBean.getCreateUser(), mDetailBean.getHospital(), mDetailBean.getContent(), mDetailBean.getImgs()))
                    .add(R.id.fl_comment_area, VVTalkDetailCommentFragment.getInstance(mDetailBean.getLaudList(), mDetailBean.getDataId() + "")).commit();
        }else if(mCurrentType == DETAIL_TYPE_ATTACH) {
            ToastUtils.showShorToast("Attach", VVTalkDetailActivity.this);
            getSupportFragmentManager().beginTransaction().
                    add(R.id.fl_detail_area,  VVTalkDetailAttachFragment.getInstance(mDetailBean.getAuthorPic(), mDetailBean.getCreateUser(),
                            mDetailBean.getHospital(), mDetailBean.getTitle(),mDetailBean.getTime(), mDetailBean.getReadCount()+"", mDetailBean.getTitle(),
                            mDetailBean.getPdfDataSize(),mDetailBean.getPdfDataUrl(),mDetailBean.getDataDescribe(), Integer.valueOf(mDetailBean.getDataType())))
                    .add(R.id.fl_comment_area, VVTalkDetailCommentFragment.getInstance(mDetailBean.getLaudList(), mDetailBean.getDataId() + "")).commit();
        }else if(mCurrentType == DETAIL_TYPE_VIDEO) {
            ToastUtils.showShorToast("Video", VVTalkDetailActivity.this);
        }

        initEvents();

        if(mDetailBean != null) {
            if(mDetailBean.getIsLaud() == 1) {
                mIvPraise.setImageResource(R.mipmap.vvtalk_detail_praise_done);
            }
        }
    }
}
