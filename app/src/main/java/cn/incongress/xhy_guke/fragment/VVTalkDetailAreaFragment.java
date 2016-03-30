package cn.incongress.xhy_guke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.adapter.VVTalkLaudAdapter;
import cn.incongress.xhy_guke.base.BaseFragment;
import cn.incongress.xhy_guke.bean.LaudListBean;

/**
 * Created by Jacky on 2016/3/30.
 * V言V语内容部分
 */
public class VVTalkDetailAreaFragment extends BaseFragment {
    private static final String EXTRA_URL = "extra_url";
    private String mUrl = "";

    public static final VVTalkDetailAreaFragment getInstance(String url) {
        VVTalkDetailAreaFragment instance = new VVTalkDetailAreaFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_URL, url);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            mUrl = getArguments().getString(EXTRA_URL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vvtalk_detail_area, null);
        getFragmentManager().beginTransaction().add(R.id.fl_webview_area, WebViewDetailFragment.getInstance(mUrl)).commit();
        return view;
    }
}
