package cn.incongress.xhy_guke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.bean.DynamicListBean;
import cn.incongress.xhy_guke.uis.CircleImageView;
import cn.incongress.xhy_guke.uis.NoScrollGridView;
import cn.trinea.android.common.util.StringUtils;

/**
 * Created by Jacky on 2016/3/30.
 * 动态的Adapter
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
        if (viewType == VIEW_TYPE_NORMAL) {
            View view = mLayoutInflater.inflate(R.layout.item_dynamic, null);
            DynamicViewHolder holder = new DynamicViewHolder(view);
            return holder;
        } else {
            View view = mLayoutInflater.inflate(R.layout.item_empty_view, null);
            EmptyViewHolder holder = new EmptyViewHolder(view);
            return holder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_NORMAL) {
            DynamicListBean data = mDynamicListBeans.get(position);
            int type = data.getType();

            //1新闻 2病例 3发帖 4课件 5视频
            //专家发帖
            if (type == 3) {
                if (cn.incongress.xhy_guke.utils.StringUtils.isNotEmpty(data.getUserPic())) {
                    Picasso.with(mContext).load(data.getUserPic()).into(((DynamicViewHolder) holder).civUserIcon);
                }
                ((DynamicViewHolder) holder).tvUserName.setText(data.getShowName());
                ((DynamicViewHolder) holder).tvUserHospital.setText(data.getCompany());

                ((DynamicViewHolder) holder).tvShowTime.setText(data.getShowTime());
                ((DynamicViewHolder) holder).tvReadCount.setText(data.getReadCount()+"");

                try {
                    String title = URLDecoder.decode(data.getTitle(), Constants.ENCODDING_UTF8);
                    ((DynamicViewHolder) holder).tvDynamicContent.setText(title);
                } catch (UnsupportedEncodingException e) {
                    ((DynamicViewHolder) holder).tvDynamicContent.setText(R.string.decode_error);
                    e.printStackTrace();
                }

                if (data.getIsImg() != 0) {
                    Picasso.with(mContext).load(data.getBgImg()).into(((DynamicViewHolder) holder).pvOneImage);
                    ((DynamicViewHolder) holder).pvOneImage.setVisibility(View.VISIBLE);

//                    // 启用图片缩放功能
//                    ((DynamicViewHolder) holder).pvOneImage.enable();
//                    // 获取图片信息
//                    Info info = ((DynamicViewHolder) holder).pvOneImage.getInfo();
//                    // 从一张图片信息变化到现在的图片，用于图片点击后放大浏览，具体使用可以参照demo的使用
//                    ((DynamicViewHolder) holder).pvOneImage.animaFrom(info);
                }

            } else {
                ((DynamicViewHolder) holder).tvDynamicContent.setText(data.getTitle());
            }

        } else {
            ((EmptyViewHolder) holder).tvEmpty.setText(R.string.dynamic_empty_tips);
        }
    }

    @Override
    public int getItemCount() {
        if (mDynamicListBeans != null && mDynamicListBeans.size() > 0) {
            return mDynamicListBeans.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mDynamicListBeans == null || mDynamicListBeans.size() == 0) {
            return VIEW_TYPE_EMPTY;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    /**
     * 专家适配器
     */
    class DynamicViewHolder extends RecyclerView.ViewHolder {
        CircleImageView civUserIcon;
        TextView tvUserName, tvUserHospital, tvDynamicContent, tvShowTime, tvReadCount;
        PhotoView pvOneImage;
        NoScrollGridView ngvTwoOrFour;
        NoScrollGridView ngvOther;

        public DynamicViewHolder(View itemView) {
            super(itemView);
            this.civUserIcon = (CircleImageView) itemView.findViewById(R.id.civ_user_icon);
            this.tvUserName = (TextView) itemView.findViewById(R.id.tv_user_name);
            this.tvUserHospital = (TextView) itemView.findViewById(R.id.tv_user_hospital);
            this.tvDynamicContent = (TextView) itemView.findViewById(R.id.tv_dynamic_content);
            this.pvOneImage = (PhotoView) itemView.findViewById(R.id.pv_one_image);
            this.ngvTwoOrFour = (NoScrollGridView) itemView.findViewById(R.id.ngv_two_or_four_image);
            this.ngvOther = (NoScrollGridView) itemView.findViewById(R.id.ngv_other_image);
            this.tvShowTime = (TextView) itemView.findViewById(R.id.tv_show_time);
            this.tvReadCount = (TextView) itemView.findViewById(R.id.tv_read_count);
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
