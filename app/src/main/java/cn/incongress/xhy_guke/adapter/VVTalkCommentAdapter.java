package cn.incongress.xhy_guke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.bean.CommentListBean;
import cn.incongress.xhy_guke.uis.CircleImageView;
import cn.incongress.xhy_guke.utils.StringUtils;

/**
 * Created by Jacky on 2016/3/30.
 */
public class VVTalkCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>     {
    private static final int VIEW_TYPE_EMPTY = 0X0001;
    private static final int VIEW_TYPE_NORMAL = 0X0002;

    private Context mContext;
    private List<CommentListBean> mCommentListBean;
    private LayoutInflater mLayoutInflater;

    public VVTalkCommentAdapter(Context context, List<CommentListBean> beans) {
        this.mCommentListBean = beans;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_NORMAL) {
            View view = mLayoutInflater.inflate(R.layout.item_vvtalk_comment, null);
            CommentViewHolder holder = new CommentViewHolder(view);
            return holder;
        }else {
            View view = mLayoutInflater.inflate(R.layout.item_empty_view, null);
            EmptyViewHolder holder = new EmptyViewHolder(view);
            return holder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == VIEW_TYPE_NORMAL) {
            CommentListBean data = mCommentListBean.get(position);

            if(StringUtils.isNotEmpty(data.getUserPic())) {
                Picasso.with(mContext).load(data.getUserPic()).into(((CommentViewHolder)holder).civCommentIcon);
            }
            ((CommentViewHolder) holder).tvCommentName.setText(data.getUserName()+":");

            try {
                String content = URLDecoder.decode(data.getContent(), Constants.ENCODDING_UTF8);
                content = URLDecoder.decode(content, Constants.ENCODDING_UTF8);
                ((CommentViewHolder) holder).tvCommentContent.setText(content);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                ((CommentViewHolder) holder).tvCommentContent.setText(R.string.decode_error);
            }

        }else {
            ((EmptyViewHolder)holder).tvEmpty.setText(R.string.vvtalk_empty_tips);
        }

    }

    @Override
    public int getItemCount() {
        if(mCommentListBean!= null  && mCommentListBean.size() > 0) {
            return mCommentListBean.size();
        }else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mCommentListBean == null || mCommentListBean.size() == 0) {
            return VIEW_TYPE_EMPTY;
        }else {
            return VIEW_TYPE_NORMAL;
        }
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        CircleImageView civCommentIcon;
        TextView tvCommentName;
        TextView tvCommentContent;

        public CommentViewHolder(View itemView) {
            super(itemView);
            this.civCommentIcon = (CircleImageView) itemView.findViewById(R.id.civ_comment_icon);
            this.tvCommentContent = (TextView) itemView.findViewById(R.id.tv_comment_content);
            this.tvCommentName = (TextView) itemView.findViewById(R.id.tv_comment_name);
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
