package cn.incongress.xhy_guke.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.bean.SuggestBean;
import cn.incongress.xhy_guke.uis.CircleImageView;
import cn.incongress.xhy_guke.utils.DensityUtil;
import cn.incongress.xhy_guke.utils.StringUtils;

/**
 * Created by Jacky on 2016/4/11.
 */
public class SuggestSpecailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private SuggestBean mSuggestBean;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private OnItemClickListener mOnItemClickListener;

    public SuggestSpecailAdapter(Context context, SuggestBean suggestBean){
        this.mContext = context;
        this.mSuggestBean = suggestBean;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_special_suggest,null);
        view.setOnClickListener(this);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(mSuggestBean!= null && mSuggestBean.getTbtjList().size() > 0) {
            SuggestBean.TbtjListBean bean = mSuggestBean.getTbtjList().get(position);

            int width = 0;

            switch (mSuggestBean.getTbtjList().size()) {
                case 1:
                    width = DensityUtil.getScreenSize((Activity)mContext)[0];
                    break;
                case 2:
                    width = DensityUtil.getScreenSize((Activity)mContext)[0]/2;
                    break;
                case 3:
                    width = DensityUtil.getScreenSize((Activity)mContext)[0]/3;
                    break;
            }
            //设置宽度
            holder.itemView.setTag(bean);
            holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));

            if(StringUtils.isNotEmpty(bean.getUserPic())) {
                Glide.with(mContext).load(bean.getUserPic()).into(((ViewHolder) holder).civUserIcon);
            }

            ((ViewHolder) holder).tvUserName.setText(bean.getUserName());
            ((ViewHolder) holder).tvUserHospital.setText(bean.getHospital());
        }
    }

    @Override
    public int getItemCount() {
        return mSuggestBean.getTbtjList().size();
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (SuggestBean.TbtjListBean) v.getTag());
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView civUserIcon;
        TextView tvUserName,tvUserHospital,tvHomePage;

        public ViewHolder(View itemView) {
            super(itemView);
            civUserIcon = (CircleImageView) itemView.findViewById(R.id.civ_user_icon);
            tvUserName = (TextView) itemView.findViewById(R.id.tv_user_name);
            tvUserHospital = (TextView) itemView.findViewById(R.id.tv_hospital);
            tvHomePage = (TextView) itemView.findViewById(R.id.tv_homepage);
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View v, SuggestBean.TbtjListBean bean);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
    }
}
