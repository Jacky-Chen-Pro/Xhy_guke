package cn.incongress.xhy_guke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.activitys.ImageViewPagerActivity;
import cn.incongress.xhy_guke.adapter.NoScrollGridViewAdapter;
import cn.incongress.xhy_guke.base.BaseFragment;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.uis.CircleImageView;
import cn.incongress.xhy_guke.uis.NoScrollGridView;
import cn.incongress.xhy_guke.utils.StringUtils;

/**
 * Created by Jacky on 2016/4/5.
 * 发帖详情页面
 */
public class VVTalkDetailMakePostFragment extends BaseFragment {
    private CircleImageView mCivCircleIcon;
    private TextView mTvName, mTvHospital, mTvContent;
    private ImageView mIvOne;
    private NoScrollGridView mTwoOrFourGridView, mOtherGridView;

    private String mUserIconUrl, mUserName, mUserHospital, mContent, mImgs;

    public static final String EXTRA_USER_ICON_URL = "userIconUrl";
    public static final String EXTRA_USER_NAME = "userName";
    public static final String EXTRA_USER_HOSPITAL = "userHospital";
    public static final String EXTRA_CONTENT = "content";
    public static final String EXTRA_IMGS = "imgs";

    public static final VVTalkDetailMakePostFragment getInstance(String userIconUrl, String userName, String userHospital, String content, String imgs) {
        VVTalkDetailMakePostFragment fragment = new VVTalkDetailMakePostFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_USER_ICON_URL, userIconUrl);
        bundle.putString(EXTRA_USER_NAME, userName);
        bundle.putString(EXTRA_USER_HOSPITAL, userHospital);
        bundle.putString(EXTRA_CONTENT, content);
        bundle.putString(EXTRA_IMGS, imgs);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            mUserIconUrl = bundle.getString(EXTRA_USER_ICON_URL, "");
            mUserName = bundle.getString(EXTRA_USER_NAME, "");
            mUserHospital = bundle.getString(EXTRA_USER_HOSPITAL, "");
            mContent = bundle.getString(EXTRA_CONTENT, "");
            mImgs = bundle.getString(EXTRA_IMGS, "");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vvtalk_post_area, null);
        initView(view);
        return view;
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        mCivCircleIcon = (CircleImageView) view.findViewById(R.id.civ_user_icon);
        mTvName = (TextView) view.findViewById(R.id.tv_name);
        mTvHospital = (TextView) view.findViewById(R.id.tv_hospital);
        mTvContent = (TextView) view.findViewById(R.id.tv_vvtalk_content);
        mIvOne = (ImageView) view.findViewById(R.id.iv_one_image);
        mTwoOrFourGridView = (NoScrollGridView) view.findViewById(R.id.ngv_two_or_four_image);
        mOtherGridView = (NoScrollGridView) view.findViewById(R.id.ngv_other_image);

        initData();
    }

    @Override
    public void initData() {
        super.initData();
        if (StringUtils.isNotEmpty(mUserIconUrl)) {
            Picasso.with(getActivity()).load(mUserIconUrl).into(mCivCircleIcon);
        }
        mTvName.setText(mUserName);
        mTvHospital.setText(mUserHospital);

        try {
            String content = URLDecoder.decode(mContent, Constants.ENCODDING_UTF8);
            mTvContent.setText(content);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            mTvContent.setText(R.string.decode_error);
        }

        //图片
        if (StringUtils.isNotEmpty(mImgs)) {
            final String[] imgs = mImgs.split(",");
            if (imgs.length == 1) {
                Picasso.with(getActivity()).load(imgs[0]).into(mIvOne);
                mIvOne.setVisibility(View.VISIBLE);
                mTwoOrFourGridView.setVisibility(View.GONE);
                mOtherGridView.setVisibility(View.GONE);

                mIvOne.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageViewPagerActivity.startImageViewPagerActivity(getActivity(),imgs,0);
                    }
                });
            } else if (imgs.length == 2 || imgs.length == 4) {
                mIvOne.setVisibility(View.GONE);
                mTwoOrFourGridView.setAdapter(new NoScrollGridViewAdapter(getActivity(), imgs));
                mTwoOrFourGridView.setVisibility(View.VISIBLE);
                mOtherGridView.setVisibility(View.GONE);

                mTwoOrFourGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ImageViewPagerActivity.startImageViewPagerActivity(getActivity(),imgs,position);
                    }
                });
            } else {
                mIvOne.setVisibility(View.GONE);
                mTwoOrFourGridView.setVisibility(View.GONE);
                mOtherGridView.setAdapter(new NoScrollGridViewAdapter(getActivity(), imgs));
                mOtherGridView.setVisibility(View.VISIBLE);

                mOtherGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ImageViewPagerActivity.startImageViewPagerActivity(getActivity(),imgs,position);
                    }
                });
            }
        }

        dismissProgressDialog();
    }
}
