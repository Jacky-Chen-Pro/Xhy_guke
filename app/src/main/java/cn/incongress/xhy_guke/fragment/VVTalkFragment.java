package cn.incongress.xhy_guke.fragment;

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
import cn.incongress.xhy_guke.api.XhyGo;
import cn.incongress.xhy_guke.base.BaseFragment;
import cn.incongress.xhy_guke.bean.VVTalkBean;
import cn.incongress.xhy_guke.utils.LogUtils;
import cn.incongress.xhy_guke.utils.StringCallBackWithProgress;
import cn.incongress.xhy_guke.utils.ToastUtils;

/**
 * Created by Jacky Chen on 2016/3/22 0022.
 * V言V语
 */
public class VVTalkFragment extends BaseFragment implements RefreshLayout.OnRefreshListener, RefreshLayout.OnLoadMoreListener, MultiColumnListView.OnItemClickListener {
    /**
     * 最后一个数据Id
     **/
    private int mLastDataId = -1;
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
    private MultiColumnListView mMultiColumnListView;
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
        mMultiColumnListView = (MultiColumnListView) view.findViewById(R.id.listview);

        mAdapter = new VVTalkAdapter(getActivity(), mTalkBeans);
        View emptyView = LayoutInflater.from(getActivity()).inflate(R.layout.emptyview,null);
        mMultiColumnListView.setEmptyView(emptyView);
        mMultiColumnListView.setAdapter(mAdapter);

        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.setOnRefreshListener(this);
        initData();
    }

    @Override
    public void initData() {
        super.initData();
        getData(mLastDataId, mTopIds);
        mMultiColumnListView.setOnItemClickListener(this);
    }

    private void getData(final int lastDataId, String topIds) {
        XhyGo.getMainDataListVyvy(getActivity(),refreshLayout, lastDataId, topIds, new StringCallBackWithProgress(getActivity()) {
            @Override
            public void onAfter() {
                super.onAfter();
                refreshLayout.finishCurrentLoad();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.println("请求成功：" + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    Gson gson = new Gson();
                    int state = obj.getInt("state");
                    if (state == 1) {
                        if(lastDataId == -1) {
                            mTalkBeans.clear();
                        }
                        mTopIds = obj.getString("topIds");
                        mTalkBeans.addAll((List<VVTalkBean>)gson.fromJson(obj.getString("dataList"), new TypeToken<List<VVTalkBean>>() {}.getType()));
                        mLastDataId = mTalkBeans.get(mTalkBeans.size()-1).getDataId();
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
        getData(mLastDataId, mTopIds);
    }

    @Override
    public void onRefresh() {
        mLastDataId = -1;
        mTopIds = "";
        getData(mLastDataId, mTopIds);
    }

    @Override
    public void onItemClick(PLA_AdapterView<?> parent, View view, int position, long id) {
        VVTalkBean data = mTalkBeans.get(position);
        ToastUtils.showShorToast(data.getTitle(), getActivity());
        VVTalkDetailActivity.startVVTalkDetailActivity(getActivity(),data.getType(),data.getDataId(),VVTalkDetailActivity.WHERE_STATE_VVTALK);
    }
}
