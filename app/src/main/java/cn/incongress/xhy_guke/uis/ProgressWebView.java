package cn.incongress.xhy_guke.uis;

import android.content.Context;
import android.net.http.SslError;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import cn.incongress.xhy_guke.utils.LogUtils;

public class ProgressWebView extends WebView {
    private ProgressBar progressbar;

    boolean allowDragTop = true; // 如果是true，则允许拖动至底部的下一页
    float downY = 0;
    boolean needConsumeTouch = true; // 是否需要承包touch事件，needConsumeTouch一旦被定性，则不会更改

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 8, 0, 0));
        addView(progressbar);
        setWebChromeClient(new MyWebChromeClient());
        setWebViewClient(new MyWebViewClient());
    }

    public class MyWebViewClient extends WebViewClient{

		@Override
		public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
			handler.proceed();
		}
    }
    public class MyWebChromeClient extends WebChromeClient {
		@Override
    	public void onCloseWindow(WebView window) {
    		super.onCloseWindow(window);
    	}
    	@Override
    	public boolean onCreateWindow(WebView view, boolean dialog,
    			boolean userGesture, Message resultMsg) {
    		return super.onCreateWindow(view, dialog, userGesture, resultMsg);
    	    
    	}
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downY = ev.getRawY();
            needConsumeTouch = true; // 默认情况下，listView内部的滚动优先，默认情况下由该listView去消费touch事件
            allowDragTop = isInBottom();
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (!needConsumeTouch) {
                // 在最顶端且向上拉了，则这个touch事件交给父类去处理
                getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            } else if (allowDragTop) {
                // needConsumeTouch尚未被定性，此处给其定性
                // 允许拖动到底部的下一页，而且又向上拖动了，就将touch事件交给父view
                if (ev.getRawY() - downY < 0) {
                    // flag设置，由父类去消费
                    needConsumeTouch = false;
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
            }
        }

        // 通知父view是否要处理touch事件
        getParent().requestDisallowInterceptTouchEvent(needConsumeTouch);
        return super.dispatchTouchEvent(ev);
    }

    /**
     * @return
     */
    private boolean isInBottom() {
        if((int)(getContentHeight()*getScale()) -(getHeight()+getScrollY()) < 5 ){
            LogUtils.println("当前高度：" + getHeight()+getScrollY());
            return true;
        }else {
            LogUtils.println("当前高度：" + getHeight()+getScrollY());
            return false;
        }
    }
}