package cn.incongress.xhy_guke.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.base.ListBaseAdapter;
import cn.incongress.xhy_guke.bean.MyCollectionBean;
import cn.incongress.xhy_guke.utils.StringUtils;

/**
 * Created by Jacky on 2016/4/19 0019.
 */
public class MyCollectionAdapter extends ListBaseAdapter<MyCollectionBean.DataListBean> {
    private Context mContext;

    public MyCollectionAdapter(Context context, ArrayList<MyCollectionBean.DataListBean> data) {
        this.mContext = context;
        this.mDatas = data;
    }

    @Override
    protected View getTrueView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null || convertView.getTag() == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_my_collection,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        MyCollectionBean.DataListBean bean = mDatas.get(position);
        if(StringUtils.isEmpty(bean.getBgImg())) {
            holder.ivPic.setVisibility(View.GONE);
        }else {
            holder.ivPic.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(bean.getBgImg()).into(holder.ivPic);
        }
        String title = "";
        if(bean.getType() == 3) {
            try {
                title = URLDecoder.decode(bean.getTitle(), Constants.ENCODDING_UTF8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else {
            title = bean.getTitle();
        }
        holder.tvTitle.setText(title);
        holder.tvAuthorName.setText(bean.getShowName());

        return convertView;
    }

    class ViewHolder {
        ImageView ivPic;
        TextView tvTitle,tvAuthorName;

        public ViewHolder(View view) {
            ivPic = (ImageView) view.findViewById(R.id.iv_pic);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvAuthorName = (TextView) view.findViewById(R.id.tv_user_name);
        }



    }
}
