package cn.incongress.xhy_guke.activitys;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.BaseActivity;
import cn.incongress.xhy_guke.uis.MainTab;
import cn.incongress.xhy_guke.uis.MyFragmentTabHost;
import cn.incongress.xhy_guke.utils.ToastUtils;

/**
 * Created by Jacky Chen on 2016/3/24 0024.
 */
public class HomeActivity  extends BaseActivity  implements TabHost.OnTabChangeListener{
    public MyFragmentTabHost mTabHost;

    private static final int RIGHT_POST = 1;
    private static final int RIGHT_SETTING = 2;
    private int mRightIconListener = RIGHT_POST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTabHost = (MyFragmentTabHost) findViewById(android.R.id.tabhost);

        mTabHost.setup(this, getSupportFragmentManager(), cn.incongress.xhy_guke.R.id.realtabcontent);
        if (android.os.Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }

        initTabs();
        initToolbar(getString(cn.incongress.xhy_guke.R.string.fragment_v_v_talk), false, true, R.mipmap.home_message, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //消息站
                startActivity(new Intent(HomeActivity.this, MsgStationActivity.class));
            }
        }, true, R.mipmap.home_make_post, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRightIconListener == RIGHT_POST) {
                    startActivity(new Intent(HomeActivity.this, MakePostActivity.class));
                }else if(mRightIconListener == RIGHT_SETTING) {
                    startActivity(new Intent(HomeActivity.this, SettingActivity.class));
                }
            }
        });

        setLeftAndRightImg(0);
        mTabHost.setCurrentTab(0);
        mTabHost.setOnTabChangedListener(this);
    }

    private void initTabs() {
        MainTab[] tabs = MainTab.values();
        final int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab mainTab = tabs[i];
            TabHost.TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));
            View indicator = LayoutInflater.from(getApplicationContext()).inflate(cn.incongress.xhy_guke.R.layout.tab_indicator, null);
            TextView title = (TextView) indicator.findViewById(cn.incongress.xhy_guke.R.id.tab_title);
            Drawable drawable =  getResources().getDrawable(mainTab.getResIcon());
            title.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);

            title.setText(getString(mainTab.getResName()));
            tab.setIndicator(indicator);
            tab.setContent(new TabHost.TabContentFactory() {

                @Override
                public View createTabContent(String tag) {
                    return new View(HomeActivity.this);
                }
            });

            mTabHost.addTab(tab, mainTab.getClz(), null);

//            if (mainTab.equals(MainTab.ME)) {
//                View cn = indicator.findViewById(R.id.tab_mes);
//                mBvNotice = new BadgeView(HomeActivity.this, cn);
//                mBvNotice.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
//                mBvNotice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
//                mBvNotice.setBackgroundResource(R.drawable.notification_bg);
//                mBvNotice.setGravity(Gravity.CENTER);
//                mBvNotice.show();
//            }
//            mTabHost.getTabWidget().getChildAt(i).setOnTouchListener(this);
        }
    }

    @Override
    public void onTabChanged(String tabId) {
        final int size = mTabHost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View v = mTabHost.getTabWidget().getChildAt(i);
            if (i == mTabHost.getCurrentTab()) {
                v.setSelected(true);
                if(i!=3) {
                    mTvTitle.setText(mTabHost.getCurrentTabTag());
                }else {
                    mTvTitle.setText(R.string.home_title);
                }

                setLeftAndRightImg(mTabHost.getCurrentTab());
            } else {
                v.setSelected(false);
            }
        }
//        if (tabId.equals(getString(MainTab.ME.getResName()))) {
//            mBvNotice.setText("");
//            mBvNotice.hide();
//        }
    }

    /**
     * 根据当前id设置左右Icon
     * @param tagId
     */
    private void setLeftAndRightImg(int tagId) {
        if(tagId == 0 || tagId == 1) {
            mIvLeftIcon.setVisibility(View.VISIBLE);
            mIvRightIcon.setVisibility(View.VISIBLE);
            mIvLeftIcon.setImageResource(R.mipmap.home_message);
            mIvRightIcon.setImageResource(R.mipmap.home_make_post);

            mRightIconListener = RIGHT_POST;
        }else if(tagId == 2) {
            mIvLeftIcon.setVisibility(View.GONE);
            mIvRightIcon.setVisibility(View.GONE);
        }else if(tagId == 3) {
            mIvLeftIcon.setVisibility(View.GONE);
            mIvRightIcon.setVisibility(View.VISIBLE);
            mIvRightIcon.setImageResource(R.mipmap.me_setting);
            mRightIconListener = RIGHT_SETTING;
        }
    }

    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                ToastUtils.showShorToast(getString(R.string.double_click_to_exit), HomeActivity.this);
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
