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

    boolean allowDragTop = true;
    float downY = 0;
    boolean needConsumeTouch = true;

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
            needConsumeTouch = true;
            allowDragTop = isInBottom();
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (!needConsumeTouch) {
                getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            } else if (allowDragTop) {
                if (ev.getRawY() - downY < 0) {
                    needConsumeTouch = false;
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
            }
        }

        getParent().requestDisallowInterceptTouchEvent(needConsumeTouch);
        return super.dispatchTouchEvent(ev);
    }

    /**
     * @return
     */
    private boolean isInBottom() {
        if((int)(getContentHeight()*getScale()) -(getHeight()+getScrollY()) < 5 ){
            return true;
        }else {
            return false;
        }
    }
}