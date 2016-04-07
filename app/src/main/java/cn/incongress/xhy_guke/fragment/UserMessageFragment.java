package cn.incongress.xhy_guke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.BaseFragment;

/**
 * Created by Jacky on 2016/4/7.
 */
public class UserMessageFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_use_message,null);
        initView(view);
        return view;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        initData();
    }

    @Override
    public void initData() {
        super.initData();
    }
}
