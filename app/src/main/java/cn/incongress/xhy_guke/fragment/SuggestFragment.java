package cn.incongress.xhy_guke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.incongress.xhy_guke.base.BaseFragment;

/**
 * Created by Jacky Chen on 2016/3/22 0022.
 * 推荐
 */
public class SuggestFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(cn.incongress.xhy_guke.R.layout.fragment_suggest,null);
        return view;
    }
}
