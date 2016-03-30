//package cn.incongress.xhy_guke.activitys;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.umeng.analytics.MobclickAgent;
//
//import org.json.JSONObject;
//
//import java.net.URLDecoder;
//
//import cn.incongress.xuehuiyi.base.BaseActivity;
//import cn.incongress.xuehuiyi.base.XhyApplication;
//import cn.incongress.xuehuiyi.bean.ImageUploadBean;
//import cn.incongress.xuehuiyi.base.Constant;
//import cn.incongress.xuehuiyi.bean.UserBean;
//import cn.incongress.xuehuiyi.json.JsonPaserTools;
//import cn.incongress.xuehuiyi.service.SimpleDataCallback;
//import cn.incongress.xuehuiyi.service.xhy.XhyAppServiceImp;
//import cn.incongress.xuehuiyi.uis.DialogSingle;
//import cn.incongress.xuehuiyi.utils.LogUtils;
//import cn.incongress.xuehuiyi.utils.StringUtils;
//import cn.jpush.android.api.JPushInterface;
//
///**
// * 说明：登录界面
// * 作者：Jacky
// * 时间：2015-08-20
// */
//public class LoginActivity extends BaseActivity {
//
//    private EditText mEtPhoneNumber;
//    private EditText mEtPhonePassword;
//    private Button mBtRegister;
//    private Button mBtLogin;
//    private TextView mTvForgetPassword;
//    private XhyAppServiceImp mAppService;
//    private LinearLayout mLlLook;
//    private TextView mTvVersion;
//
//    private String mUserPhoneNum;
//    private String mUserPassword;
//
//    private ImageUploadBean mImageBean;
//
//    @Override
//    protected void initializeData(Bundle savedInstanceState) {
//        mAppService = XhyAppServiceImp.getXhyServiceInstance(this);
//    }
//
//    @Override
//    protected void setContentView(Bundle savedInstanceState) {
//        setContentView(R.layout.activity_login);
//        XhyApplication.getInstance().addActivity(LoginActivity.this);
//    }
//
//    @Override
//    protected void initializeViews(Bundle savedInstanceState) {
//        mEtPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
//        mEtPhonePassword = (EditText) findViewById(R.id.et_phone_password);
//        mBtRegister = (Button) findViewById(R.id.bt_register);
//        mBtLogin = (Button) findViewById(R.id.bt_login);
//        mTvForgetPassword = (TextView) findViewById(R.id.tv_forget_password);
//        mLlLook = (LinearLayout)findViewById(R.id.ll_look_look);
//        mTvVersion = getViewById(R.id.tv_version);
//    }
//
//    @Override
//    protected void initializeEvents() {
//
//        //获取版本号
//        mTvVersion.setText(getVersion());
//
//        mEtPhoneNumber.setOnClickListener(this);
//        mEtPhonePassword.setOnClickListener(this);
//        mBtRegister.setOnClickListener(this);
//        mBtLogin.setOnClickListener(this);
//        mTvForgetPassword.setOnClickListener(this);
//        mLlLook.setOnClickListener(this);
//
//        //查看是否登陆，然后进入主界面或者不进入
//        //将UserId保存到本地
//        String logInId = mSharedPreference.getString(Constant.USER_USER_ID, "-1");
//        if(logInId.equals("-1")) {
//            //不做任何的操作
//        }else {
//            //登陆过则直接登陆，并设置用户id
//            XhyApplication.setUserId(Integer.parseInt(logInId));
//            LoginActivity.this.finish();
//            startActivity(MainActivity_Swipe.class, null);
//        }
//    }
//
//    @Override
//    public void onClick(View view) {
//        int target = view.getId();
//        if (target == R.id.et_phone_number) {
//        } else if (target == R.id.et_phone_password) {
//        } else if (target == R.id.bt_register) {
//            this.startActivity(RegisterActivity.class, null);
//        } else if (target == R.id.bt_login) {
//            if (StringUtils.isMobileNO(mEtPhoneNumber.getText().toString().trim())) {
//                if (StringUtils.isEmpty(mEtPhonePassword.getText().toString().trim())) {
//                    showMySingleDialog(getString(R.string.dialog_title), getString(R.string.dialog_msg_no_phone_password), new DialogSingle.DialogListener() {
//                        @Override
//                        public void ok() {
//                        }
//                    });
//                } else {
//                    //执行登陆操作
//                    mUserPhoneNum = mEtPhoneNumber.getText().toString().trim();
//                    mUserPassword = mEtPhonePassword.getText().toString().trim();
//
//                    mAppService.doGetXhyClientLogin(Integer.valueOf(Constant.CLIENT_TYPE), mUserPhoneNum, mUserPassword, new SimpleDataCallback<JSONObject>() {
//                        @Override
//                        public void onStart() {
//                            super.onStart();
//                            showSimpleLoadDialog("正在登录..");
//                        }
//
//                        @Override
//                        public void onError(Exception e) {
//                            super.onError(e);
//                        }
//
//                        @Override
//                        public void onComplete() {
//                            super.onComplete();
//                            dismissSimpleLoadDialog();
//                        }
//
//                        @Override
//                        public void onFinish(JSONObject obj, DataFlag flag) throws Exception {
//                            super.onFinish(obj, flag);
//                            if (obj.getInt("state") == 1) {
//                                String userId = obj.getString("userId");
//
//                                //TODO 将用户手机型号上传到服务器
//                                String phoneInfo = getPhontInfo();
//                                getXhyAppServiceImp().doSavePhoneType(Integer.parseInt(userId), phoneInfo, null);
//
//                                //将用户信息保存到本地进行存储
//                                UserBean bean = new UserBean();
//                                bean = JsonPaserTools.getUserInfo(obj);
//
//                                LogUtils.println("doGetXhyClientLogin==="+ obj.toString());
//                                //将UserId保存到本地
//                                SharedPreferences.Editor editor = mSharedPreference.edit();
//                                editor.putBoolean(Constant.USER_CLIENT_MODE, false);
//                                editor.putString(Constant.USER_USER_ID, URLDecoder.decode(bean.getUserId() + "", Constant.ENCODING_UTF8));
//                                editor.putString(Constant.USER_NAME, URLDecoder.decode(bean.getTrueName(), Constant.ENCODING_UTF8));
//                                editor.putString(Constant.USER_NICKNAME,bean.getNickName());
//                                editor.putString(Constant.USER_IMAG_URL, URLDecoder.decode(bean.getImgUrl(), Constant.ENCODING_UTF8));
//                                editor.putString(Constant.USER_SEX, URLDecoder.decode(bean.getSex(), Constant.ENCODING_UTF8));
//                                editor.putString(Constant.USER_MOBILE, URLDecoder.decode(bean.getMobilePhone(), Constant.ENCODING_UTF8));
//                                editor.putString(Constant.USER_EMAIL, URLDecoder.decode(bean.getEmail(), Constant.ENCODING_UTF8));
//                                editor.putString(Constant.USER_KESHI, URLDecoder.decode(bean.getKeshi(), Constant.ENCODING_UTF8));
//                                editor.putString(Constant.USER_ZHICHENG, URLDecoder.decode(bean.getZhicheng(), Constant.ENCODING_UTF8));
//                                editor.putString(Constant.USER_PROVINCE_ID, URLDecoder.decode(bean.getProvince(), Constant.ENCODING_UTF8));
//                                editor.putString(Constant.USER_PROVINCE_NAME, URLDecoder.decode(bean.getProvinceName(), Constant.ENCODING_UTF8));
//                                editor.putString(Constant.USER_CITY_ID, URLDecoder.decode(bean.getCity(), Constant.ENCODING_UTF8));
//                                editor.putString(Constant.USER_CITY_NAME, URLDecoder.decode(bean.getCityName(), Constant.ENCODING_UTF8));
//                                editor.putString(Constant.USER_HOSPITAL_ID, URLDecoder.decode(bean.getHospital(), Constant.ENCODING_UTF8));
//                                editor.putString(Constant.USER_HOSPITAL_NAME, URLDecoder.decode(bean.getHospitalName(), Constant.ENCODING_UTF8));
//                                editor.putString(Constant.USER_TYPE, URLDecoder.decode(bean.getType(), Constant.ENCODING_UTF8));
//                                editor.putString(Constant.USER_UNIV, URLDecoder.decode(bean.getUniv(), Constant.ENCODING_UTF8));
//                                editor.putString(Constant.USER_UNIV_ID,bean.getUnivId());
//                                editor.putString(Constant.USER_UNIV_YEAR,bean.getUnivYear());
//                                editor.putBoolean(Constant.IS_FIRST_TO_MAIN, true);
//
//                                //判断资料是否完整
//                                if(StringUtils.isEmpty(bean.getTrueName()+"") ||
//                                        StringUtils.isEmpty(bean.getCityName()) ||
//                                        StringUtils.isEmpty(bean.getHospitalName()) ||
//                                        StringUtils.isEmpty(bean.getKeshi()) ||
//                                        StringUtils.isEmpty(bean.getZhicheng())) {
//                                    editor.putBoolean(Constant.USER_INFO_COMPLETE, false);
//                                }else {
//                                    editor.putBoolean(Constant.USER_INFO_COMPLETE, true);
//                                }
//                                editor.apply();
//
//                                if(mSharedPreference.getBoolean(Constant.INTRO_DUCTION_FIRST, true)) {
//                                    startActivity(IntroductionActivity.class, null);
//                                }else {
//                                    startActivity(MainActivity_Swipe.class, null);
//                                }
//
//                                XhyApplication.setUserId(Integer.parseInt(userId));
//                                LoginActivity.this.finish();
//                            } else {
////                                showDialog("登录失败");
//                                showMySingleDialog(getString(R.string.dialog_title), getString(R.string.dialog_msg_error_phone_password), new DialogSingle.DialogListener() {
//                                    @Override
//                                    public void ok() {
//                                    }
//                                });
//                            }
//                        }
//                    });
//                }
//            } else {
////                showDialog("请输入正确的手机号码");
//                showMySingleDialog(getString(R.string.dialog_title), getString(R.string.dialog_msg_no_phone_password), new DialogSingle.DialogListener() {
//                    @Override
//                    public void ok() {
//                    }
//                });
//            }
//        } else if (target == R.id.tv_forget_password) {
//            //忘记密码，用于重置密码
//            Bundle bundle = new Bundle();
//            bundle.putBoolean("isForget", true);
//            this.startActivity(RegisterActivity.class, bundle);
//        }else if(target == R.id.ll_look_look) {
//            this.finish();
//            startActivity(MainActivity_Swipe.class, null);
//            SharedPreferences.Editor editor = mSharedPreference.edit();
//            editor.putBoolean(Constant.USER_CLIENT_MODE, true);
//            editor.apply();
//            XhyApplication.setUserId(XhyApplication.getInstance().ClientMode);
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        JPushInterface.onResume(this);
//        MobclickAgent.onResume(this);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        JPushInterface.onPause(this);
//        MobclickAgent.onPause(this);
//    }
//}
