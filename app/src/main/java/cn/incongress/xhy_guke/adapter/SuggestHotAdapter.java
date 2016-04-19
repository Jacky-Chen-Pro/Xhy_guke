package cn.incongress.xhy_guke.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yqritc.recyclerviewflexibledivider.FlexibleDividerDecoration;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.bean.SuggestBean;
import cn.incongress.xhy_guke.uis.CircleImageView;
import cn.incongress.xhy_guke.utils.DensityUtil;
import cn.incongress.xhy_guke.utils.StringUtils;

/**
 * Created by Jacky on 2016/4/11.
 */
public class SuggestHotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        FlexibleDividerDecoration.PaintProvider,
//        FlexibleDividerDecoration.SizeProvider,
//        FlexibleDividerDecoration.ColorProvider,
        FlexibleDividerDecoration.VisibilityProvider,
        HorizontalDividerItemDecoration.MarginProvider, View.OnClickListener {

    @Override
    public int dividerLeftMargin(int position, RecyclerView parent) {
        return DensityUtil.dip2px(mContext, 12);
    }

    @Override
    public int dividerRightMargin(int position, RecyclerView parent) {
        return DensityUtil.dip2px(mContext, 12);
    }

    @Override
    public Paint dividerPaint(int position, RecyclerView parent) {
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(1);

        return paint;
    }

    @Override
    public boolean shouldHideDivider(int position, RecyclerView parent) {
        if(position == 0 ||  position == mSuggestBean.getZvList().size() || position == mSuggestBean.getZvList().size()+1) {
            return true;
        }else {
            return false;
        }
    }

    private SuggestBean mSuggestBean;
    private Context mContext;
    private LayoutInflater mInflater;

    private static final int TYPE_NORMAL = 0X0001;
    private static final int TYPE_TITLE = 0X0002;

    private List<SuggestBean.ZvListBean> mInnerBean = new ArrayList<>();


    public SuggestHotAdapter(Context context, SuggestBean bean) {
        this.mContext = context;
        this.mSuggestBean = bean;
        this.mInflater = LayoutInflater.from(mContext);

        mInnerBean.add(new SuggestBean.ZvListBean());
        for (int i = 0; i < mSuggestBean.getZvList().size(); i++) {
            mInnerBean.add(mSuggestBean.getZvList().get(i));
        }

        mInnerBean.add(new SuggestBean.ZvListBean());
        for (int i = 0; i < mSuggestBean.getTrList().size(); i++) {
            SuggestBean.TrListBean trListBean = mSuggestBean.getTrList().get(i);

            SuggestBean.ZvListBean zvListBean = new SuggestBean.ZvListBean();
            zvListBean.setUserId(trListBean.getUserId());
            zvListBean.setUserType(trListBean.getUserType());
            zvListBean.setUserName(trListBean.getUserName());
            zvListBean.setUserPic(trListBean.getUserPic());
            zvListBean.setHospital(trListBean.getHospital());
            zvListBean.setFocusMeCount(trListBean.getFocusMeCount());
            zvListBean.setIsFocus(trListBean.getIsFocus());
            zvListBean.setLaudCount(trListBean.getLaudCount());
            zvListBean.setReadCount(trListBean.getReadCount());
            zvListBean.setRang(trListBean.getRang());
            mInnerBean.add(zvListBean);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            View view = mInflater.inflate(R.layout.item_hot_suggest, null);
            HotViewHolder holder = new HotViewHolder(view);
            return holder;
        } else {
            View view = mInflater.inflate(R.layout.item_suggest_title, null);
            TitleViewHolder holder = new TitleViewHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        if (viewType == TYPE_NORMAL) {
            SuggestBean.ZvListBean zvListBean = mInnerBean.get(position);
            if (StringUtils.isNotEmpty(zvListBean.getUserPic())) {
                Glide.with(mContext).load(zvListBean.getUserPic()).into(((HotViewHolder) holder).civUserIcon);
            }
            ((HotViewHolder) holder).tvUserName.setText(zvListBean.getUserName());
            ((HotViewHolder) holder).tvUserHospital.setText((String) zvListBean.getHospital());
            ((HotViewHolder) holder).tvAttentionProgress.setText(mContext.getString(R.string.suggest_attention_progress, zvListBean.getFocusMeCount()));
            ((HotViewHolder) holder).tvReadCount.setText(mContext.getString(R.string.suggest_read_progress, zvListBean.getReadCount()));
            ((HotViewHolder) holder).tvPraiseCount.setText(mContext.getString(R.string.suggest_praise_progress, zvListBean.getLaudCount()));
            if(position >3) {
                setHotTrImageRange(((HotViewHolder) holder).ivHotLable, zvListBean.getRang());
            }else {
                setHotZrImageRange(((HotViewHolder) holder).ivHotLable, zvListBean.getRang());
            }

            if(zvListBean.getIsFocus() == 1) {
                ((HotViewHolder) holder).ivAddFollow.setImageResource(R.mipmap.follow_done);
            }else {
                ((HotViewHolder) holder).ivAddFollow.setImageResource(R.mipmap.follow_add);
            }

            ((HotViewHolder) holder).ivAddFollow.setTag(zvListBean);
            ((HotViewHolder) holder).ivAddFollow.setOnClickListener(this);

            holder.itemView.setTag(zvListBean);
            holder.itemView.setOnClickListener(this);

        } else {
            if (position == 0) {
                ((TitleViewHolder) holder).tvTitle.setText(R.string.suggest_hot_head);
            } else {
                ((TitleViewHolder) holder).tvTitle.setText(R.string.suggest_hot_colleague);
            }
        }
    }

    @Override
    public int getItemCount() {
        return 2 + mSuggestBean.getTrList().size() + mSuggestBean.getZvList().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == mSuggestBean.getZvList().size() + 1) {
            return TYPE_TITLE;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public void onClick(View v) {
        if(v instanceof ImageView) {
            if(mFollowListener!= null) {
                mFollowListener.followClickListener(v, (SuggestBean.ZvListBean) v.getTag());
            }
        }else {
            if(mHomePageListener != null) {
                mHomePageListener.homePageClickListener(v, (SuggestBean.ZvListBean) v.getTag());
            }
        }
    }

    /**
     * 热门推荐布局
     */
    class HotViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHotLable,ivAddFollow;
        CircleImageView civUserIcon;
        TextView tvUserName, tvUserHospital, tvAttentionProgress, tvReadCount, tvPraiseCount;

        public HotViewHolder(View itemView) {
            super(itemView);

            ivHotLable = (ImageView) itemView.findViewById(R.id.iv_hot_lable);
            civUserIcon = (CircleImageView) itemView.findViewById(R.id.civ_user_icon);
            tvUserName = (TextView) itemView.findViewById(R.id.tv_user_name);
            tvUserHospital = (TextView) itemView.findViewById(R.id.tv_user_hospital);
            tvAttentionProgress = (TextView) itemView.findViewById(R.id.tv_attention_progress);
            tvReadCount = (TextView) itemView.findViewById(R.id.tv_read_count);
            tvPraiseCount = (TextView) itemView.findViewById(R.id.tv_praise_count);
            ivAddFollow = (ImageView) itemView.findViewById(R.id.iv_add_follow);
        }
    }

    /**
     * 标题
     */
    class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public TitleViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    private void setHotZrImageRange(ImageView view, int range) {
        if(range == 1) {
            view.setImageResource(R.mipmap.hot_zr_first);
        }else if(range == 2) {
            view.setImageResource(R.mipmap.hot_zr_second);
        }else {
            view.setImageResource(R.mipmap.hot_zr_third);
        }
    }

    private void setHotTrImageRange(ImageView view, int range) {
        if(range == 1) {
            view.setImageResource(R.mipmap.hot_tr_first);
        }else if(range == 2) {
            view.setImageResource(R.mipmap.hot_tr_second);
        }else {
            view.setImageResource(R.mipmap.hot_tr_third);
        }
    }

    private OnFollowClickListener mFollowListener;
    private OnHomePageClickListener mHomePageListener;

    public interface OnFollowClickListener{
        public void followClickListener(View view, SuggestBean.ZvListBean bean);
    }

    public interface OnHomePageClickListener{
        public void homePageClickListener(View view, SuggestBean.ZvListBean bean);
    }

    public void setFollowListener(OnFollowClickListener listener) {
        this.mFollowListener = listener;
    }

    public void setHomePageListener(OnHomePageClickListener listener) {
        this.mHomePageListener = listener;
    }

    /**
     * 修改关注状态
     * @param userId
     * @param focusState
     */
    public void setFocusState(int userId, int focusState) {
        for(int i=0; i<mInnerBean.size(); i++) {
            if(mInnerBean.get(i).getUserId() == userId) {
                mInnerBean.get(i).setIsFocus(focusState);
            }
        }
        notifyDataSetChanged();
    }
}
