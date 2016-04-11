package cn.incongress.xhy_guke.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.AccessibleObject;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.bean.SuggestBean;
import cn.incongress.xhy_guke.uis.CircleImageView;
import cn.incongress.xhy_guke.utils.DensityUtil;
import cn.incongress.xhy_guke.utils.StringUtils;

/**
 * Created by Jacky on 2016/4/11.
 */
public class SuggestSpecailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private SuggestBean mSuggestBean;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public SuggestSpecailAdapter(Context context, SuggestBean suggestBean){
        this.mContext = context;
        this.mSuggestBean = suggestBean;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_special_suggest,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(mSuggestBean!= null && mSuggestBean.getTbtjList().size() > 0) {
            SuggestBean.TbtjListBean bean = mSuggestBean.getTbtjList().get(position);

            //设置宽度
            holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(DensityUtil.getScreenSize((Activity)mContext)[0]/3, ViewGroup.LayoutParams.WRAP_CONTENT));

            if(StringUtils.isNotEmpty(bean.getUserPic())) {
                Picasso.with(mContext).load(bean.getUserPic()).into(((ViewHolder) holder).civUserIcon);
            }

            ((ViewHolder) holder).tvUserName.setText(bean.getUserName());
            ((ViewHolder) holder).tvUserHospital.setText(bean.getHospital());
        }
    }

    @Override
    public int getItemCount() {
        return mSuggestBean.getTbtjList().size();
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
}
