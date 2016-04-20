package cn.incongress.xhy_guke.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.concurrent.CompletionService;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.api.XhyGo;
import cn.incongress.xhy_guke.base.BaseActivity;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.bean.LoginBean;
import cn.incongress.xhy_guke.utils.LogUtils;
import cn.incongress.xhy_guke.utils.StringUtils;
import cn.incongress.xhy_guke.utils.ToastUtils;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Jacky on 2016/4/6.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText mEtLoginName, mEtLoginPwd;
    private Button mBtLogin;
    private TextView mTvRegister;

    private LoginBean mLoginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEtLoginName = getViewById(R.id.et_mobile);
        mEtLoginPwd = getViewById(R.id.et_pwd);
        mBtLogin = getViewById(R.id.bt_login);
        mTvRegister = getViewById(R.id.tv_register);
        initEvents();
    }

    private void initEvents() {
        mBtLogin.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);

        //判断是否登录
        if (isLogin()) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            this.finish();
        }
    }

    @Override
    public void onClick(View v) {
        int target = v.getId();
        if (target == R.id.bt_login) {
            String loginName = mEtLoginName.getText().toString();
            String loginPwd = mEtLoginPwd.getText().toString();


            if (StringUtils.isNotEmpty(loginName, loginPwd)) {
                if (StringUtils.isMobileNO(loginName)) {
                    XhyGo.goXhyClientLogin(LoginActivity.this, loginName, loginPwd, new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e) {

                        }

                        @Override
                        public void onBefore(Request request) {
                            super.onBefore(request);
                            showProgressDialog();
                        }

                        @Override
                        public void onAfter() {
                            super.onAfter();
                            dismissProgressDialog();
                        }

                        @Override
                        public void onResponse(String response) {
                            LogUtils.println("login:" + response);
                            mLoginBean = new Gson().fromJson(response, LoginBean.class);

                            if (mLoginBean != null && mLoginBean.getState() == 1) {
                                ToastUtils.showShorToast(getString(R.string.login_success), LoginActivity.this);
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                //将个人信息保存到本地
                                saveLoginInfo();
                                LoginActivity.this.finish();
                            } else {
                                if (mLoginBean == null) {
                                    ToastUtils.showShorToast(getString(R.string.login_fail, ""), LoginActivity.this);
                                } else {
                                    ToastUtils.showShorToast(getString(R.string.login_fail, mLoginBean.getMsg()), LoginActivity.this);
                                }
                            }
                        }
                    });
                } else {
                    ToastUtils.showShorToast(getString(R.string.login_mobile_format_error), LoginActivity.this);
                }
            } else {
                ToastUtils.showShorToast(getString(R.string.login_name_pwd_empty), LoginActivity.this);
            }
        } else if (target == R.id.tv_register) {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        }
    }

    private void saveLoginInfo() {
        setSPIntValue(Constants.USER_USER_ID, mLoginBean.getUserId());
        setSPStringValue(Constants.USER_TRUE_NAME, mLoginBean.getTrueName());
        setSPStringValue(Constants.USER_PIC, mLoginBean.getUserPic());
        setSPStringValue(Constants.USER_SEX, mLoginBean.getSex());
        setSPStringValue(Constants.USER_MOBILE, mLoginBean.getMobilePhone());
        setSPStringValue(Constants.USER_EMAIL, mLoginBean.getEmail());
        setSPStringValue(Constants.USER_KESHI, mLoginBean.getKeshi());
        setSPStringValue(Constants.USER_ZHICHENG, mLoginBean.getZhicheng());
        setSPStringValue(Constants.USER_PROVINCE_ID, mLoginBean.getProvince());
        setSPStringValue(Constants.USER_PROVINCE_NAME, mLoginBean.getProvinceName());
        setSPStringValue(Constants.USER_CITY_ID, mLoginBean.getCity());
        setSPStringValue(Constants.USER_CITY_NAME, mLoginBean.getCityName());
        setSPStringValue(Constants.USER_HOSPITAL_ID, mLoginBean.getHospital());
        setSPStringValue(Constants.USER_HOSPITAL_NAME, mLoginBean.getHospitalName());
        setSPStringValue(Constants.USER_UNIV_ID, mLoginBean.getUnivsId());
        setSPStringValue(Constants.USER_UNIV, mLoginBean.getUniv());
        setSPStringValue(Constants.USER_UNIV_YEAR, mLoginBean.getUnivYear());
        setSPStringValue(Constants.USER_NICKNAME, mLoginBean.getNickname());
        setSPStringValue(Constants.USER_REMARK, mLoginBean.getRemark());
        setSPIntValue(Constants.USER_TYPE, mLoginBean.getUserType());
    }

    private boolean isLogin() {
        return getSPIntValue(Constants.USER_USER_ID) != -1 ? true : false;
    }
}
