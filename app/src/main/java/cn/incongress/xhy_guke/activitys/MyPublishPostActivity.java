package cn.incongress.xhy_guke.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zhy.http.okhttp.callback.StringCallback;

import org.jackyonline.refreshdemo.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.adapter.MyPublishVVTalkAdapter;
import cn.incongress.xhy_guke.api.XhyGo;
import cn.incongress.xhy_guke.base.BaseActivity;
import cn.incongress.xhy_guke.base.XhyApplication;
import cn.incongress.xhy_guke.bean.MyVVTalkBean;
import cn.incongress.xhy_guke.utils.LogUtils;
import cn.incongress.xhy_guke.utils.ToastUtils;
import okhttp3.Call;

/**
 * Created by Jacky on 2016/4/13.
 */
public class MyPublishPostActivity extends BaseActivity implements RefreshLayout.OnRefreshListener, RefreshLayout.OnLoadMoreListener{
    private int mLastDataId = -1;

    private RefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private MyVVTalkBean mVVTalkBean = new MyVVTalkBean();
    private MyPublishVVTalkAdapter mAdapter;
    private List<MyVVTalkBean.DataListBean> mListBeans = new ArrayList<>();

    public static void startMyPublishVVtalkActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyPublishPostActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_publish_vvtalk);
        initToolbar(getString(R.string.me_my_publish_post), true, false, -1, null, false, -1, null);

        initView();
        initData();
    }

    private void initData() {
      getData(mLastDataId);
    }

    private void initView() {
        mRecyclerView = getViewById(R.id.rcv_vvtalk);
        mRefreshLayout = getViewById(R.id.refreshLayout);

        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);

        mAdapter = new MyPublishVVTalkAdapter(this, mListBeans);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(MyPublishPostActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(MyPublishPostActivity.this).build());
        mAdapter.setOnItemClickListener(new MyPublishVVTalkAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, MyVVTalkBean.DataListBean dataBean) {
                ToastUtils.showShorToast(dataBean.getTitle(), MyPublishPostActivity.this);
                VVTalkDetailActivity.startVVTalkDetailActivity(MyPublishPostActivity.this,dataBean.getType(),dataBean.getDataId(), VVTalkDetailActivity.WHERE_STATE_MY_PUBLISH);
            }
        });
    }

    private void getData(final int lastDataId) {
        XhyGo.goGetDataListBySelf(MyPublishPostActivity.this, XhyApplication.userId, lastDataId + "", new StringCallback() {
            @Override
            public void onAfter() {
                super.onAfter();
                mRefreshLayout.finishCurrentLoad();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.println(response);
                mVVTalkBean = new Gson().fromJson(response, MyVVTalkBean.class);

                int state = mVVTalkBean.getState();

                if (state == 1) {
                    if (mLastDataId == -1) {
                        mListBeans.clear();
                    }
                    mListBeans.addAll(mVVTalkBean.getDataList());
                    mLastDataId = mListBeans.get(mListBeans.size() - 1).getDataId();
                    mAdapter.notifyDataSetChanged();
                } else {
                    ToastUtils.showShorToast(mVVTalkBean.getMsg(), MyPublishPostActivity.this);
                }
            }

            @Override
            public void onError(Call call, Exception e) {
                mRefreshLayout.finishCurrentLoad();
            }
        });
    }

    @Override
    public void onLoadMore() {
        if(mVVTalkBean != null && mVVTalkBean.getPageState() == 1) {
            getData(mLastDataId);
        }else {
            mRefreshLayout.finishCurrentLoad();
            ToastUtils.showShorToast(getString(R.string.mypublish_no_more_data), MyPublishPostActivity.this);
        }

    }

    @Override
    public void onRefresh() {
        mLastDataId = -1;
        getData(mLastDataId);
    }


}
