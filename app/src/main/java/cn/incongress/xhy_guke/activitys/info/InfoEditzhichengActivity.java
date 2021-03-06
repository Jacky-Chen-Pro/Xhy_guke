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

/**
 * Created by Administrator on 2015/7/8.
 */
public class InfoEditzhichengActivity extends BaseActivity {
    private EditText mEtZhiCheng;
    private ImageView mIvDelete;

    private ActionBarFragment mActionBarFragment;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_info_zhicheng);

        mActionBarFragment = ActionBarFragment.getInstance(ActionBarFragment.ARG_TYPE_PERSON_CENTER);
        FragmentTransaction tans = getFragmentManager().beginTransaction();
        tans.add(R.id.include_title_top, mActionBarFragment, "title");
        tans.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mActionBarFragment.setMiddleText(R.string.info_zhicheng);
        mActionBarFragment.setRightTextAndListener(getString(R.string.personal_info_save), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StringUtils.isEmpty(mEtZhiCheng.getText().toString())) {
                    ToastUtils.show(InfoEditzhichengActivity.this, R.string.zhcheng_can_not_empty);
                } else {
                    SharedPreferences.Editor editor = mSharedPreference.edit();
                    editor.putString(Constant.USER_ZHICHENG, mEtZhiCheng.getText().toString());
                    editor.commit();
                    InfoEditzhichengActivity.this.finish();
                }
            }
        });
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        mEtZhiCheng = getViewById(R.id.et_zhicheng);
        mIvDelete = getViewById(R.id.iv_delete);
    }

    @Override
    protected void initializeData(Bundle savedInstanceState) {
        mEtZhiCheng.setText(mSharedPreference.getString(Constant.USER_ZHICHENG,""));
        mEtZhiCheng.setSelection(mEtZhiCheng.getText().length());
    }

    @Override
    protected void initializeEvents() {
        mIvDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int target = v.getId();

       if(target == R.id.iv_delete) {
            mEtZhiCheng.setText(StringUtils.EMPTY_STRING);
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
