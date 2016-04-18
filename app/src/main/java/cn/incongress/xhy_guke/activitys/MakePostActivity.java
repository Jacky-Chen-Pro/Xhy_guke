package cn.incongress.xhy_guke.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.adapter.MakePostFragmentAdapter;
import cn.incongress.xhy_guke.base.BaseActivity;
import cn.incongress.xhy_guke.fragment.MakePostAcademicFragment;
import cn.incongress.xhy_guke.fragment.MakePostVVFragment;
import cn.incongress.xhy_guke.utils.ToastUtils;
import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by Jacky on 2016/4/12.
 * 发布界面
 */
public class MakePostActivity extends BaseActivity {
    private ViewPager mViewpager;
    private SegmentedGroup mSegmentGroup;
    private List<Fragment> mFragmetns = new ArrayList<>();
    private MakePostFragmentAdapter mFragmentAdapter;
    private TextView mTvPublish;

    private RadioButton rbVV,rbAcademic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_post);
        initToolbar("", true, false, -1, null, false, -1, null);

        mViewpager = getViewById(R.id.viewpager);
        mSegmentGroup = getViewById(R.id.segment);
        rbVV = getViewById(R.id.rb_vv);
        rbAcademic = getViewById(R.id.rb_academics);
        mTvPublish = getViewById(R.id.tv_publish);

        initConfig();
    }

    private void initConfig() {
        mSegmentGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_vv) {
                    mViewpager.setCurrentItem(0);
                    mTvPublish.setVisibility(View.VISIBLE);
                }else {
                    mViewpager.setCurrentItem(1);
                    mTvPublish.setVisibility(View.INVISIBLE);
                }
            }
        });

        mFragmetns.add(0, MakePostVVFragment.getInstance());
        mFragmetns.add(1, MakePostAcademicFragment.getInstance());

        mFragmentAdapter = new MakePostFragmentAdapter(getSupportFragmentManager(), mFragmetns);

        rbVV.setChecked(true);
        mViewpager.setAdapter(mFragmentAdapter);
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    rbVV.setChecked(true);
                } else {
                    rbAcademic.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTvPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MakePostVVFragment)mFragmetns.get(0)).createPostImag();
            }
        });
    }
}
