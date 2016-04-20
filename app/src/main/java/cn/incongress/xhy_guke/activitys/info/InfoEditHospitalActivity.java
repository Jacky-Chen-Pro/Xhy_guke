package cn.incongress.xhy_guke.activitys.info;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import cn.incongress.xuehuiyi.base.Constant;
import cn.incongress.xuehuiyi.bean.HospitalBean;
import cn.incongress.xuehuiyi.fragment.ActionBarFragment;
import cn.incongress.xuehuiyi.utils.AssetsDBUtils;
import cn.trinea.android.common.util.ToastUtils;

public class InfoEditHospitalActivity extends BaseActivity {
    private List<HospitalBean> mBeans = new ArrayList<HospitalBean>();
    private HospitalAdapter mAdapter;

    private ListView mListView;
    private String mCityId = "-1";
    private String mCurrentHospitalId = "7";
    private String mCurrentHospitalName = "";
    private boolean isEdit = false;

    private EditText mEtSearch;
    private TextView mTvCancel;

    private ActionBarFragment mActionBarFragment;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_info_hospital);

        Bundle bundle = getIntent().getBundleExtra("bundle");
        mCityId = bundle.getString(Constant.USER_CITY_ID, "-1");
        mCurrentHospitalId = mSharedPreference.getString(Constant.USER_HOSPITAL_ID, "2");

        mActionBarFragment = ActionBarFragment.getInstance(ActionBarFragment.ARG_TYPE_PERSON_CENTER);
        FragmentTransaction tans = getFragmentManager().beginTransaction();
        tans.add(R.id.include_title_top,mActionBarFragment,"title");
        tans.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mActionBarFragment.setMiddleText(R.string.info_hospital);
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        mListView = getViewById(R.id.lv_hospitals);
        if(!(mCityId.equals("-1"))) {
            mBeans = AssetsDBUtils.getHospitalsByCityId(Integer.parseInt(mCityId));
        }

        View view = getLayoutInflater().inflate(R.layout.search_view, null);
        mEtSearch = (EditText)view.findViewById(R.id.et_hospital);
        mTvCancel = (TextView)view.findViewById(R.id.tv_cancel);
        mListView.addHeaderView(view);

        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mBeans = AssetsDBUtils.getHospitalByNameAndId(Integer.parseInt(mCityId), mEtSearch.getText().toString());
                mAdapter.notifyDataSetChanged();

                if(mBeans == null || mBeans.size() == 0) {
                    ToastUtils.show(InfoEditHospitalActivity.this, getString(R.string.no_hospital_find));
                }

            }
        });

        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mEtSearch.getWindowToken(), 0) ;

                mEtSearch.clearFocus();
                mEtSearch.setText("");
            }
        });
    }

    @Override
    protected void initializeData(Bundle savedInstanceState) {
        mAdapter = new HospitalAdapter();

        mListView.setAdapter(mAdapter);
        mListView.setFastScrollEnabled(true);
    }

    @Override
    protected void initializeEvents() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                i = i-1;

                isEdit = true;
                mCurrentHospitalName = mBeans.get(i).getName();
                mCurrentHospitalId = mBeans.get(i).getHospitalId() +"";
                mAdapter.notifyDataSetChanged();

                if(isEdit) {
                    Intent intent = new Intent();
                    intent.putExtra(Constant.USER_HOSPITAL_ID, mCurrentHospitalId);
                    intent.putExtra(Constant.USER_HOSPITAL_NAME, mCurrentHospitalName);
                    intent.putExtra(Constant.USER_CITY_ID, mCityId);
                    InfoEditHospitalActivity.this.setResult(0, intent);

                    SharedPreferences.Editor editor = mSharedPreference.edit();
                    editor.putString(Constant.USER_HOSPITAL_ID, mCurrentHospitalId+"");
                    editor.putString(Constant.USER_HOSPITAL_NAME, mCurrentHospitalName+"");
                    editor.commit();

                    InfoEditHospitalActivity.this.finish();
                }else {
                    InfoEditHospitalActivity.this.finish();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {  }

    private class HospitalAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mBeans.size();
        }

        @Override
        public HospitalBean getItem(int i) {
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
                view = LayoutInflater.from(InfoEditHospitalActivity.this).inflate(R.layout.item_city,null);
                holder = new ViewHolder();
                holder.hospitalName = (TextView)view.findViewById(R.id.tv_name);
                holder.choose = (ImageView)view.findViewById(R.id.iv_choose);
                view.setTag(holder);
            }else {
                holder = (ViewHolder)view.getTag();
            }

            HospitalBean bean = mBeans.get(i);
            holder.hospitalName.setText(bean.getName());

            if(mCurrentHospitalId.equals(bean.getHospitalId()+"")) {
                holder.choose.setVisibility(View.VISIBLE);
            }else {
                holder.choose.setVisibility(View.GONE);
            }

            return view;
        }

    }

    class ViewHolder {
        private TextView hospitalName;
        private ImageView choose;
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
