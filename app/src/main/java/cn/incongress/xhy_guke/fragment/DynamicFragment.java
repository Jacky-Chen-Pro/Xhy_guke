package cn.incongress.xhy_guke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.callback.StringCallback;

import org.jackyonline.refreshdemo.RefreshLayout;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.adapter.DynamicsAdapter;
import cn.incongress.xhy_guke.api.XhyGo;
import cn.incongress.xhy_guke.base.BaseFragment;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.bean.DynamicListBean;
import cn.incongress.xhy_guke.utils.LogUtils;
import okhttp3.Call;


/**
 * Created by Jacky Chen on 2016/3/22 0022.
 * V言V语
 */
public class DynamicFragment extends BaseFragment implements RefreshLayout.OnRefreshListener, RefreshLayout.OnLoadMoreListener{
    private int mLasdDataID = -1;

    private RefreshLayout mRefresh;
    private RecyclerView mRcvDynamics;
    private LinearLayoutManager mLinearLayoutManager;
    private DynamicsAdapter mAdapter;

    private List<DynamicListBean> mDynamicBeans = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(cn.incongress.xhy_guke.R.layout.fragment_dynamic,null);

        mRefresh = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        mRcvDynamics = (RecyclerView) view.findViewById(R.id.rcv_dynamics);
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        mRcvDynamics.setLayoutManager(mLinearLayoutManager);

        mRefresh.setOnRefreshListener(this);
        mRefresh.setOnLoadMoreListener(this);

        int result = XhyGo.getDataListDt(getActivity(), mLasdDataID + "", Constants.PAGE_SIZE, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                LogUtils.println(response);

                try {
                    JSONObject obj = new JSONObject(response);
                    Gson gson = new Gson();
                    mDynamicBeans.addAll((List<DynamicListBean>) gson.fromJson(obj.getString("dataList"), new TypeToken<List<DynamicListBean>>() {}.getType()));

                    fillContainer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

    private void fillContainer() {
        mAdapter = new DynamicsAdapter(getActivity(),mDynamicBeans);
        mRcvDynamics.setAdapter(mAdapter);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onRefresh() {

    }
}
