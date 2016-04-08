package cn.incongress.xhy_guke.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.base.ListBaseAdapter;
import cn.incongress.xhy_guke.bean.VVTalkBean;
import cn.incongress.xhy_guke.uis.CircleImageView;
import cn.incongress.xhy_guke.utils.DensityUtil;
import cn.incongress.xhy_guke.utils.StringUtils;

/**
 * Created by Jacky on 2016/3/28.
 */
public class VVTalkAdapter extends ListBaseAdapter<VVTalkBean> {

    private static final int VIEW_TYPE_PROFESSOR = 0;
    private static final int VIEW_TYPE_NORMAL = 1;

    private Context mContext;

    public VVTalkAdapter(Context context, ArrayList<VVTalkBean> beans) {
        this.mContext = context;
        this.mDatas = beans;
    }

    @Override
    protected View getTrueView(int position, View convertView, ViewGroup parent) {
        if(getItemViewType(position) == VIEW_TYPE_PROFESSOR) {
            ViewHolder holder;
            if(convertView == null || convertView.getTag() == null) {
                convertView = getLayoutInflater(parent.getContext()).inflate(R.layout.item_vvtalk_professor,null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            VVTalkBean data = mDatas.get(position);
            bindDataWithView(holder, data,position);

            return convertView;
        }else {
            ViewHolder holder;
            if(convertView == null || convertView.getTag() == null) {
                convertView = getLayoutInflater(parent.getContext()).inflate(R.layout.item_vvtalk_normal,null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            VVTalkBean data = mDatas.get(position);
            bindDataWithView(holder, data, position);

            return convertView;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(position<2) {
            return VIEW_TYPE_PROFESSOR;
        }else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * 专家和普通医生的ui字段相同
     */
    class ViewHolder {
        RelativeLayout rlContainer;
        ImageView ivBg;
        CircleImageView civUserIcon;
        TextView tvUserName;
        TextView tvUserHospital;
        TextView tvTitle;
        TextView tvLookNums;
        TextView tvPublishTime;

        public ViewHolder(View view) {
            rlContainer = (RelativeLayout) view.findViewById(R.id.rl_container);
            ivBg = (ImageView) view.findViewById(R.id.iv_bg);
            civUserIcon = (CircleImageView) view.findViewById(R.id.civ_user_icon);
            tvUserName = (TextView) view.findViewById(R.id.tv_user_name);
            tvUserHospital = (TextView) view.findViewById(R.id.tv_user_hospital);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvLookNums = (TextView) view.findViewById(R.id.tv_look_num);
            tvPublishTime = (TextView) view.findViewById(R.id.tv_publish_time);
        }
    }

    private void bindDataWithView(ViewHolder holder, VVTalkBean data, int position) {
        if(StringUtils.isNotEmpty(data.getIsImg()) && StringUtils.isNotEmpty(data.getBgImg())) {
            holder.ivBg.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(mContext,200)));
            holder.rlContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,DensityUtil.dip2px(mContext,200)));
            Picasso.with(mContext).load(data.getBgImg()).placeholder(R.mipmap.item_background_professor_default).error(R.mipmap.item_background_professor_default).into(holder.ivBg);
        }else {
            holder.ivBg.setImageResource(R.mipmap.item_background_professor_default);
            if(getItemViewType(position) == VIEW_TYPE_NORMAL) {
                holder.ivBg.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(mContext,70)));
                holder.rlContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,DensityUtil.dip2px(mContext,70)));
                holder.rlContainer.setGravity(RelativeLayout.CENTER_VERTICAL);
            }else {
                holder.ivBg.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(mContext,200)));
                holder.rlContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,DensityUtil.dip2px(mContext,200)));
            }
        }

        if(StringUtils.isNotEmpty(data.getUserPic())) {
            Picasso.with(mContext).load(data.getUserPic()).placeholder(R.mipmap.item_vvtalk_professor_head_default).error(R.mipmap.item_vvtalk_professor_head_default).into(holder.civUserIcon);
        }else {
            holder.civUserIcon.setImageResource(R.mipmap.item_vvtalk_professor_head_default);
        }

        holder.tvUserName.setText(data.getShowName());
        holder.tvUserHospital.setText(data.getCompany());
        if(data.getType() == 3) {
            try {
                String title = URLDecoder.decode(data.getTitle(), Constants.ENCODDING_UTF8);
                title = URLDecoder.decode(title, Constants.ENCODDING_UTF8);
                holder.tvTitle.setText(title);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                holder.tvTitle.setText(StringUtils.EMPTY_STRING);
            }
        }else {
            holder.tvTitle.setText(data.getTitle());
        }

        //主任或者专家的区分


        holder.tvLookNums.setText(mContext.getString(R.string.vvtalk_read_count, data.getReadCount()));
        holder.tvPublishTime.setText(data.getShowTime());
    }
}
