package cn.incongress.xhy_guke.uis;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Jacky on 2016/4/19 0019.
 */
public class MyFixedListView extends ListView {
    public MyFixedListView(Context context) {
        super(context);
    }

    public MyFixedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFixedListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        try {
            super.dispatchDraw(canvas);
        } catch (IndexOutOfBoundsException e) {
            // samsung error
        }
    }
}
