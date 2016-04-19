package cn.incongress.xhy_guke.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import org.jackyonline.refreshdemo.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.adapter.MyCollectionAdapter;
import cn.incongress.xhy_guke.api.XhyGo;
import cn.incongress.xhy_guke.base.BaseActivity;
import cn.incongress.xhy_guke.base.XhyApplication;
import cn.incongress.xhy_guke.bean.MyCollectionBean;
import cn.incongress.xhy_guke.uis.MyFixedListView;
import cn.incongress.xhy_guke.utils.LogUtils;
import cn.incongress.xhy_guke.utils.ToastUtils;
import okhttp3.Call;

/**
 * Created by Jacky on 2016/4/19 0019.
 */
public class MyCollectionActivity extends BaseActivity implements RefreshLayout.OnRefreshListener{
    private RefreshLayout mRefreshLayout;
    private MyFixedListView mLvCollections;

    private MyCollectionAdapter mAdapter;
    private String mLastDataId = "-1";

    private MyCollectionBean mCollectionBean;
    private ArrayList<MyCollectionBean.DataListBean> mDataBeans = new ArrayList<>();

    public static final void startMyCollectionActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MyCollectionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        initToolbar(getString(R.string.collection_title), true, false, -1, null, false, -1, null);

        mRefreshLayout = getViewById(R.id.refreshLayout);
        mLvCollections = getViewById(R.id.lv_collections);

        mAdapter = new MyCollectionAdapter(MyCollectionActivity.this, mDataBeans);
        mLvCollections.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshListener(this);

        mLvCollections.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyCollectionBean.DataListBean dataListBean = mDataBeans.get(position);
                VVTalkDetailActivity.startVVTalkDetailActivity(MyCollectionActivity.this, dataListBean.getType(), dataListBean.getDataId(), VVTalkDetailActivity.WHERE_STATE_VVTALK);
            }
        });

        getData();
    }

    private void getData() {
        XhyGo.goGetXhyShouCangs(MyCollectionActivity.this, XhyApplication.userId, new StringCallback() {
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
                LogUtils.println("response:" + response);
                mCollectionBean = new Gson().fromJson(response, MyCollectionBean.class);
                if(mCollectionBean.getState() == 1) {
                    if(mCollectionBean.getDataList()!=null && mCollectionBean.getDataList().size()>0) {
                        mDataBeans.addAll(mCollectionBean.getDataList());
                        mAdapter.notifyDataSetChanged();
                    }
                }else {
                    ToastUtils.showShorToast(getString(R.string.get_data_fail), MyCollectionActivity.this);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        mDataBeans.clear();
        getData();
    }
}
