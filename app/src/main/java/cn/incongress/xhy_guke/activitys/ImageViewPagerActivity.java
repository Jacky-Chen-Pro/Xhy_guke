package cn.incongress.xhy_guke.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.BaseActivity;
import cn.incongress.xhy_guke.uis.indicator.CircleIndicator;

/**
 * Created by Jacky on 2016/4/12.
 */
public class ImageViewPagerActivity extends BaseActivity {
    private ViewPager mViewpager;
    private CircleIndicator mIndicator;

    private String[] mImageUrls;
    private int mStartPosition = 0;

    private static final String EXTRA_IMAGE_URLS = "urls";
    private static final String EXTRA_START_POSITION = "position";

    public static void startImageViewPagerActivity(Context context,String[] imgsUrls,int startPosition) {
        Intent intent = new Intent();
        intent.setClass(context, ImageViewPagerActivity.class);
        intent.putExtra(EXTRA_IMAGE_URLS, imgsUrls);
        intent.putExtra(EXTRA_START_POSITION, startPosition);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_pager);

        mImageUrls = getIntent().getStringArrayExtra(EXTRA_IMAGE_URLS);
        mStartPosition = getIntent().getIntExtra(EXTRA_START_POSITION, 0);

        mViewpager = getViewById(R.id.viewpager);
        mIndicator = getViewById(R.id.indicator);


        mViewpager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        mViewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mImageUrls.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView view = new PhotoView(ImageViewPagerActivity.this);
                view.enable();
                view.setImageResource(R.mipmap.default_load_bg);
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                view.setBackgroundColor(getResources().getColor(R.color.black));
                Glide.with(ImageViewPagerActivity.this).load(mImageUrls[position]).into(view);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        mIndicator.setViewPager(mViewpager);
        mViewpager.setCurrentItem(mStartPosition);
    }

}
