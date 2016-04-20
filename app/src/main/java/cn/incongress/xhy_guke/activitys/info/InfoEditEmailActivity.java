package cn.incongress.xhy_guke.activitys.info;

import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.umeng.analytics.MobclickAgent;

import cn.incongress.xuehuiyi.base.Constant;
import cn.incongress.xuehuiyi.fragment.ActionBarFragment;
import cn.incongress.xuehuiyi.utils.StringUtils;
import cn.trinea.android.common.util.ToastUtils;

public class InfoEditEmailActivity extends BaseActivity {
    private EditText mEtEmail;
    private ImageView mIvDelete;

    private ActionBarFragment mActionBarFragment;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_info_email);

        mActionBarFragment = ActionBarFragment.getInstance(ActionBarFragment.ARG_TYPE_PERSON_CENTER);
        FragmentTransaction tans = getFragmentManager().beginTransaction();
        tans.add(R.id.include_title_top,mActionBarFragment,"title");
        tans.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mActionBarFragment.setMiddleText(R.string.info_email);
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(StringUtils.isEmpty(mEtEmail.getText().toString().trim())) {
                    ToastUtils.show(InfoEditEmailActivity.this, R.string.email_can_not_empty);
                }else {
                    if(checkEmail(mEtEmail.getText().toString().trim())) {
                        SharedPreferences.Editor editor = mSharedPreference.edit();
                        editor.putString(Constant.USER_EMAIL, mEtEmail.getText().toString());
                        editor.commit();
                        InfoEditEmailActivity.this.finish();
                    }else {
                        ToastUtils.show(InfoEditEmailActivity.this,R.string.email_wrong_format);
                    }
                }
            }
        };

        mActionBarFragment.setRightTextAndListener(getString(R.string.personal_info_save), onClickListener);
        mEtEmail = getViewById(R.id.et_email);
        mIvDelete = getViewById(R.id.iv_delete);
    }

    @Override
    protected void initializeData(Bundle savedInstanceState) {
        mEtEmail.setText(mSharedPreference.getString(Constant.USER_EMAIL,""));
        mEtEmail.setSelection(mEtEmail.getText().length());
    }

    @Override
    protected void initializeEvents() {
        mIvDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int target = v.getId();

        if(target == R.id.iv_delete) {
            mEtEmail.setText(StringUtils.EMPTY_STRING);
        }
    }
}
