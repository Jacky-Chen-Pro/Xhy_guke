package cn.incongress.xhy_guke.activitys;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.activitys.info.InfoEditEmailActivity;
import cn.incongress.xhy_guke.activitys.info.InfoEditHospitalActivity;
import cn.incongress.xhy_guke.activitys.info.InfoEditKeshiActivity;
import cn.incongress.xhy_guke.activitys.info.InfoEditLocationActivity;
import cn.incongress.xhy_guke.activitys.info.InfoEditNameActivity;
import cn.incongress.xhy_guke.activitys.info.InfoEditNicknameActivity;
import cn.incongress.xhy_guke.activitys.info.InfoEditSchoolActivity;
import cn.incongress.xhy_guke.activitys.info.InfoEditSexualActivity;
import cn.incongress.xhy_guke.activitys.info.InfoEditzhichengActivity;
import cn.incongress.xhy_guke.base.BaseActivity;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.base.Constantss;
import cn.incongress.xhy_guke.bean.HospitalBean;
import cn.incongress.xhy_guke.uis.CircleImageView;
import cn.incongress.xhy_guke.uis.popup.PhotoPopupWindow;
import cn.incongress.xhy_guke.utils.AssetsDBUtils;
import cn.incongress.xhy_guke.utils.StringUtils;
import cn.trinea.android.common.util.ToastUtils;
import okhttp3.Call;

/**
 * 说明：个人中心
 * 作者：Jacky
 * 时间：2015-08-20
 */
public class PersonCenterActivity extends BaseActivity implements View.OnClickListener{

    private static final int CHOOSE_PHOTO = 0x0001;
    private static final int TAKE_PHOTO = 0x0002;
    public static final int GET_CITYID = 0x0003;
    public static final int GET_HOSPITAL = 0x0004;
    public static final int GET_NAME = 0x0005;
    public static final int GET_SEX = 0x0006;
    public static final int GET_EMAIL = 0x0007;
    public static final int GET_KESHI = 0x008;
    public static final int GET_ZHICHENG = 0x009;
    public static final int GET_SCHOOL = 0x010;
    public static final int GET_NICKNAME = 0x011;

    public boolean mGetPicSuccess = false;

    private LinearLayout mLlPersonIcon;
    private CircleImageView mCivHeadIcon;

    private int mProvinceId = -1;
    private String mCityName = "";
    private String mProviceName = "";
    private String mCityId = "-1";
    private String mHospitalId = "-1";
    private String mHospitalName = "";
    private String mMobilePhone = "";
    private String mUniv = "";
    private String mUnivId = "";
    private String mUnivYear = "";

    private Bitmap bmp;
    private String fileName;
    private String mDownloadHeadIconUrl;

    private LinearLayout mLlName;
    private LinearLayout mLlSexual;
    private LinearLayout mLlEmail;
    private LinearLayout mLlLocation;
    private LinearLayout mLlHospital;
    private LinearLayout mLlKeshi;
    private LinearLayout mLlZhicheng;
    private LinearLayout mLlSchool;
    private LinearLayout mLlEducationTime;
    private LinearLayout mLlNickName;

    /** 照片选择 **/
    private PhotoPopupWindow mPhotoPopupWindow;

    /** 时间选择 **/
    private PopupWindow mTimePopupWindow;
    private ArrayList<String> mDataList = new ArrayList<String>();

    private TextView mTvArea;
    private TextView mTvHospital;
    private TextView mTvName;
    private TextView mTvSex;
    private TextView mTvMobile;
    private TextView mTvEmail;
    private TextView mTvKeshi;
    private TextView mTvZhiCheng;
    private TextView mTvSchool;
    private TextView mTvSchoolEducation;
    private TextView mTvNickName;

    private List<HospitalBean> mHospitalBeans = new ArrayList<HospitalBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar(getString(R.string.personal_center), true, false, -1, null, false, -1, null);

