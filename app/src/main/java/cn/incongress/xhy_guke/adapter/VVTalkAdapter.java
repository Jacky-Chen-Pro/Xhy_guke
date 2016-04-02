package cn.incongress.xhy_guke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.ListBaseAdapter;
import cn.incongress.xhy_guke.bean.VVTalkBean;
import cn.incongress.xhy_guke.uis.CircleImageView;
import cn.incongress.xhy_guke.utils.StringUtils;

/**
 * Created by Jacky on 2016/3/28.
 */
public class VVTalkAdapter extends ListBaseAdapter<VVTalkBean> {
    private Context mContext;

    public VVTalkAdapter(Context context, ArrayList<VVTalkBean> beans) {
        this.mContext = context;
        this.mDatas = beans;
    }

    @Override
    protected View getTrueView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(R.layout.item_vvtalk_professor,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        final VVTalkBean data = mDatas.get(position);
        bindDataWithView(holder, data);

        return convertView;
    }

    /**
     * 专家和普通医生的ui字段相同
     */
    class ViewHolder {
        ImageView ivBg;
        CircleImageView civUserIcon;
        TextView tvUserName;
        TextView tvUserHospital;
        TextView tvTitle;
        TextView tvLookNums;
        TextView tvPublishTime;

        public ViewHolder(View view) {
            ivBg = (ImageView) view.findViewById(R.id.iv_bg);
            civUserIcon = (CircleImageView) view.findViewById(R.id.civ_user_icon);
            tvUserName = (TextView) view.findViewById(R.id.tv_user_name);
            tvUserHospital = (TextView) view.findViewById(R.id.tv_user_hospital);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvLookNums = (TextView) view.findViewById(R.id.tv_look_num);
            tvPublishTime = (TextView) view.findViewById(R.id.tv_publish_time);
        }
    }

    private void bindDataWithView(ViewHolder holder, VVTalkBean data) {
        if(StringUtils.isNotEmpty(data.getBgImg())) {
            Picasso.with(mContext).load(data.getBgImg()).placeholder(R.mipmap.item_background_professor_default).error(R.mipmap.item_background_professor_default).into(holder.ivBg);
        }
        if(StringUtils.isNotEmpty(data.getUserPic())) {
            Picasso.with(mContext).load(data.getUserPic()).placeholder(R.mipmap.item_vvtalk_professor_head_default).error(R.mipmap.item_vvtalk_professor_head_default).into(holder.civUserIcon);
        }
        holder.tvUserName.setText(data.getShowName());
        holder.tvUserHospital.setText(data.getCompany());
        holder.tvTitle.setText(data.getTitle());
        holder.tvLookNums.setText(mContext.getString(R.string.vvtalk_read_count,data.getReadCount()));
        holder.tvPublishTime.setText(data.getShowTime());
    }
}
