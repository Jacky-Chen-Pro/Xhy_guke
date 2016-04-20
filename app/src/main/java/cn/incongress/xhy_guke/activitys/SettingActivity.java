package cn.incongress.xhy_guke.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.api.XhyApiClient;
import cn.incongress.xhy_guke.base.BaseActivity;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.base.XhyApplication;
import cn.incongress.xhy_guke.utils.ToastUtils;

/**
 * Created by Jacky on 2016/4/20.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initToolbar(getString(R.string.setting_title), true, false, -1, null, false, -1, null);

        findViewById(R.id.rl_feed_back).setOnClickListener(this);
        findViewById(R.id.rl_suggest_friend).setOnClickListener(this);
        findViewById(R.id.rl_about_us).setOnClickListener(this);
        findViewById(R.id.bt_logout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_feed_back:
                WebViewActivity.startWebViewActivity(SettingActivity.this, getString(R.string.setting_feed_back_url, XhyApplication.userId, Constants.PROJECT_NAME), getString(R.string.setting_feed_back));
                break;
            case R.id.rl_suggest_friend:
                startActivity(new Intent(SettingActivity.this, SuggestFriendActivity.class));
                break;
            case R.id.rl_about_us:
                WebViewActivity.startWebViewActivity(SettingActivity.this, getString(R.string.setting_about_us_url, XhyApplication.userId, Constants.PROJECT_NAME), getString(R.string.setting_about_us));
                break;
            case R.id.bt_logout:
                ToastUtils.showShorToast(getString(R.string.setting_exit), SettingActivity.this);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
