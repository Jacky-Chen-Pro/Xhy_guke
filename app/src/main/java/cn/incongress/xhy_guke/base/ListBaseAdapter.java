package cn.incongress.xhy_guke.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Jacky on 2016/3/28.
 */
public class ListBaseAdapter<T extends Object> extends BaseAdapter {
    //数据源
    protected ArrayList<T> mDatas = new ArrayList<T>();

    private LayoutInflater mInflater;

    protected LayoutInflater getLayoutInflater(Context context) {
        if (mInflater == null) {
            mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        return mInflater;
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        if (mDatas.size() > position) {
            return mDatas.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = getTrueView(position,convertView, parent);
        return view;
    }

    /**
     * 需要重写返回真正的页面
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    protected View getTrueView(int position, View convertView ,ViewGroup parent){
        return null;
    }

}
