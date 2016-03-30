package cn.incongress.xhy_guke.base;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Jacky Chen on 2016/3/24 0024.
 */
public class BaseActivity extends AppCompatActivity {
    /** 全局唯一的toolbar **/
    protected Toolbar mOnlyToolbar;
    /** 左右两侧按钮 **/
    protected ImageView mIvLeftIcon,mIvRightIcon;

    protected TextView mTvTitle;

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

}
