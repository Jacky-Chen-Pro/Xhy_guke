package cn.incongress.xhy_guke.activitys;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.adapter.MsgStationAdapter;
import cn.incongress.xhy_guke.base.BaseActivity;
import cn.incongress.xhy_guke.fragment.BroadcastMessageFragment;
import cn.incongress.xhy_guke.fragment.SystemMessageFragment;
import cn.incongress.xhy_guke.fragment.UserMessageFragment;

/**
 * Created by Jacky on 2016/4/7.
 */
public class MsgStationActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewpager;
    private MsgStationAdapter mPageAdapter;

    private int[] mTitles = {R.string.msg_user_msg, R.string.msg_system_msg, R.string.msg_broadcast_msg};
    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_station);
        initToolbar(getString(R.string.msg_title), true, false, -1, null, false, -1, null);

        initViews();
    }

    private void initViews() {
        mTabLayout = getViewById(R.id.tablayout);
        mViewpager = getViewById(R.id.viewpager);

        for(int i=0; i<mTitles.length; i++) {
            mTitleList.add(getString(mTitles[i]));
        }

        mFragments.add(0, new UserMessageFragment());
        mFragments.add(1, new SystemMessageFragment());
        mFragments.add(2, new BroadcastMessageFragment());

        //设置TabLayout的模式
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称

        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(2)));

        mPageAdapter = new MsgStationAdapter(getSupportFragmentManager(),mFragments,mTitleList);
        mViewpager.setAdapter(mPageAdapter);
        mViewpager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewpager);
    }

}
