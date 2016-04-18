package cn.incongress.xhy_guke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.ListBaseAdapter;
import cn.incongress.xhy_guke.bean.DynamicListBean;
import cn.incongress.xhy_guke.utils.StringUtils;

/**
 * Created by Jacky on 2016/4/5.
 */
public class NoScrollGridViewLocalPathAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<String> mDatas = new ArrayList<>();
    private OnDeleteClickListener mDeleteClickListener;

    public NoScrollGridViewLocalPathAdapter(Context context, List<String> paths) {
        this.mContext = context;
        this.mDatas = paths;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_dynamic_img_for_make_post, null);
        view.setOnClickListener(this);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position+"");
        Picasso.with(mContext).load(new File(mDatas.get(position))).resize(400, 400).into((((ViewHolder)holder).imageView));
    }

    public void addData( String path) {
        mDatas.add(0, path);
        notifyItemInserted(0);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mDatas.size());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onClick(View v) {
        if (mDeleteClickListener != null) {
            mDeleteClickListener.onDeleteClick(v, (String) v.getTag());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.iv_dynamic_img);
        }
    }

    public interface OnDeleteClickListener {
        public void onDeleteClick(View view, String position);
    }

    public void setDeleteClickListener(OnDeleteClickListener deleteClickListener) {
        this.mDeleteClickListener = deleteClickListener;
    }

}