package cn.incongress.xhy_guke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.incongress.xhy_guke.base.BaseFragment;

/**
 * Created by Jacky on 2015/12/7.
 * 我
 */
public class MeFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(cn.incongress.xhy_guke.R.layout.fragment_me,null);
        initView(view);
        return view;
    }

    @Override
    public void initView(View view) {
        super.initView(view);


    }

    @Override
    public void initData() {
        super.initData();
    }
}
