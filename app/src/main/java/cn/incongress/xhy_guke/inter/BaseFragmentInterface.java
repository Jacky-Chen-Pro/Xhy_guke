package cn.incongress.xhy_guke.inter;

import android.view.View;

/**
 * Created by Jacky on 2016/3/25.
 * 基类Fragment需要实现的接口
 */
public interface BaseFragmentInterface {
    /**
     * 初始化页面的控件
     * @param view
     */
    void initView(View view);

    /**
     * 初始化数据，并设置到页面上
     */
    void initData();
}
