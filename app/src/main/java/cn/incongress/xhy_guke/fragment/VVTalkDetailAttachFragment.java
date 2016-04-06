package cn.incongress.xhy_guke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.BaseFragment;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.uis.CircleImageView;
import cn.incongress.xhy_guke.utils.StringUtils;
import cn.incongress.xhy_guke.utils.ToastUtils;

/**
 * Created by Jacky on 2016/4/6.
 */
public class VVTalkDetailAttachFragment extends BaseFragment {
    private CircleImageView mCivUserIcon;
    private TextView mTvUserName,mTvUserHospital,mTvTitle,mTvShowTime,mTvReadCount,
            mTvPdfName,mTvPdfSize,mTvDownload,mTvAttachIntro;

    private String mUserIconUrl, mUserName, mUserHospital, mTitle, mShowTime, mReadCount,mPdfName, mPdfSize, mDownloadUrl, mAttachIntro;

    //文件类型
    private int mDataType = Constants.DATA_TYPE_PDF;

    public static final String EXTRA_USER_ICON_URL = "userIconUrl";
    public static final String EXTRA_USER_NAME = "userName";
    public static final String EXTRA_USER_HOSPITAL = "userHospital";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_SHOW_TIME = "showTime";
    public static final String EXTRA_READ_COUNT = "readCount";
    public static final String EXTRA_PDF_NAME = "pdfName";
    public static final String EXTRA_PDF_SIZE = "pdfSize";
    public static final String EXTRA_DOWNLOAD_URL = "downloadUrl";
    public static final String EXTRA_ATTACH_INTRO = "attachIntro";
    public static final String EXTRA_DATA_TYPE = "dataType";

    public static final VVTalkDetailAttachFragment getInstance(String userIconUrl, String userName, String userHospital, String title, String showTime, String readCount,
                    String pdfName, String pdfSize, String downloadUrl, String attachIntro, int dataType) {
        VVTalkDetailAttachFragment fragment = new VVTalkDetailAttachFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_USER_ICON_URL, userIconUrl);
        bundle.putString(EXTRA_USER_NAME, userName);
        bundle.putString(EXTRA_USER_HOSPITAL, userHospital);
        bundle.putString(EXTRA_TITLE, title);
        bundle.putString(EXTRA_SHOW_TIME, showTime);
        bundle.putString(EXTRA_READ_COUNT, readCount);
        bundle.putString(EXTRA_PDF_NAME, pdfName);
        bundle.putString(EXTRA_PDF_SIZE, pdfSize);
        bundle.putString(EXTRA_DOWNLOAD_URL, downloadUrl);
        bundle.putString(EXTRA_ATTACH_INTRO, attachIntro);
        bundle.putInt(EXTRA_DATA_TYPE, dataType);

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
            mTitle = bundle.getString(EXTRA_TITLE, "");
            mShowTime = bundle.getString(EXTRA_SHOW_TIME, "");
            mReadCount = bundle.getString(EXTRA_READ_COUNT, "");
            mPdfName = bundle.getString(EXTRA_PDF_NAME, "");
            mPdfSize = bundle.getString(EXTRA_PDF_SIZE,"");
            mDownloadUrl = bundle.getString(EXTRA_DOWNLOAD_URL, "");
            mAttachIntro = bundle.getString(EXTRA_ATTACH_INTRO, "");
            mDataType = bundle.getInt(EXTRA_DATA_TYPE, Constants.DATA_TYPE_PDF);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vvtalk_attach_area, null);
        initView(view);
        return view;
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        mCivUserIcon = (CircleImageView) view.findViewById(R.id.civ_user_icon);
        mTvUserName = (TextView) view.findViewById(R.id.tv_user_name);
        mTvUserHospital = (TextView) view.findViewById(R.id.tv_user_hospital);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mTvShowTime = (TextView) view.findViewById(R.id.tv_show_time);
        mTvReadCount = (TextView) view.findViewById(R.id.tv_read_count);
        mTvPdfName = (TextView) view.findViewById(R.id.tv_pdf_name);
        mTvPdfSize = (TextView) view.findViewById(R.id.tv_pdf_size);
        mTvAttachIntro = (TextView) view.findViewById(R.id.tv_attach_intro);
        mTvDownload = (TextView) view.findViewById(R.id.tv_click_download);

        initData();
    }

    @Override
    public void initData() {
        super.initData();

        if(StringUtils.isNotEmpty(mUserIconUrl)) {
            Picasso.with(getActivity()).load(mUserIconUrl).into(mCivUserIcon);
        }

        mTvUserName.setText(mUserName);
        mTvUserHospital.setText(mUserHospital);
        mTvTitle.setText(mTitle);
        mTvShowTime.setText(mShowTime);
        mTvReadCount.setText(getString(R.string.vvtalk_read_count, mReadCount));
        mTvPdfName.setText(getPdfName(mPdfName,mDataType));
        mTvPdfSize.setText(mPdfSize);
        mTvAttachIntro.setText(mAttachIntro);

        mTvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShorToast("click to download", getActivity());
            }
        });

    }

    private String getPdfName(String pdfName, int type) {
        if(type == Constants.DATA_TYPE_PDF) {
            return pdfName + ".pdf";
        }else if(type == Constants.DATA_TYPE_WORD) {
            return pdfName + ".word";
        }else {
            return pdfName + ".ppt";
        }
    }
}
