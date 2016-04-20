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
import cn.incongress.xuehuiyi.bean.ProvinceBean;
import cn.incongress.xuehuiyi.bean.SchoolProvinceBean;
import cn.incongress.xuehuiyi.fragment.ActionBarFragment;
import cn.incongress.xuehuiyi.utils.AssetsDBUtils;
import cn.incongress.xuehuiyi.utils.LogUtils;
import cn.trinea.android.common.util.ToastUtils;

public class InfoEditSchoolActivity extends BaseActivity {
    private ListView mListView;
    private ProvinceAdapter mAdapter;

    public static final int REQUEST_CODE_SCHOOL = 0x9000;

    private List<SchoolProvinceBean> mBeans = new ArrayList<SchoolProvinceBean>();

    private ActionBarFragment mActionBarFragment;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_info_school);

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
        mListView = getViewById(R.id.lv_provinces);
    }

    @Override
    protected void initializeData(Bundle savedInstanceState) {
        mBeans = AssetsDBUtils.getAllSchoolProvince();
        mAdapter = new ProvinceAdapter();
//
        mListView.setAdapter(mAdapter);
        mListView.setFastScrollEnabled(true);
    }

    @Override
    protected void initializeEvents() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LogUtils.v("school query", "provinceId :" + mBeans.get(i).getSchoolProvinceId() + ",provinceName:" + mBeans.get(i).getSchoolProvinceName());

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("provinceName", mBeans.get(i).getSchoolProvinceName());
                bundle.putInt("provinceId", mBeans.get(i).getSchoolProvinceId());
                intent.putExtras(bundle);
                intent.setClass(InfoEditSchoolActivity.this,InfoEditSchoolSecondActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCHOOL);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int target = v.getId();
    }

    private class ProvinceAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mBeans.size();
        }

        @Override
        public SchoolProvinceBean getItem(int i) {
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
                view = LayoutInflater.from(InfoEditSchoolActivity.this).inflate(R.layout.item_province,null);
                holder = new ViewHolder();
                holder.tvProvinceName = (TextView)view.findViewById(R.id.tv_province_name);
                view.setTag(holder);
            }else {
                holder = (ViewHolder)view.getTag();
            }

            SchoolProvinceBean bean = mBeans.get(i);
            holder.tvProvinceName.setText(bean.getSchoolProvinceName());
            return view;
        }

        class ViewHolder {
            TextView tvProvinceName;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            if(requestCode == REQUEST_CODE_SCHOOL) {
                this.finish();
            }
        }else {

        }
    }
}