        mLlPersonIcon = getViewById(R.id.ll_person_icon);
        mCivHeadIcon = getViewById(R.id.civ_user_icon);
        mLlSexual = getViewById(R.id.ll_sexual);
        mLlEmail = getViewById(R.id.ll_email);
        mLlName = getViewById(R.id.ll_name);
        mLlLocation = getViewById(R.id.ll_location);
        mLlHospital = getViewById(R.id.ll_hospital);
        mLlKeshi = getViewById(R.id.ll_keshi);
        mLlZhicheng = getViewById(R.id.ll_zhicheng);
        mLlSchool = getViewById(R.id.ll_education_school);
        mLlEducationTime = getViewById(R.id.ll_education_time);
        mLlNickName = getViewById(R.id.ll_nickname);

        mTvArea = getViewById(R.id.tv_area);
        mTvHospital = getViewById(R.id.tv_hospital);
        mTvName = getViewById(R.id.tv_name);
        mTvSex = getViewById(R.id.tv_sex);
        mTvMobile = getViewById(R.id.tv_mobile);
        mTvEmail = getViewById(R.id.tv_email);
        mTvKeshi = getViewById(R.id.tv_keshi);
        mTvZhiCheng = getViewById(R.id.tv_zhicheng);
        mTvSchool = getViewById(R.id.tv_school);
        mTvSchoolEducation = getViewById(R.id.tv_school_education);
        mTvNickName = getViewById(R.id.tv_nickname);

