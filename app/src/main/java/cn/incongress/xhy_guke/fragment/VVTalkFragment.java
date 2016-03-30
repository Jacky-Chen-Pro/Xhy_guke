package cn.incongress.xhy_guke.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huewu.pla.lib.MultiColumnListView;
import com.huewu.pla.lib.internal.PLA_AdapterView;

import org.jackyonline.refreshdemo.RefreshLayout;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.activitys.VVTalkDetailActivity;
import cn.incongress.xhy_guke.adapter.VVTalkAdapter;
import cn.incongress.xhy_guke.api.XhyApiClient;
import cn.incongress.xhy_guke.base.BaseFragment;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.bean.VVTalkBean;
import cn.incongress.xhy_guke.utils.LogUtils;
import cn.incongress.xhy_guke.utils.StringCallBackWithProgress;
import cn.incongress.xhy_guke.utils.ToastUtils;
import okhttp3.Call;

/**
 * Created by Jacky Chen on 2016/3/22 0022.
 * V言V语
 */
public class VVTalkFragment extends BaseFragment implements RefreshLayout.OnRefreshListener, RefreshLayout.OnLoadMoreListener, MultiColumnListView.OnItemClickListener {
    /**
     * 最后一个数据Id
     **/
    private String mLastDataId = "-1";
    /**
     * 指定的帖子Id
     **/
    private String mTopIds = "";
    /**
     * 下拉刷新控件
     */
    private RefreshLayout refreshLayout;
    /**
     * 瀑布流控件
     **/
    private MultiColumnListView mRecyclerView;
    /**
     * 瀑布流适配器
     **/
    private VVTalkAdapter mAdapter;
    /**
     * 首页数据集
     **/
    private ArrayList<VVTalkBean> mTalkBeans = new ArrayList<VVTalkBean>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(cn.incongress.xhy_guke.R.layout.fragment_vvtalk, null);
        initView(view);
        return view;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        mRecyclerView = (MultiColumnListView) view.findViewById(R.id.listview);

        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.setOnRefreshListener(this);
        initData();
    }

    @Override
    public void initData() {
        super.initData();
        getData(mLastDataId, mTopIds, true);

        mAdapter = new VVTalkAdapter(getActivity(), mTalkBeans);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnItemClickListener(this);
    }

    private void getData(String lastDataId, String topIds, final boolean isRefresh) {
        XhyApiClient.getMainDataListVyvy(lastDataId, Constants.PAGE_SIZE, topIds, new StringCallBackWithProgress(getActivity()) {
            List<VVTalkBean> beans = new ArrayList<>();

            @Override
            public void onError(Call call, Exception e) {
                LogUtils.println("请求失败" + e.toString());
            }

            @Override
            public void onAfter() {
                super.onAfter();

                if (isRefresh) {
                    refreshLayout.refreshComplete();
                } else {
                    refreshLayout.loadMoreComplete();
                }
            }

            @Override
            public void onResponse(String response) {
                LogUtils.println("请求成功：" + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    Gson gson = new Gson();
                    int state = obj.getInt("state");
                    if (state == 1) {
                        beans = gson.fromJson(obj.getString("dataList"), new TypeToken<List<VVTalkBean>>() {
                        }.getType());
                        mTopIds = obj.getString("topIds");
                        mTalkBeans.addAll(beans);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtils.showShorToast(obj.getString("msg"), getActivity());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onLoadMore() {
        getData(mTalkBeans.get(mTalkBeans.size() - 1).getDataId() + "", mTopIds, false);
    }

    @Override
    public void onRefresh() {
        mLastDataId = "-1";
        mTopIds = "";
        mTalkBeans.clear();
        getData(mLastDataId, mTopIds, true);
    }

    @Override
    public void onItemClick(PLA_AdapterView<?> parent, View view, int position, long id) {
        VVTalkBean data = mTalkBeans.get(position);
        ToastUtils.showShorToast(data.getTitle(), getActivity());
        VVTalkDetailActivity.startVVTalkDetailActivity(getActivity(),data.getType(),data.getDataId(),VVTalkDetailActivity.WHERE_STATE_VVTALK);
    }
}
