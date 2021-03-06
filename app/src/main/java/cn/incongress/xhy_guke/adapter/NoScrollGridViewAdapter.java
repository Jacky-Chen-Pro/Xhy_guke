package cn.incongress.xhy_guke.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.ListBaseAdapter;

/**
 * Created by Jacky on 2016/4/5.
 */
public class NoScrollGridViewAdapter extends ListBaseAdapter<String> {
    private Context mContext;

    public NoScrollGridViewAdapter(Context context, String[] imgs) {
        this.mContext = context;
        for(int i=0; i<imgs.length; i++) {
            mDatas.add(imgs[i]);
        }
    }

    @Override
    protected View getTrueView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null || convertView.getTag() == null) {
            convertView =  getLayoutInflater(parent.getContext()).inflate(R.layout.item_dynamic_img,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Glide.with(mContext)
                .load(mDatas.get(position))
                .centerCrop()
                .placeholder(R.mipmap.default_load_bg)
                .into(holder.imageView);
        return convertView;
    }

    class ViewHolder  {
        ImageView imageView;

        public ViewHolder(View view) {
            this.imageView = (ImageView) view.findViewById(R.id.iv_dynamic_img);
        }
    }
}
