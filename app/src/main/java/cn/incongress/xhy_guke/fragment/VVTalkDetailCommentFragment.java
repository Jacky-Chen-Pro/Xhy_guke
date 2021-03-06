package cn.incongress.xhy_guke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.activitys.VVTalkDetailActivity;
import cn.incongress.xhy_guke.adapter.VVTalkCommentAdapter;
import cn.incongress.xhy_guke.adapter.VVTalkLaudAdapter;
import cn.incongress.xhy_guke.api.XhyGo;
import cn.incongress.xhy_guke.base.BaseFragment;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.base.XhyApplication;
import cn.incongress.xhy_guke.bean.CommentListBean;
import cn.incongress.xhy_guke.bean.LaudListBean;
import cn.incongress.xhy_guke.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Jacky on 2016/3/30.
 * V言V语评论部分
 */
public class VVTalkDetailCommentFragment extends BaseFragment {
    private static final String EXTRA_LAUD_LIST = "extra_laud_list";
    private static final String EXTRA_DATA_ID = "extra_data_id";

    private List<LaudListBean> mLaudList;
    private String mDataId;

    /** 点赞列表 **/
    private RecyclerView mRvLaud;
    private LinearLayoutManager mLmRvLaud;
    private VVTalkLaudAdapter mLaudAdapter;
    private TextView mTvLaudCount;

    /** 评论列表 **/
    private RecyclerView mRcvComments;
    private int mLastCommentId = -1;
    private List<CommentListBean> mCommentList = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;
    private VVTalkCommentAdapter mCommentAdapter;

    public static final VVTalkDetailCommentFragment getInstance(List<LaudListBean> laudList, String dataId) {
        VVTalkDetailCommentFragment instance = new VVTalkDetailCommentFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_LAUD_LIST, (Serializable) laudList);
        bundle.putString(EXTRA_DATA_ID, dataId);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            mLaudList = (List<LaudListBean>) getArguments().getSerializable(EXTRA_LAUD_LIST);
            mDataId = getArguments().getString(EXTRA_DATA_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vvtalk_comment_area, null);

        //点赞榜
        mRvLaud = (RecyclerView) view.findViewById(R.id.rv_laud);
        mLmRvLaud = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        mRvLaud.setLayoutManager(mLmRvLaud);
        mTvLaudCount = (TextView) view.findViewById(R.id.tv_laud_count);

        mLaudAdapter = new VVTalkLaudAdapter(getActivity(), mLaudList);
        mRvLaud.setAdapter(mLaudAdapter);
        mTvLaudCount.setText(getString(R.string.vvtalk_detail_laud_count, mLaudList.size()));

        //评论列表
        mRcvComments = (RecyclerView) view.findViewById(R.id.rcv_comments);
        mLinearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        mRcvComments.setLayoutManager(mLinearLayoutManager);

        mCommentAdapter = new VVTalkCommentAdapter(getActivity(),mCommentList);
        mRcvComments.setAdapter(mCommentAdapter);

        mRcvComments.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getActivity())
                        .color(getActivity().getResources().getColor(R.color.graywhite))
                        .size(getResources().getDimensionPixelSize(R.dimen.divider_height))
                        .margin(getResources().getDimensionPixelSize(R.dimen.layout_margin),
                                getResources().getDimensionPixelSize(R.dimen.layout_margin))
                        .build());

        if(getData(mLastCommentId+"") == XhyGo.INTERNET_ERROR && mProgressDialog!=null && mProgressDialog.isShowing()) {
            dismissProgressDialog();
        }

        return view;
    }

    private void fillContainer() {
       mCommentAdapter.notifyDataSetChanged();

      ((VVTalkDetailActivity)getActivity()).completeRefresh();
    }

    private int getData(String lastCommentId) {
        return XhyGo.getCommentList(getActivity(), mDataId, XhyApplication.userId, lastCommentId, Constants.PAGE_SIZE, new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
            }

            @Override
            public void onAfter() {
                super.onAfter();
                dismissProgressDialog();
            }

            @Override
            public void onResponse(String response) {
                LogUtils.println(response);
                try {
                    JSONObject obj = new JSONObject(response);
                    Gson gson = new Gson();
                    mCommentList.addAll((List<CommentListBean>) gson.fromJson(obj.getString("commentList"), new TypeToken<List<CommentListBean>>() {
                    }.getType()));
                    if (mCommentList.size() > 0) {
                        mLastCommentId = mCommentList.get(mCommentList.size() - 1).getCommentId();
                    }
                    fillContainer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //加载更多评论
    public void loadMoreComment() {
        getData(mLastCommentId + "");
    }

    public void addCommentToList(CommentListBean commentListBean) {
        mCommentList.add(0, commentListBean);
        mCommentAdapter.notifyDataSetChanged();
        //刷新末尾的评论id
        if (mCommentList.size() > 0) {
            mLastCommentId = mCommentList.get(mCommentList.size() - 1).getCommentId();
        }
    }

}
