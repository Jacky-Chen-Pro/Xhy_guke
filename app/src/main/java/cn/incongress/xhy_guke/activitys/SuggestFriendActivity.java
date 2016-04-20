package cn.incongress.xhy_guke.activitys;

import android.os.Bundle;
import android.view.View;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.BaseActivity;
import cn.incongress.xhy_guke.utils.ToastUtils;

/**
 * Created by Administrator on 2015/7/8.
 */
public class SuggestFriendActivity extends BaseActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_friend);

        initToolbar(getString(R.string.setting_suggest_friend), true, false, -1, null, false, -1, null);

        findViewById(R.id.rl_suggest_to_friend).setOnClickListener(this);
        findViewById(R.id.rl_suggest_to_friend_circle).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_suggest_to_friend:
                ToastUtils.showShorToast("推荐给朋友", SuggestFriendActivity.this);
                break;
            case R.id.rl_suggest_to_friend_circle:
                ToastUtils.showShorToast("推荐给朋友圈", SuggestFriendActivity.this);
                break;
        }
    }
}
