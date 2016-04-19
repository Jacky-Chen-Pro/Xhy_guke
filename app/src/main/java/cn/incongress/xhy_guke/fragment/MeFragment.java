package cn.incongress.xhy_guke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import org.jackyonline.refreshdemo.RefreshLayout;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.activitys.MyCollectionActivity;
import cn.incongress.xhy_guke.activitys.MyPublishPostActivity;
import cn.incongress.xhy_guke.api.XhyGo;
import cn.incongress.xhy_guke.base.BaseFragment;
import cn.incongress.xhy_guke.base.XhyApplication;
import cn.incongress.xhy_guke.bean.UserCountBean;
import cn.incongress.xhy_guke.uis.CircleImageView;
import cn.incongress.xhy_guke.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Jacky on 2015/12/7.
 * 我
 */
public class MeFragment extends BaseFragment implements RefreshLayout.OnRefreshListener,View.OnClickListener {
    CircleImageView mCivUserIcon;
    private TextView mTvFollowNums, mTvFanNums, mTvVisitCount, mTvPraiseCount, mTvCommentCount;
    private TextView mTvWeekVisitCount, mTvWeekPraiseCount, mTvWeekCommentCount;
    private TextView mTvPublishCount, mTvCollectionCount;
    private LinearLayout mLlMyPublishVVTalk,mLlCollection;

    private UserCountBean mUserCountBean;

    private RefreshLayout mRefreshLayout;

    private View rootView;// 缓存Fragment view

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.fragment_me_professor, null);
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
        mCivUserIcon = (CircleImageView) view.findViewById(R.id.civ_user_icon);
        mTvFollowNums = (TextView) view.findViewById(R.id.tv_follow_num);
        mTvFanNums = (TextView) view.findViewById(R.id.tv_fan_num);
        mTvVisitCount = (TextView) view.findViewById(R.id.tv_visit_count);
        mTvPraiseCount = (TextView) view.findViewById(R.id.tv_praise_count);
        mTvCommentCount = (TextView) view.findViewById(R.id.tv_comment_count);
        mTvWeekVisitCount = (TextView) view.findViewById(R.id.tv_week_visit);
        mTvWeekPraiseCount = (TextView) view.findViewById(R.id.tv_week_praise);
        mTvWeekCommentCount = (TextView) view.findViewById(R.id.tv_week_comment);
        mTvPublishCount = (TextView) view.findViewById(R.id.tv_publish_count);
        mTvCollectionCount = (TextView) view.findViewById(R.id.tv_collection_count);

        mLlMyPublishVVTalk = (LinearLayout) view.findViewById(R.id.ll_my_publish_vvtalk);
        mLlCollection = (LinearLayout) view.findViewById(R.id.ll_collection);

        initData();
    }

    @Override
    public void initData() {
        super.initData();

        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(null);
        mLlMyPublishVVTalk.setOnClickListener(this);
        mLlCollection.setOnClickListener(this);

        if(getData() == XhyGo.INTERNET_ERROR && mProgressDialog != null && mProgressDialog.isShowing()) {
            dismissProgressDialog();
        }
    }



    private int getData() {
        return XhyGo.goGetUserNumbers(getActivity(), XhyApplication.userId, XhyApplication.userType + "", new StringCallback() {
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
                LogUtils.println("userInfo=" + response);
                Gson gson = new Gson();
                mUserCountBean = gson.fromJson(response, UserCountBean.class);

                if (mUserCountBean != null && mUserCountBean.getState() == 1) {
                    mTvFollowNums.setText(getString(R.string.me_follow_count, mUserCountBean.getFocusCount()));
                    mTvFanNums.setText(getString(R.string.me_fans_count, mUserCountBean.getFansCount()));

                    mTvVisitCount.setText(getString(R.string.me_all_visit_count, mUserCountBean.getReadCount()));
                    mTvPraiseCount.setText(getString(R.string.me_all_praise_count, mUserCountBean.getLaudCount()));
                    mTvCommentCount.setText(getString(R.string.me_all_comment_count, mUserCountBean.getCommentCount()));

                    mTvWeekVisitCount.setText(mUserCountBean.getWeekReadCount() + "");
                    mTvWeekPraiseCount.setText(mUserCountBean.getWeekLaudCount() + "");
                    mTvWeekCommentCount.setText(mUserCountBean.getWeekCommentCount() + "");

                    mTvPublishCount.setText(mUserCountBean.getDataCount() + "");
                    mTvCollectionCount.setText(mUserCountBean.getShoucangCount() + "");
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        getData();
    }

    @Override
    public void onClick(View v) {
        int target = v.getId();

        switch (target) {
            case R.id.ll_my_publish_vvtalk:
                MyPublishPostActivity.startMyPublishVVtalkActivity(getActivity());
                break;
            case R.id.ll_collection:
                MyCollectionActivity.startMyCollectionActivity(getActivity());
                break;
        }
    }
}
