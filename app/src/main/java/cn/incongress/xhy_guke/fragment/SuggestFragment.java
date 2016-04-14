package cn.incongress.xhy_guke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zhy.http.okhttp.callback.StringCallback;

import org.jackyonline.refreshdemo.RefreshLayout;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.adapter.SuggestHotAdapter;
import cn.incongress.xhy_guke.adapter.SuggestSpecailAdapter;
import cn.incongress.xhy_guke.api.XhyGo;
import cn.incongress.xhy_guke.base.BaseFragment;
import cn.incongress.xhy_guke.base.XhyApplication;
import cn.incongress.xhy_guke.bean.SuggestBean;
import cn.incongress.xhy_guke.uis.CircleImageView;
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

    private RecyclerView mRcvSpecail,mRcvHot;
    private LinearLayoutManager mHorizontalManager, mVerticalManager;
    private SuggestSpecailAdapter mSpecialAdapter;
    private SuggestHotAdapter mHotAdapter;

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
        mRcvSpecail = (RecyclerView) view.findViewById(R.id.rcv_special_suggest);
        mRcvHot = (RecyclerView) view.findViewById(R.id.rcv_hot_suggest);

        mVerticalManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        mHorizontalManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        if(getData() == XhyGo.INTERNET_ERROR && mProgressDialog != null && mProgressDialog.isShowing()) {
            dismissProgressDialog();
        }
    }

    @Override
    public void initData() {
        super.initData();
        mRefreshLayout.setOnRefreshListener(this);
        getData();
    }

    private int getData() {
        return XhyGo.goGetTuiJianList(getActivity(), XhyApplication.userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onAfter() {
                super.onAfter();
                mRefreshLayout.finishCurrentLoad();
                dismissProgressDialog();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.println("respont=" + response);
                Gson gson  = new Gson();
                mSuggestBean = gson.fromJson(response, SuggestBean.class);
                if(mSuggestBean!= null ) {
                    mSpecialAdapter = new SuggestSpecailAdapter(getActivity(),mSuggestBean);
                    mHotAdapter = new SuggestHotAdapter(getActivity(),mSuggestBean);

                    mRcvHot.setAdapter(mHotAdapter);
                    mRcvSpecail.setAdapter(mSpecialAdapter);

                    mRcvSpecail.setLayoutManager(mHorizontalManager);
                    mRcvHot.setLayoutManager(mVerticalManager);

                    mRcvHot.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                            .paintProvider(mHotAdapter)
                            .visibilityProvider(mHotAdapter)
                            .marginProvider(mHotAdapter)
                            .build());
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        getData();
    }
}