        initializeData();
    }

    /**
     * 初始化数据
     */
    private void initializeData() {
        for(int i=1950; i<2051; i++) {
            mDataList.add(String.valueOf(i));
        }

//        fileName = "userHeadIcon" + XhyApplication.getUserId() + ".jpg";
        mTvName.setText(StringUtils.isEmpty(getSPStringValue(Constants.USER_TRUE_NAME))?getString(R.string.info_no_setting):getSPStringValue(Constants.USER_TRUE_NAME));
        mTvNickName.setText(StringUtils.isEmpty(getSPStringValue(Constants.USER_NICKNAME))?getString(R.string.info_no_setting):getSPStringValue(Constants.USER_NICKNAME));

        String sex = getSPStringValue(Constants.USER_SEX);

        if (sex.equals("1")) {
            mTvSex.setText(R.string.male);
        } else if (sex.equals("0")) {
            mTvSex.setText(R.string.female);
        } else {
            mTvSex.setText(R.string.male);
        }

        mMobilePhone = getSPStringValue(Constants.USER_MOBILE);
        mTvMobile.setText(mMobilePhone);

        mHospitalName = getSPStringValue(Constants.USER_HOSPITAL_NAME);
        mTvHospital.setText(StringUtils.isEmpty(mHospitalName)?getString(R.string.info_no_setting):mHospitalName);

        mCityName = getSPStringValue(Constants.USER_CITY_NAME);
        mProviceName = getSPStringValue(Constants.USER_PROVINCE_NAME);
        mTvArea.setText(StringUtils.isEmpty(mCityName+mProviceName)?getString(R.string.info_no_setting) : mProviceName + getString(R.string.info_blank) + mCityName);

        mTvKeshi.setText(StringUtils.isEmpty(getSPStringValue(Constants.USER_KESHI))?getString(R.string.info_no_setting):getSPStringValue(Constants.USER_KESHI));
        mTvZhiCheng.setText(StringUtils.isEmpty(getSPStringValue(Constants.USER_ZHICHENG))?getString(R.string.info_no_setting):getSPStringValue(Constants.USER_ZHICHENG));
        mTvEmail.setText(StringUtils.isEmpty(getSPStringValue(Constants.USER_EMAIL))?getString(R.string.info_no_setting):getSPStringValue(Constants.USER_EMAIL));
        mTvSchool.setText(StringUtils.isEmpty(getSPStringValue(Constants.USER_UNIV))?getString(R.string.info_no_setting):getSPStringValue(Constants.USER_UNIV));
        mTvSchoolEducation.setText(StringUtils.isEmpty(getSPStringValue(Constants.USER_UNIV_YEAR)) ? getString(R.string.info_no_setting) : getSPStringValue(Constants.USER_UNIV_YEAR));

        mDownloadHeadIconUrl = getSPStringValue(Constants.USER_PIC);
        Glide.with(PersonCenterActivity.this).load(mDownloadHeadIconUrl).placeholder(R.mipmap.item_background_professor_default).into(mCivHeadIcon);

        mCityId  = getSPStringValue(Constants.USER_CITY_ID);
        if(mCityId.equals(""))
            mCityId = "-1";

        mHospitalBeans = AssetsDBUtils.getHospitalsByCityId(Integer.parseInt(mCityId));

        mLlPersonIcon.setOnClickListener(this);
        mLlName.setOnClickListener(this);
        mLlNickName.setOnClickListener(this);
        mLlSexual.setOnClickListener(this);
        mLlEmail.setOnClickListener(this);
        mLlLocation.setOnClickListener(this);
        mLlHospital.setOnClickListener(this);
        mLlKeshi.setOnClickListener(this);
        mLlZhicheng.setOnClickListener(this);
        mLlSchool.setOnClickListener(this);
        mLlEducationTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int target = v.getId();
        if (target == R.id.ll_person_icon) {
            mPhotoPopupWindow = new PhotoPopupWindow(PersonCenterActivity.this, new StringCallback() {
                @Override
                public void onError(Call call, Exception e) {

                }

                @Override
                public void onResponse(String response) {

                }
            })
        } else if (target == R.id.ll_name) {
            Intent intent = new Intent();
            intent.setClass(PersonCenterActivity.this, InfoEditNameActivity.class);
            startActivityForResult(intent, GET_NAME);
        } else if (target == R.id.ll_sexual) {
            Intent intent = new Intent();
            intent.setClass(PersonCenterActivity.this, InfoEditSexualActivity.class);
            startActivityForResult(intent, GET_SEX);
        } else if (target == R.id.ll_email) {
            Intent intent = new Intent();
            intent.setClass(PersonCenterActivity.this, InfoEditEmailActivity.class);
            startActivityForResult(intent, GET_EMAIL);
        } else if (target == R.id.ll_location) {
            Intent intent = new Intent();
            intent.setClass(PersonCenterActivity.this, InfoEditLocationActivity.class);
            startActivityForResult(intent, GET_CITYID);
        } else if (target == R.id.ll_hospital) {
//            mCityId = getSPStringValue(Constants.USER_CITY_ID, "-1");
            if (mHospitalBeans.size() == 0 && mCityId.equals("-1")) {
                ToastUtils.show(PersonCenterActivity.this, R.string.choose_area_first, Toast.LENGTH_SHORT);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.USER_CITY_ID, mCityId);
                Intent intent = new Intent();
                intent.setClass(PersonCenterActivity.this, InfoEditHospitalActivity.class);
                intent.putExtra("bundle", bundle);
                startActivityForResult(intent, GET_HOSPITAL);
            }
        } else if (target == R.id.ll_keshi) {
            Intent intent = new Intent();
            intent.setClass(PersonCenterActivity.this, InfoEditKeshiActivity.class);
            startActivityForResult(intent, GET_KESHI);
        } else if (target == R.id.ll_zhicheng) {
            Intent intent = new Intent();
            intent.setClass(PersonCenterActivity.this, InfoEditzhichengActivity.class);
            startActivityForResult(intent, GET_ZHICHENG);
        } else if(target == R.id.ll_education_school) {
            Intent intent = new Intent();
            intent.setClass(PersonCenterActivity.this, InfoEditSchoolActivity.class);
            startActivityForResult(intent, GET_SCHOOL);
        }else if(target == R.id.ll_education_time) {
            //弹出时间滚轮选择器
            mTimePopupWindow.showAtLocation(PersonCenterActivity.this.findViewById(R.id.ll_whole_area),
                    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        }else if(target == R.id.ll_nickname) {
            Intent intent = new Intent();
            intent.setClass(PersonCenterActivity.this, InfoEditNicknameActivity.class);
            startActivityForResult(intent, GET_NICKNAME);
        }
    }

}
