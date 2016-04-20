package cn.incongress.xhy_guke.activitys.info;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import cn.incongress.xuehuiyi.base.Constant;
import cn.incongress.xuehuiyi.bean.CityBean;
import cn.incongress.xuehuiyi.fragment.ActionBarFragment;
import cn.incongress.xuehuiyi.uis.IndexableListView;
import cn.incongress.xuehuiyi.utils.AssetsDBUtils;
import cn.incongress.xuehuiyi.utils.StringMatcher;

/**
 * Created by Administrator on 2015/7/8.
 */
public class InfoEditLocationActivity extends BaseActivity {
    private List<CityBean> mBeans = new ArrayList<CityBean>();
    private CityAdapter mAdapter;


    private String mCurrentCityId = "2";
    private int mCurrentProvinceId = -1;
    private String mCurrentCityName = "";
    private boolean isEdit = false;

    private IndexableListView mListView;

    private ActionBarFragment mActionBarFragment;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_info_location);

        mActionBarFragment = ActionBarFragment.getInstance(ActionBarFragment.ARG_TYPE_NORMAL);
        FragmentTransaction tans = getFragmentManager().beginTransaction();
        tans.add(R.id.include_title_top, mActionBarFragment, "title");
        tans.commit();

        mCurrentCityId = mSharedPreference.getString("city", "2");
    }

    @Override
    protected void onStart() {
        super.onStart();
        mActionBarFragment.setMiddleText(R.string.info_location);
    }

    @Override
    protected void initializeViews(Bundle savedInstanceState) {
        mListView = getViewById(R.id.lv_city);
        mBeans = AssetsDBUtils.getAllCity();
    }

    @Override
    protected void initializeData(Bundle savedInstanceState) {
        mAdapter = new CityAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setFastScrollEnabled(true);
    }

    @Override
    protected void initializeEvents() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                isEdit = true;
                mCurrentCityId = mBeans.get(i).getCityId() + "";
                mCurrentProvinceId = mBeans.get(i).getProvinceId();
                mCurrentCityName = mBeans.get(i).getName();
                mAdapter.notifyDataSetChanged();

                if(isEdit) {
                    Intent intent = new Intent();
                    intent.putExtra(Constant.USER_PROVINCE_ID, mCurrentProvinceId);
                    intent.putExtra(Constant.USER_CITY_NAME, mCurrentCityName);
                    intent.putExtra(Constant.USER_CITY_ID, mCurrentCityId);
                    InfoEditLocationActivity.this.setResult(0, intent);
                    InfoEditLocationActivity.this.finish();
                }else {
                    InfoEditLocationActivity.this.finish();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int target = v.getId();

        if(target == R.id.iv_left_icon_back) {
            if(isEdit) {
                Intent intent = new Intent();
                intent.putExtra(Constant.USER_PROVINCE_ID, mCurrentProvinceId);
                intent.putExtra(Constant.USER_CITY_NAME, mCurrentCityName);
                intent.putExtra(Constant.USER_CITY_ID, mCurrentCityId);
                this.setResult(0, intent);
                this.finish();
            }else {
                this.finish();
            }
        }
    }

    private class CityAdapter extends BaseAdapter implements SectionIndexer {

        private String mSections = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        @Override
        public int getCount() {
            return mBeans.size();
        }

        @Override
        public String getItem(int i) {
            String s = mBeans.get(i).getPinyin().toUpperCase();
            return s;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if(view == null) {
                view = LayoutInflater.from(InfoEditLocationActivity.this).inflate(R.layout.item_city,null);
                holder = new ViewHolder();
                holder.cityName = (TextView)view.findViewById(R.id.tv_name);
                holder.choose = (ImageView)view.findViewById(R.id.iv_choose);
                view.setTag(holder);
            }else {
                holder = (ViewHolder)view.getTag();
            }

            CityBean bean = mBeans.get(i);
            holder.cityName.setText(bean.getName());
            if(mCurrentCityId.equals(String.valueOf(bean.getCityId()))) {
                holder.choose.setVisibility(View.VISIBLE);
            }else {
                holder.choose.setVisibility(View.GONE);
            }

            return view;
        }

        @Override
        public int getPositionForSection(int section) {
            // If there is no item for current section, previous section will be selected
            for (int i = section; i >= 0; i--) {
                for (int j = 0; j < getCount(); j++) {
                    if (i == 0) {
                        // For numeric section
                        for (int k = 0; k <= 9; k++) {
                            if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(k)))
                                return j;
                        }
                    } else {
                        if (StringMatcher.match(String.valueOf(getItem(j).charAt(0)), String.valueOf(mSections.charAt(i))))
                            return j;
                    }
                }
            }
            return 0;
        }

        @Override
        public int getSectionForPosition(int position) {
            return 0;
        }

        @Override
        public Object[] getSections() {
            String[] sections = new String[mSections.length()];
            for (int i = 0; i < mSections.length(); i++)
                sections[i] = String.valueOf(mSections.charAt(i));
            return sections;
        }

    }

    class ViewHolder {
        private TextView cityName;
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
