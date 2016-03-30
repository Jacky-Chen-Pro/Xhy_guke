package cn.incongress.xhy_guke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.bean.DynamicListBean;
import cn.incongress.xhy_guke.uis.CircleImageView;

/**
 * Created by Jacky on 2016/3/30.
 */
public class DynamicsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_EMPTY = 0X0001;
    private static final int VIEW_TYPE_NORMAL = 0X0002;

    private Context mContext;
    private List<DynamicListBean> mDynamicListBeans;
    private LayoutInflater mLayoutInflater;

    public DynamicsAdapter(Context context, List<DynamicListBean> beans) {
        this.mDynamicListBeans = beans;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_NORMAL) {
            View view = mLayoutInflater.inflate(R.layout.item_dynamic, null);
            DynamicViewHolder holder = new DynamicViewHolder(view);
            return holder;
        }else {
            View view = mLayoutInflater.inflate(R.layout.item_empty_view, null);
            EmptyViewHolder holder = new EmptyViewHolder(view);
            return holder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_TYPE_NORMAL) {
            DynamicListBean data = mDynamicListBeans.get(position);
            ((DynamicViewHolder)holder).tvTitle.setText(data.getTitle());
        }else {
            ((EmptyViewHolder)holder).tvEmpty.setText(R.string.dynamic_empty_tips);
        }

    }

    @Override
    public int getItemCount() {
        if(mDynamicListBeans != null  && mDynamicListBeans.size() > 0) {
            return mDynamicListBeans.size();
        }else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mDynamicListBeans == null || mDynamicListBeans.size() == 0) {
            return VIEW_TYPE_EMPTY;
        }else {
            return VIEW_TYPE_NORMAL;
        }
    }

    class DynamicViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public DynamicViewHolder(View itemView) {
            super(itemView);
            this.tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
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
