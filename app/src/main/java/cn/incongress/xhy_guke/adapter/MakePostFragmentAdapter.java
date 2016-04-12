package cn.incongress.xhy_guke.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacky on 2016/4/12.
 */
public class MakePostFragmentAdapter extends FragmentPagerAdapter{
    private List<Fragment> mFragments = new ArrayList<>();

    public MakePostFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
      return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
