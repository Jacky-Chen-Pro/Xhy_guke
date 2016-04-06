package cn.incongress.xhy_guke.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.BaseActivity;

/**
 * Created by Jacky on 2016/4/6.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private Button mBtLogin;
    private TextView mTvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mBtLogin = getViewById(R.id.bt_login);
        mTvRegister = getViewById(R.id.tv_register);
        initEvents();
    }

    private void initEvents() {
        mBtLogin.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int target = v.getId();
        if(target == R.id.bt_login) {
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
        }else if(target == R.id.tv_register) {
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        }
    }
}
