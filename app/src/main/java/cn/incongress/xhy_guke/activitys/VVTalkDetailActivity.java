package cn.incongress.xhy_guke.activitys;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.adapter.VVTalkLaudAdapter;
import cn.incongress.xhy_guke.api.XhyGo;
import cn.incongress.xhy_guke.base.BaseActivity;
import cn.incongress.xhy_guke.base.XhyApplication;
import cn.incongress.xhy_guke.bean.VVTalkDetailBean;
import cn.incongress.xhy_guke.fragment.VVTalkDetailAreaFragment;
import cn.incongress.xhy_guke.fragment.VVTalkDetailCommentFragment;
import cn.incongress.xhy_guke.fragment.WebViewDetailFragment;
import cn.incongress.xhy_guke.utils.LogUtils;
import cn.incongress.xhy_guke.utils.ToastUtils;
import okhttp3.Call;

/**
 * Created by Jacky Chen on 2016/3/29 0029.
 * V言V语详情页
 */
public class VVTalkDetailActivity extends BaseActivity {

    private static final String EXTRA_TYPE = "extra_type";
    private static final String EXTRA_DATA_ID = "data_id";
    private static final String EXTRA_WHERE_STATE = "where_state";

    /** EXTRA_TYPE **/
    public static final int DETAIL_TYPE = 1;//webview

    public static final int WHERE_STATE_VVTALK = 1;
    public static final int WHERE_STATE_DYNAMIC = 2;

    private int type;
    private int dataId;
    private int whereState;

    private VVTalkDetailBean mDetailBean;

    public static final void startVVTalkDetailActivity(Context context, int type, int dataId, int whereState) {
        Intent intent = new Intent();
        intent.setClass(context, VVTalkDetailActivity.class);
        intent.putExtra(EXTRA_TYPE, type);
        intent.putExtra(EXTRA_DATA_ID, dataId);
        intent.putExtra(EXTRA_WHERE_STATE, whereState);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vvtalk_detail);
        initToolbar(getString(R.string.vvtalk_detail_title), true, false, -1, null, false, -1, null);


        type = getIntent().getIntExtra(EXTRA_TYPE, -1);
        dataId = getIntent().getIntExtra(EXTRA_DATA_ID, -1);
        whereState = getIntent().getIntExtra(EXTRA_WHERE_STATE, -1);

        if(type==-1 || dataId == -1 || whereState == -1) {
            ToastUtils.showShorToast(getString(R.string.error_happen_back_to_home),this);
            this.finish();
        }

        int result = XhyGo.getDataById(this, dataId+"", XhyApplication.userId, whereState+"", new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                LogUtils.println(response);
                Gson gson = new Gson();
                mDetailBean = gson.fromJson(response, VVTalkDetailBean.class);
                LogUtils.println(mDetailBean.toString());

                fillContainer();
            }
        });
    }

    /**
     * 页面布局
     */
    private void fillContainer() {
        getSupportFragmentManager().beginTransaction().
                add(R.id.fl_detail_area, VVTalkDetailAreaFragment.getInstance(mDetailBean.getHtmlUrl()))
                .add(R.id.fl_comment_area,   VVTalkDetailCommentFragment.getInstance(mDetailBean.getLaudList(), mDetailBean.getDataId() + "")).commit();
    }
}
