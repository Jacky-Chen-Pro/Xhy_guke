package cn.incongress.xhy_guke.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.BaseActivity;
import cn.incongress.xhy_guke.fragment.VVTalkDetailWebViewFragment;

/**
 * Created by Jacky on 2016/4/20.
 */
public class WebViewActivity extends BaseActivity {
    private String mUrl,mTitle;

    public static final String EXTRA_URL = "url";
    public static final String EXTRA_TITLE = "title";

    private FragmentManager mManager;

    public static void startWebViewActivity(Context context, String url, String title) {
        Intent intent = new Intent();
        intent.setClass(context,WebViewActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, title);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mUrl = getIntent().getStringExtra(EXTRA_URL);
        mTitle = getIntent().getStringExtra(EXTRA_TITLE);

        initToolbar(mTitle, true, false, -1, null, false, -1, null);

        mManager = getSupportFragmentManager();
        mManager.beginTransaction().add(R.id.fl_webview, VVTalkDetailWebViewFragment.getInstance(mUrl,"-1")).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
