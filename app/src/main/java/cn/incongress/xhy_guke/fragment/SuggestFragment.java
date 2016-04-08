package cn.incongress.xhy_guke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import org.jackyonline.refreshdemo.RefreshLayout;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.api.XhyGo;
import cn.incongress.xhy_guke.base.BaseFragment;
import cn.incongress.xhy_guke.base.XhyApplication;
import cn.incongress.xhy_guke.bean.SuggestBean;
import cn.incongress.xhy_guke.utils.LogUtils;
import cn.trinea.android.common.util.AppUtils;
import okhttp3.Call;

/**
 * Created by Jacky Chen on 2016/3/22 0022.
 * 推荐
 */
public class SuggestFragment extends BaseFragment implements RefreshLayout.OnRefreshListener{
    private View rootView;// 缓存Fragment view
    private RefreshLayout mRefreshLayout;
    private SuggestBean mSuggestBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.fragment_suggest, null);
            initView(rootView);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }

        return rootView;
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        mRefreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);

        initData();
    }

    @Override
    public void initData() {
        super.initData();
        mRefreshLayout.setOnRefreshListener(this);
        getData();
    }

    private void getData() {
        XhyGo.goGetTuiJianList(getActivity(), XhyApplication.userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onAfter() {
                super.onAfter();
                mRefreshLayout.finishCurrentLoad();
            }

            @Override
            public void onResponse(String response) {

                Gson gson  = new Gson();
                mSuggestBean = gson.fromJson(response, SuggestBean.class);

            }
        });
    }

    @Override
    public void onRefresh() {
        getData();
    }
}
