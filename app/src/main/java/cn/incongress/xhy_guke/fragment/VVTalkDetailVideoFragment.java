package cn.incongress.xhy_guke.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.BaseFragment;
import cn.incongress.xhy_guke.uis.CircleImageView;
import cn.incongress.xhy_guke.utils.StringUtils;
import cn.incongress.xhy_guke.utils.ToastUtils;

/**
 * Created by Jacky on 2016/4/6.
 */
public class VVTalkDetailVideoFragment extends BaseFragment implements UniversalVideoView.VideoViewCallback {
    private static final String TAG = "MainActivity";
    private static final String SEEK_POSITION_KEY = "SEEK_POSITION_KEY";
    private static final String VIDEO_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    UniversalVideoView mVideoView;
    UniversalMediaController mMediaController;

    View mVideoLayout;

    private CircleImageView mCivUserIcon;
    private TextView mTvUserName,mTvTitle,mTvShowTime,mTvReadCount;

    private int mSeekPosition;
    private int cachedHeight;
    private boolean isFullscreen;

    private String mVideoUrl = "";
    private String mVideoTitle = "";
    private String mUserIconUrl, mUserName,mTitle, mShowTime, mReadCount;

    public static final String EXTRA_VIDEO_URL = "videoUrl";
    public static final String EXTRA_VIDEO_TITLE = "videoTitle";
    public static final String EXTRA_USER_ICON_URL = "userIconUrl";
    public static final String EXTRA_USER_NAME = "userName";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_SHOW_TIME = "showTime";
    public static final String EXTRA_READ_COUNT = "readCount";

    public static final VVTalkDetailVideoFragment getInstance(String videoUrl,String videoTitle,String userIconUrl, String userName, String title, String showTime, String readCount) {
        VVTalkDetailVideoFragment fragment = new VVTalkDetailVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_VIDEO_URL, videoUrl);
        bundle.putString(EXTRA_VIDEO_TITLE, videoTitle);
        bundle.putString(EXTRA_USER_ICON_URL, userIconUrl);
        bundle.putString(EXTRA_USER_NAME, userName);
        bundle.putString(EXTRA_TITLE, title);
        bundle.putString(EXTRA_SHOW_TIME, showTime);
        bundle.putString(EXTRA_READ_COUNT, readCount);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            Bundle bundle = getArguments();
            mVideoUrl = bundle.getString(EXTRA_VIDEO_URL, "");
            mVideoTitle = bundle.getString(EXTRA_VIDEO_TITLE, "");
            mUserIconUrl = bundle.getString(EXTRA_USER_ICON_URL, "");
            mUserName = bundle.getString(EXTRA_USER_NAME, "");
            mTitle = bundle.getString(EXTRA_TITLE, "");
            mShowTime = bundle.getString(EXTRA_SHOW_TIME, "");
            mReadCount = bundle.getString(EXTRA_READ_COUNT, "");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vvtalk_video_area, null);

        initView(view);
        return view;
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        mVideoLayout = view.findViewById(R.id.video_layout);
        mVideoView = (UniversalVideoView) view.findViewById(R.id.videoView);
        mMediaController = (UniversalMediaController) view.findViewById(R.id.media_controller);
        mCivUserIcon = (CircleImageView) view.findViewById(R.id.civ_user_icon);
        mTvUserName = (TextView) view.findViewById(R.id.tv_user_name);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mTvShowTime = (TextView) view.findViewById(R.id.tv_show_time);
        mTvReadCount = (TextView) view.findViewById(R.id.tv_read_count);

        initData();
    }

    @Override
    public void initData() {
        super.initData();

        mVideoView.setMediaController(mMediaController);
        setVideoAreaSize();
        mVideoView.setVideoViewCallback(this);
        mMediaController.setTitle(mVideoTitle);

        if(StringUtils.isNotEmpty(mUserIconUrl)) {
            Picasso.with(getActivity()).load(mUserIconUrl).into(mCivUserIcon);
        }

        mTvUserName.setText(mUserName);
        mTvTitle.setText(mTitle);
        mTvShowTime.setText(mShowTime);
        mTvReadCount.setText(mReadCount);

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                ToastUtils.showShorToast("video complete", getActivity());
            }
        });

        dismissProgressDialog();
    }


    @Override
    public void onPause() {
        super.onPause();
        ToastUtils.showShorToast("video onPause", getActivity());
        if (mVideoView != null && mVideoView.isPlaying()) {
            mSeekPosition = mVideoView.getCurrentPosition();
            mVideoView.pause();
        }
    }

    /**
     * 置视频区域大小
     */
    private void setVideoAreaSize() {
        mVideoLayout.post(new Runnable() {
            @Override
            public void run() {
                int width = mVideoLayout.getWidth();
                cachedHeight = (int) (width * 405f / 720f);
//                cachedHeight = (int) (width * 3f / 4f);
//                cachedHeight = (int) (width * 9f / 16f);
                ViewGroup.LayoutParams videoLayoutParams = mVideoLayout.getLayoutParams();
                videoLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                videoLayoutParams.height = cachedHeight;
                mVideoLayout.setLayoutParams(videoLayoutParams);
                mVideoView.setVideoPath(mVideoUrl);
                mVideoView.requestFocus();
                mVideoView.start();
            }
        });
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SEEK_POSITION_KEY, mSeekPosition);
    }

//    @Override
//    public void onRestoreInstanceState(Bundle outState) {
//        mSeekPosition = outState.getInt(SEEK_POSITION_KEY);
//    }


    @Override
    public void onScaleChange(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        if (isFullscreen) {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoLayout.setLayoutParams(layoutParams);

        } else {
            ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = this.cachedHeight;
            mVideoLayout.setLayoutParams(layoutParams);
        }

        switchTitleBar(!isFullscreen);
    }

    private void switchTitleBar(boolean show) {
//        android.support.v7.app.ActionBar supportActionBar = getActivity().get();
//        if (supportActionBar != null) {
//            if (show) {
//                supportActionBar.show();
//            } else {
//                supportActionBar.hide();
//            }
//        }
    }

    @Override
    public void onPause(MediaPlayer mediaPlayer) {
    }

    @Override
    public void onStart(MediaPlayer mediaPlayer) {
    }

    @Override
    public void onBufferingStart(MediaPlayer mediaPlayer) {
    }

    @Override
    public void onBufferingEnd(MediaPlayer mediaPlayer) {
    }

//    @Override
//    public void onBackPressed() {
//        if (this.isFullscreen) {
//            mVideoView.setFullscreen(false);
//        } else {
//            super.onBackPressed();
//        }
//    }

}
