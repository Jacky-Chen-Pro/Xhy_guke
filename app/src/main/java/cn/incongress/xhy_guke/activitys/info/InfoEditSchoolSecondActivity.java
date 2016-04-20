package cn.incongress.xhy_guke.activitys.info;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.incongress.xuehuiyi.base.Constant;
import cn.incongress.xuehuiyi.bean.SchoolBean;
import cn.incongress.xuehuiyi.bean.SchoolProvinceBean;
import cn.incongress.xuehuiyi.fragment.ActionBarFragment;
import cn.incongress.xuehuiyi.utils.AssetsDBUtils;
import cn.incongress.xuehuiyi.utils.LogUtils;
import cn.trinea.android.common.util.ToastUtils;

public class InfoEditSchoolSecondActivity extends BaseActivity {
    private ListView mListView;
    private boolean isEdit = false;

    private String mProvinceName;
    private String mProvinceId;

    private SchoolAdapter mAdapter;

    private EditText mEtSearch;
    private TextView mTvCancel;

    private List<SchoolBean> mBeans = new ArrayList<SchoolBean>();

    private ActionBarFragment mActionBarFragment;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_info_school_second);

        Bundle bundle = getIntent().getExtras();
        mProvinceName = bundle.getString("provinceName");
        mProvinceId = String.valueOf(bundle.getInt("provinceId"));

        mActionBarFragment = ActionBarFragment.getInstance(ActionBarFragment.ARG_TYPE_NORMAL);
        FragmentTransaction tans = getFragmentManager().beginTransaction();
        tans.add(R.id.include_title_top, mActionBarFragment, "title");
        tans.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mActionBarFragment.setMiddleText(R.string.school);
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        mActionBarFragment.setMiddleText(mProvinceName);
        mListView = getViewById(R.id.lv_schools);
    }

    @Override
    protected void initializeData(Bundle savedInstanceState) {
        mBeans = AssetsDBUtils.getAllSchoolByProvinceId(mProvinceId);
        mAdapter = new SchoolAdapter();

        mListView.setAdapter(mAdapter);
        mListView.setFastScrollEnabled(true);
    }

    @Override
    protected void initializeEvents() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                i--;
                LogUtils.v("school query", "provinceId :" + mBeans.get(i).getSchoolProvinceId() + ",provinceName:" + mBeans.get(i).getSchoolName());

                SharedPreferences.Editor editor = mSharedPreference.edit();
                editor.putString(Constant.USER_UNIV, mBeans.get(i).getSchoolName());
                editor.putString(Constant.USER_UNIV_ID, mBeans.get(i).getSchoolId()+"");
                 editor.commit();
                InfoEditSchoolSecondActivity.this.setResult(RESULT_OK);
                InfoEditSchoolSecondActivity.this.finish();
            }
        });

        View view = getLayoutInflater().inflate(R.layout.search_view, null);
        mEtSearch = (EditText)view.findViewById(R.id.et_hospital);
        mTvCancel = (TextView)view.findViewById(R.id.tv_cancel);
        mListView.addHeaderView(view);

        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                mBeans = AssetsDBUtils.getAllSchoolByProvinceIdAndName(mProvinceId, mEtSearch.getText().toString());
                mAdapter.notifyDataSetChanged();

                if(mBeans == null || mBeans.size() == 0) {
                    ToastUtils.show(InfoEditSchoolSecondActivity.this, getString(R.string.no_hospital_find));
                }
            }
        });

        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mEtSearch.getWindowToken(), 0);

                mEtSearch.clearFocus();
                mEtSearch.setText("");
            }
        });
    }

    @Override
    public void onClick(View v) {
        int target = v.getId();

        if(target == R.id.iv_left_icon_back) {
            setResult(RESULT_CANCELED);
            this.finish();
        }
    }

    private class SchoolAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mBeans.size();
        }

        @Override
        public SchoolBean getItem(int i) {
            return mBeans.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if(view == null) {
                view = LayoutInflater.from(InfoEditSchoolSecondActivity.this).inflate(R.layout.item_province,null);
                holder = new ViewHolder();
                holder.tvSchoolName = (TextView)view.findViewById(R.id.tv_province_name);
                view.setTag(holder);
            }else {
                holder = (ViewHolder)view.getTag();
            }

            SchoolBean bean = mBeans.get(i);
            holder.tvSchoolName.setText(bean.getSchoolName());
            return view;
        }

        class ViewHolder {
            TextView tvSchoolName;
        }

    }


}
