package cn.incongress.xhy_guke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.bean.LaudListBean;
import cn.incongress.xhy_guke.uis.CircleImageView;
import cn.incongress.xhy_guke.utils.StringUtils;

/**
 * Created by Jacky Chen on 2016/3/29 0029.
 */
public class VVTalkLaudAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<LaudListBean> mBeans;
    private Context mContext;
    private LayoutInflater mInflater;

    public VVTalkLaudAdapter(Context context, List<LaudListBean> beans) {
        this.mBeans = beans;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_laud_icon, null);
        LaudViewHolder holder = new LaudViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LaudListBean bean = mBeans.get(position);
        if(StringUtils.isNotEmpty(bean.getUserPic()))
            Glide.with(mContext).load(bean.getUserPic()).placeholder(R.mipmap.item_vvtalk_professor_head_default).error(R.mipmap.item_vvtalk_professor_head_default).into(((LaudViewHolder) holder).civUserIcon);
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    class LaudViewHolder extends RecyclerView.ViewHolder {
        CircleImageView civUserIcon;
        public LaudViewHolder(View itemView) {
            super(itemView);
            this.civUserIcon = (CircleImageView) itemView.findViewById(R.id.civ_user_icon);
        }
    }
}
