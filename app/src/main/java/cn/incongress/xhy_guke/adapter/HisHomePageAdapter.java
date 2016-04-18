package cn.incongress.xhy_guke.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.base.ListBaseAdapter;
import cn.incongress.xhy_guke.bean.HomePageBean;
import cn.incongress.xhy_guke.bean.VVTalkBean;
import cn.incongress.xhy_guke.utils.StringUtils;

/**
 * Created by Jacky on 2016/4/18.
 */
public class HisHomePageAdapter extends ListBaseAdapter<HomePageBean.DataListBean> {
    private Context mContext;

    public HisHomePageAdapter(Context context, ArrayList<HomePageBean.DataListBean> beans) {
        this.mContext = context;
        this.mDatas = beans;
    }

    @Override
    protected View getTrueView(int position, View convertView, ViewGroup parent) {
        ListViewHolder holder;
        if(convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(R.layout.item_home_page,null);
            holder = new ListViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ListViewHolder) convertView.getTag();
        }

        HomePageBean.DataListBean bean = mDatas.get(position);

        if(StringUtils.isNotEmpty(bean.getBgImg())) {
            holder.ivPic.setVisibility(View.VISIBLE);
            Picasso.with(mContext).load(bean.getBgImg()).resize(400,400).into(holder.ivPic);
        }else {
            holder.ivPic.setVisibility(View.GONE);
        }
        String title = "";
        if(bean.getType() == 3) {
            try {
                title = URLDecoder.decode(bean.getTitle(), Constants.ENCODDING_UTF8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                title = "";
            }
        }else {
            title = bean.getTitle();
        }

        holder.tvTitle.setText(title);
        holder.tvShowTime.setText(bean.getTimeShow());
        holder.tvLookNum.setText(bean.getReadCount()+"");

        return convertView;
    }

    class ListViewHolder {
        ImageView ivPic;
        TextView tvTitle;
        TextView tvShowTime;
        TextView tvLookNum;

        public ListViewHolder(View view) {
            ivPic = (ImageView) view.findViewById(R.id.iv_pic);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvShowTime = (TextView) view.findViewById(R.id.tv_show_time);
            tvLookNum = (TextView) view.findViewById(R.id.tv_read_count);
        }
    }
}
