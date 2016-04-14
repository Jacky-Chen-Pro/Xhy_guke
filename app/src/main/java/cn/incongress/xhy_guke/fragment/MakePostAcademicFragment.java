package cn.incongress.xhy_guke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.BaseFragment;

/**
 * Created by Jacky on 2016/4/12.
 * 发布学术界面
 */
public class MakePostAcademicFragment extends BaseFragment {

    public static MakePostAcademicFragment getInstance() {
        MakePostAcademicFragment fragment = new MakePostAcademicFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_post_academics, null);
        initView(view);
        return view;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        initData();
        dismissProgressDialog();
    }

    @Override
    public void initData() {
        super.initData();
    }
}
