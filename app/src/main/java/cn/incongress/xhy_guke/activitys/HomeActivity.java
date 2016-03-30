package cn.incongress.xhy_guke.activitys;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import cn.incongress.xhy_guke.base.BaseActivity;
import cn.incongress.xhy_guke.uis.MainTab;
import cn.incongress.xhy_guke.uis.MyFragmentTabHost;

/**
 * Created by Jacky Chen on 2016/3/24 0024.
 */
public class HomeActivity  extends BaseActivity  implements TabHost.OnTabChangeListener{
    public MyFragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cn.incongress.xhy_guke.R.layout.activity_home);

        mTabHost = (MyFragmentTabHost) findViewById(android.R.id.tabhost);

        mTabHost.setup(this, getSupportFragmentManager(), cn.incongress.xhy_guke.R.id.realtabcontent);
        if (android.os.Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }

        initTabs();
        initToolbar(getString(cn.incongress.xhy_guke.R.string.fragment_v_v_talk),false,false,-1,null,false,-1,null);
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
                    mTvTitle.setText(cn.incongress.xhy_guke.R.string.home_title);
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
            mIvLeftIcon.setImageResource(cn.incongress.xhy_guke.R.mipmap.home_message);
            mIvRightIcon.setImageResource(cn.incongress.xhy_guke.R.mipmap.home_make_post);
        }else if(tagId == 2) {
            mIvLeftIcon.setVisibility(View.GONE);
            mIvRightIcon.setVisibility(View.GONE);
        }else if(tagId == 3) {
            mIvLeftIcon.setVisibility(View.GONE);
            mIvRightIcon.setVisibility(View.GONE);
        }
    }
}
