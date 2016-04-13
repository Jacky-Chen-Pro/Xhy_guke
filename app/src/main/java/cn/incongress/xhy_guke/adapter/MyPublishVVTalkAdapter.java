package cn.incongress.xhy_guke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.bean.MyVVTalkBean;
import cn.incongress.xhy_guke.utils.StringUtils;

/**
 * Created by Jacky on 2016/4/13.
 */
public class MyPublishVVTalkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_EMPTY = 0X0001;
    private static final int VIEW_TYPE_NORMAL = 0X0002;

    private Context mContext;
    private List<MyVVTalkBean.DataListBean> mMyTalkBeans;
    private LayoutInflater mInflater;

    public MyPublishVVTalkAdapter(Context context, List<MyVVTalkBean.DataListBean> beans) {
        this.mContext = context;
        this.mMyTalkBeans = beans;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_NORMAL) {
            View view = mInflater.inflate(R.layout.item_my_vvtalk_with_bg, null);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }else{
            View view = mInflater.inflate(R.layout.item_empty_view, null);
            EmptyViewHolder holder = new EmptyViewHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_TYPE_NORMAL) {
            MyVVTalkBean.DataListBean dataListBean = mMyTalkBeans.get(position);

            if(StringUtils.isNotEmpty(dataListBean.getBgImg())) {
                Picasso.with(mContext).load(dataListBean.getBgImg()).into( ((ViewHolder)holder).ivBg);
                ((ViewHolder)holder).ivBg.setVisibility(View.VISIBLE);
            }else {
                ((ViewHolder)holder).ivBg.setVisibility(View.GONE);
            }

            ((ViewHolder)holder).mTvTitleWithTime.setText(dataListBean.getTitle());
            ((ViewHolder)holder).mTvReadCount.setText(mContext.getString(R.string.mypublish_read_count, dataListBean.getReadCount()));
            ((ViewHolder)holder).mTvCommentCount.setText(mContext.getString(R.string.mypublish_comment_count, dataListBean.getCommentCount()));
            ((ViewHolder)holder).mTvPraiseCount.setText(mContext.getString(R.string.mypublish_praise_count, dataListBean.getLaudCount()));
        }else {
            ((EmptyViewHolder) holder).tvEmpty.setText(R.string.mypublish_no_data);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mMyTalkBeans == null || mMyTalkBeans.size() == 0) {
            return VIEW_TYPE_EMPTY;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        if(mMyTalkBeans != null && mMyTalkBeans.size() > 0) {
            return mMyTalkBeans.size();
        }else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBg;
        TextView mTvTitleWithTime,mTvReadCount,mTvCommentCount,mTvPraiseCount;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivBg = (ImageView) itemView.findViewById(R.id.iv_bg);
            this.mTvTitleWithTime = (TextView) itemView.findViewById(R.id.tv_title_with_time);
            this.mTvReadCount = (TextView) itemView.findViewById(R.id.tv_read_count);
            this.mTvCommentCount = (TextView) itemView.findViewById(R.id.tv_comment_count);
            this.mTvPraiseCount = (TextView) itemView.findViewById(R.id.tv_praise_count);
        }
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmpty;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            tvEmpty = (TextView) itemView.findViewById(R.id.tv_empty_tips);
        }
    }
}
