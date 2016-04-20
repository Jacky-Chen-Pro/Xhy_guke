package cn.incongress.xhy_guke.activitys.info;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.umeng.analytics.MobclickAgent;

import cn.incongress.xuehuiyi.base.Constant;
import cn.incongress.xuehuiyi.fragment.ActionBarFragment;

/**
 * Created by Administrator on 2015/7/8.
 */
public class InfoEditSexualActivity extends BaseActivity {
    private LinearLayout mLlMale;
    private LinearLayout mLlFemale;
    private ImageView mIvMale;
    private ImageView mIvFemale;

    private ActionBarFragment mActionBarFragment;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_info_sexual);

        mActionBarFragment = ActionBarFragment.getInstance(ActionBarFragment.ARG_TYPE_PERSON_CENTER);
        FragmentTransaction tans = getFragmentManager().beginTransaction();
        tans.add(R.id.include_title_top, mActionBarFragment, "title");
        tans.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mActionBarFragment.setMiddleText(R.string.info_name);
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        mLlMale = getViewById(R.id.ll_male);
        mLlFemale = getViewById(R.id.ll_female);
        mIvMale = getViewById(R.id.iv_male);
        mIvFemale = getViewById(R.id.iv_female);
    }

    @Override
    protected void initializeData(Bundle savedInstanceState) {

        String sex = mSharedPreference.getString(Constant.USER_SEX,"");
        if(sex.equals("1")) {
            mIvMale.setVisibility(View.VISIBLE);
            mIvFemale.setVisibility(View.GONE);
        }else if(sex.equals("0")) {
            mIvMale.setVisibility(View.GONE);
            mIvFemale.setVisibility(View.VISIBLE);
        }else {
            mIvMale.setVisibility(View.VISIBLE);
            mIvFemale.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initializeEvents() {
        mLlMale.setOnClickListener(this);
        mLlFemale.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int target = v.getId();

        if(target == R.id.ll_male) {
            mIvMale.setVisibility(View.VISIBLE);
            mIvFemale.setVisibility(View.GONE);
            SharedPreferences.Editor editor = mSharedPreference.edit();
            editor.putString(Constant.USER_SEX,"1");
            editor.commit();
            this.finish();
        }else if(target == R.id.ll_female) {
            mIvFemale.setVisibility(View.VISIBLE);
            mIvMale.setVisibility(View.GONE);
            SharedPreferences.Editor editor = mSharedPreference.edit();
            editor.putString(Constant.USER_SEX, "0");
            editor.commit();
            this.finish();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
