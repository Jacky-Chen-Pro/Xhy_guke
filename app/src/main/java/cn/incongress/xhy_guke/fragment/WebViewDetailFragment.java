package cn.incongress.xhy_guke.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.BaseFragment;
import cn.incongress.xhy_guke.uis.ProgressWebView;

/**
 * Created by Jacky Chen on 2016/3/29 0029.
 * V言V语详情页中加载的WevView使用该Fragment来嵌套
 */
public class WebViewDetailFragment extends BaseFragment {
    private static final String BUNDLE_URL = "url";
    private ProgressWebView mWebView;
    private String mUrl;

    public static final WebViewDetailFragment getInstance(String url) {
        WebViewDetailFragment fragment = new WebViewDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUrl = getArguments().getString(BUNDLE_URL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vvtalk_webview_detail, null);
        initView(view);
        return view;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mWebView = (ProgressWebView) view.findViewById(R.id.webview);
//        mIvLoading = (ImageView) view.findViewById(R.id.iv_loading);
        initialWebViewSetting();
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    @Override
    public void initData() {
        super.initData();
        mWebView.loadUrl(mUrl);
    }

    /**
     * 初始化Webview的配置
     */
    private void initialWebViewSetting() {
        //只有在系统版本号低于18的情况下才调用该方法
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        }
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setUseWideViewPort(true);

        // /*** 打开本地缓存提供JS调用 这里是因为js需要调用本地缓存而设置的权限**/
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);

        String appCachePath = getActivity().getCacheDir().getAbsolutePath();
        mWebView.getSettings().setAppCachePath(appCachePath);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                animationDrawable.stop();
//                mIvLoading.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                mIvLoading.setImageResource(R.drawable.anim_loading);
//                animationDrawable = (AnimationDrawable) mIvLoading.getDrawable();
//                animationDrawable.start();
            }
        });

        //设置回退规则
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
                        mWebView.goBack();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        clearCache();
        mWebView.reload();
    }

    /**
     * 缓存清理
     */
    private void clearCache() {
        try {
            mWebView.loadUrl("javascript:clearCachc()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
