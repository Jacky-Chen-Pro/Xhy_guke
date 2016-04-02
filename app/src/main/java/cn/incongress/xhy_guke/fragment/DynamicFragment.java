package cn.incongress.xhy_guke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
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
import cn.incongress.xhy_guke.bean.VVTalkBean;
import cn.incongress.xhy_guke.utils.LogUtils;
import cn.incongress.xhy_guke.utils.ToastUtils;
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

    View mParent;
    View mBg;
    PhotoView mPhotoView;
    Info mInfo;

    AlphaAnimation in = new AlphaAnimation(0, 1);
    AlphaAnimation out = new AlphaAnimation(1, 0);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(cn.incongress.xhy_guke.R.layout.fragment_dynamic,null);

        mParent = view.findViewById(R.id.parent);
        mBg = view.findViewById(R.id.bg);
        mPhotoView = (PhotoView) view.findViewById(R.id.img);

        mRefresh = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        mRcvDynamics = (RecyclerView) view.findViewById(R.id.rcv_dynamics);
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        mRcvDynamics.setLayoutManager(mLinearLayoutManager);

        mAdapter = new DynamicsAdapter(getActivity(),mDynamicBeans);
        mAdapter.setGoToBrowerModeListener(new DynamicsAdapter.GoToBrowserModeListener() {
            @Override
            public void doGoBrower(View view, String urlPath) {
                PhotoView p = (PhotoView) view;
                mInfo = p.getInfo();

                Picasso.with(getActivity()).load(urlPath).into(mPhotoView);

                mBg.startAnimation(in);
                mParent.setVisibility(View.VISIBLE);
                mPhotoView.animaFrom(mInfo);
            }
        });

        mPhotoView.enable();
        mPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBg.startAnimation(out);
                mPhotoView.animaTo(mInfo, new Runnable() {
                    @Override
                    public void run() {
                        mParent.setVisibility(View.GONE);
                    }
                });
            }
        });

        mRcvDynamics.setAdapter(mAdapter);
        mRcvDynamics.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());

        mRefresh.setOnRefreshListener(this);
        mRefresh.setOnLoadMoreListener(this);

        getData(mLasdDataID);

        return view;
    }

    @Override
    public void onLoadMore() {
        getData(mLasdDataID);
    }

    @Override
    public void onRefresh() {
        getData(mLasdDataID = -1);
    }

    /**
     * 获取数据
     * @return
     */
    private int getData(final int lasdDataID) {
        return XhyGo.getDataListDt(getActivity(), mRefresh,lasdDataID + "", Constants.PAGE_SIZE, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onAfter() {
                super.onAfter();
                mRefresh.finishCurrentLoad();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.println(response);

                try {
                    JSONObject obj = new JSONObject(response);
                    int state = obj.getInt("state");
                    Gson gson = new Gson();

                    if (state == 1) {
                        if(lasdDataID == -1) {
                            mDynamicBeans.clear();
                        }
                        mDynamicBeans.addAll((List<DynamicListBean>) gson.fromJson(obj.getString("dataList"), new TypeToken<List<DynamicListBean>>() {}.getType()));
                        mLasdDataID = mDynamicBeans.get(mDynamicBeans.size()-1).getDataId();
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
}
