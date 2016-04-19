package cn.incongress.xhy_guke.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.callback.StringCallback;

import org.jackyonline.refreshdemo.RefreshLayout;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.adapter.HisHomePageAdapter;
import cn.incongress.xhy_guke.api.XhyGo;
import cn.incongress.xhy_guke.base.BaseActivity;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.base.XhyApplication;
import cn.incongress.xhy_guke.bean.HomePageBean;
import cn.incongress.xhy_guke.bean.UserByHeadBean;
import cn.incongress.xhy_guke.uis.CircleImageView;
import cn.incongress.xhy_guke.uis.ExpandableTextView;
import cn.incongress.xhy_guke.uis.MyFixedListView;
import cn.incongress.xhy_guke.utils.LogUtils;
import cn.incongress.xhy_guke.utils.StringUtils;
import cn.incongress.xhy_guke.utils.ToastUtils;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Jacky on 2016/4/18.
 * 他的主页
 */
public class HisHomePageActivity extends BaseActivity implements RefreshLayout.OnLoadMoreListener, RefreshLayout.OnRefreshListener {
    private RefreshLayout mRefreshLayout;
    private MyFixedListView mLvHomePage;

    private static final String EXTRA_VISIT_USERID = "visitUserId";

    private String mVisitUserId = "-1";//访问的userId
    private String mLastDataId = "-1";

    private HomePageBean mHomePageBean;
    private UserByHeadBean mUserBean;
    private CircleImageView mCivUser;
    private TextView mTvUserName, mTvUserHospital, mTvFocusCount;
    private ImageView mIvAddFocus;
    private ExpandableTextView mEtvPersonInfo;

    private ArrayList<HomePageBean.DataListBean> mListBean = new ArrayList<>();
    private HisHomePageAdapter mAdapter;
    private int mHeaderViewCount = 1;

    public static void startHisHomePageActivity(Context context, String visitUserId) {
        Intent intent = new Intent();
        intent.setClass(context, HisHomePageActivity.class);
        intent.putExtra(EXTRA_VISIT_USERID, visitUserId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_home_page);

        mVisitUserId = getIntent().getStringExtra(EXTRA_VISIT_USERID);
        initToolbar(getString(R.string.homepage_title), true, false, -1, null, false, -1, null);

        mRefreshLayout = getViewById(R.id.refreshLayout);
        mLvHomePage = getViewById(R.id.lv_home_page);

        final View headView = LayoutInflater.from(this).inflate(R.layout.head_home_page, null);
        mCivUser = (CircleImageView) headView.findViewById(R.id.civ_user_icon);
        mTvUserName = (TextView) headView.findViewById(R.id.tv_user_name);
        mTvUserHospital = (TextView) headView.findViewById(R.id.tv_user_hospital);
        mTvFocusCount = (TextView) headView.findViewById(R.id.tv_focus_count);
        mEtvPersonInfo = (ExpandableTextView) headView.findViewById(R.id.expand_text_view);
        mIvAddFocus = (ImageView) headView.findViewById(R.id.iv_add_follow);

        mLvHomePage.addHeaderView(headView);

        mAdapter = new HisHomePageAdapter(this, mListBean);
        mLvHomePage.setAdapter(mAdapter);

        mLvHomePage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position >= mHeaderViewCount) {
                    HomePageBean.DataListBean dataListBean = mListBean.get(position - mHeaderViewCount);
                    VVTalkDetailActivity.startVVTalkDetailActivity(HisHomePageActivity.this, dataListBean.getType(), dataListBean.getDataId(), VVTalkDetailActivity.WHERE_STATE_VVTALK);
                }
            }
        });

        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setOnRefreshListener(this);
        mIvAddFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUserBean.getIsFocus() == 1) {
                    addFocus(mVisitUserId, Constants.FOCUS_CANCEL);
                } else {
                    addFocus(mVisitUserId, Constants.FOCUS_ADD);
                }
            }
        });

        showProgressDialog();
        getData(mLastDataId);
    }

    @Override
    public void onLoadMore() {
        getData(mLastDataId);
    }

    @Override
    public void onRefresh() {
        mListBean.clear();
        getData(mLastDataId = "-1");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getData(String lastDataId) {
        XhyGo.goGetDataListByUser(this, mVisitUserId, lastDataId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
            }

            @Override
            public void onResponse(String response) {
                LogUtils.println(response);

                mHomePageBean = new Gson().fromJson(response, HomePageBean.class);
                if (mHomePageBean.getState() == 1) {
                    if (mHomePageBean.getDataList() != null && mHomePageBean.getDataList().size() > 0) {
                        mListBean.addAll(mHomePageBean.getDataList());

                    } else {

                    }
                } else {
                    ToastUtils.showShorToast(getString(R.string.no_data_tips), HisHomePageActivity.this);
                }
            }

            @Override
            public void onAfter() {
                super.onAfter();
                if ("-1".equals(mLastDataId)) {
                    getHeadData();
                    if (mListBean.size() > 0) {
                        mLastDataId = "" + mListBean.get(mListBean.size() - 1).getDataId();
                    }
                } else {
                    mAdapter.notifyDataSetChanged();
                    if (mListBean.size() > 0) {
                        mLastDataId = "" + mListBean.get(mListBean.size() - 1).getDataId();
                    }
                    dismissProgressDialog();
                    mRefreshLayout.finishCurrentLoad();
                }

            }
        });
    }

    /**
     * 获取头部信息
     */
    private void getHeadData() {
        XhyGo.goGetUserByHeader(this, XhyApplication.userId, mVisitUserId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onAfter() {
                super.onAfter();
                mRefreshLayout.finishCurrentLoad();
                mAdapter.notifyDataSetChanged();
                dismissProgressDialog();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.println("personInfo:" + response);
                mUserBean = new Gson().fromJson(response, UserByHeadBean.class);

                if (mUserBean.getState() == 1) {
                    if (StringUtils.isNotEmpty(mUserBean.getUserPic())) {
                        Picasso.with(HisHomePageActivity.this).load(mUserBean.getUserPic()).into(mCivUser);
                    }

                    mTvUserName.setText(mUserBean.getTrueName());
                    mTvUserHospital.setText(mUserBean.getHospitalName());
                    mTvFocusCount.setText(mUserBean.getFocusCount() + "");
                    mEtvPersonInfo.setText(mUserBean.getRemark());

                    if (mUserBean.getIsFocus() == 1) {
                        mIvAddFocus.setImageResource(R.mipmap.follow_done);
                    } else {
                        mIvAddFocus.setImageResource(R.mipmap.follow_add);
                    }
                }
            }
        });
    }

    /**
     * 添加关注 1关注0取消关注
     */
    private void addFocus(final String focusUserId, final String focusState) {
        XhyGo.goDoUserFocus(HisHomePageActivity.this, XhyApplication.userId, focusUserId, focusState, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                LogUtils.println("focus:" + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getInt("state") == 1) {
                        mUserBean.setIsFocus(Integer.parseInt(focusState));

                        if (mUserBean.getIsFocus() == 1) {
                            mIvAddFocus.setImageResource(R.mipmap.follow_done);
                        } else {
                            mIvAddFocus.setImageResource(R.mipmap.follow_add);
                        }
                    } else {
                        ToastUtils.showShorToast(getString(R.string.suggest_focus_fail, obj.getString("msg")), HisHomePageActivity.this);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
