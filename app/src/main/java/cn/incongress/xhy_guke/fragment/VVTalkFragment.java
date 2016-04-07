package cn.incongress.xhy_guke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huewu.pla.lib.MultiColumnListView;
import com.huewu.pla.lib.internal.PLA_AbsListView;
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
import cn.incongress.xhy_guke.uis.popup.CommentPopupWindow;
import cn.incongress.xhy_guke.utils.LogUtils;
import cn.incongress.xhy_guke.utils.StringCallBackWithProgress;
import cn.incongress.xhy_guke.utils.ToastUtils;

/**
 * Created by Jacky Chen on 2016/3/22 0022.
 * V言V语
 */
public class VVTalkFragment extends BaseFragment implements RefreshLayout.OnRefreshListener, RefreshLayout.OnLoadMoreListener, MultiColumnListView.OnItemClickListener {
    private int mLastDataId = -1;
    private String mTopIds = "";

    private RefreshLayout refreshLayout;
    private MultiColumnListView mMultiColumnListView;
    private VVTalkAdapter mAdapter;

    private ArrayList<VVTalkBean> mTalkBeans = new ArrayList<VVTalkBean>();

    private View rootView;// 缓存Fragment view
    private int scrolledX = 0,scrolledY = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.fragment_vvtalk, null);
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
    public void onResume() {
        super.onResume();

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

        mMultiColumnListView.setOnScrollListener(new PLA_AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(PLA_AbsListView view, int scrollState) {
                // 不滚动时保存当前滚动到的位置
                if (scrollState == PLA_AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    if (getActivity() != null) {
                        scrolledY = mMultiColumnListView.getFirstVisiblePosition();
                        LogUtils.e("VVTalkFragment", "position:" + scrolledX +"," + scrolledY);
                    }
                }
            }

            @Override
            public void onScroll(PLA_AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
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
        VVTalkDetailActivity.startVVTalkDetailActivity(getActivity(),data.getType(),data.getDataId(),VVTalkDetailActivity.WHERE_STATE_VVTALK);
    }
}
