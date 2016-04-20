package cn.incongress.xhy_guke.base;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.utils.ToastUtils;

/**
 * Created by Jacky Chen on 2016/3/24 0024.
 */
public class BaseActivity extends AppCompatActivity {
    /** 全局唯一的toolbar **/
    protected Toolbar mOnlyToolbar;
    /** 左右两侧按钮 **/
    protected ImageView mIvLeftIcon,mIvRightIcon;

    protected TextView mTvTitle;

    protected ProgressDialog mProgressDialog;

    /** 个人信息sp文件名 **/
    private static final String SP_PERSON_INFO = "personInfo";

    protected void dismissProgressDialog() {
        if(mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void showProgressDialog() {
        if(mProgressDialog == null || !mProgressDialog.isShowing() ) {
            mProgressDialog = ProgressDialog.show(this, null, getString(R.string.loading));
            mProgressDialog.setCanceledOnTouchOutside(true);
        }
    }

    /** 友盟分享监听 **/
    protected UMShareListener mUmengShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA share_media) {
            ToastUtils.showShorToast(getString(R.string.share_success), BaseActivity.this);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            ToastUtils.showShorToast(getString(R.string.share_fail), BaseActivity.this);

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            ToastUtils.showShorToast(getString(R.string.share_cancel), BaseActivity.this);

        }
    };
    /** 分享的渠道 **/
    protected final SHARE_MEDIA[] mShareList = new SHARE_MEDIA[]
    {
            SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE
    };

    /**
     *
     * @param title 标题
     * @param isBackEnable 是否有返回键
     * @param isHasLeftIcon 是否有左侧图片
     * @param leftIconId 左侧图片ID
     * @param isHasRightIcon 是否有右侧图片
     * @param rightIconId  右侧图片ID
     */
    protected void initToolbar(String title,boolean isBackEnable, boolean isHasLeftIcon, int leftIconId, View.OnClickListener leftIconListener, boolean isHasRightIcon, int rightIconId, View.OnClickListener rightIconListener) {
        mOnlyToolbar = getViewById(cn.incongress.xhy_guke.R.id.toolbar);
        mIvLeftIcon = getViewById(cn.incongress.xhy_guke.R.id.iv_left_icon);
        mIvRightIcon = getViewById(cn.incongress.xhy_guke.R.id.iv_right_icon);
        mTvTitle = getViewById(cn.incongress.xhy_guke.R.id.tv_title);

        setSupportActionBar(mOnlyToolbar);
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setTitle("");
            mTvTitle.setText(title);
            //返回按钮及左侧按钮
            if(isBackEnable) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }else {
                if(isHasLeftIcon) {
                    mIvLeftIcon.setVisibility(View.VISIBLE);
                    mIvLeftIcon.setImageResource(leftIconId);
                    mIvLeftIcon.setOnClickListener(leftIconListener);
                }else {
                    mIvLeftIcon.setVisibility(View.GONE);
                }
            }

            //右侧按钮
            if(isHasRightIcon) {
                mIvRightIcon.setVisibility(View.VISIBLE);
                mIvRightIcon.setImageResource(rightIconId);
                mIvRightIcon.setOnClickListener(rightIconListener);
            }else {
                mIvRightIcon.setVisibility(View.GONE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T getViewById(int id) {
        return (T) findViewById(id);
    }

    /**
     * 分享文章
     * @param shareTitle 标题
     * @param shareContent 内容
     * @param shareUrl 跳转地址
     */
    protected void sharePost(String shareTitle, String shareContent, String shareUrl) {
        UMImage image = new UMImage(BaseActivity.this, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        new ShareAction(BaseActivity.this).setDisplayList(mShareList)
                .withText(shareContent )
                .withTitle(shareTitle)
                .withTargetUrl(shareUrl)
                .withMedia(image )
                .setListenerList(mUmengShareListener)
                .open();
    }


    protected void setSPStringValue(String key, String value) {
        SharedPreferences sp = getSharedPreferences(SP_PERSON_INFO,MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key,value);
        edit.commit();
    }

    protected void setSPIntValue(String key, int value) {
        SharedPreferences sp = getSharedPreferences(SP_PERSON_INFO,MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    protected String getSPStringValue(String key) {
        SharedPreferences sp = getSharedPreferences(SP_PERSON_INFO,MODE_PRIVATE);
        return sp.getString(key,"");
    }

    protected int getSPIntValue(String key) {
        SharedPreferences sp = getSharedPreferences(SP_PERSON_INFO,MODE_PRIVATE);
        return sp.getInt(key, -1);
    }

    protected void clearSPValue() {
        SharedPreferences sp = getSharedPreferences(SP_PERSON_INFO,MODE_PRIVATE);
        sp.edit().clear().commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
