package cn.incongress.xhy_guke.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.BaseFragment;

/**
 * Created by Jacky on 2016/4/12.
 * 发布V说界面
 */
public class MakePostVVFragment extends BaseFragment {
    private AppCompatCheckBox mCheckbox;
    private boolean mIsNickName = false;//是否昵称发布
    private ImageView mIvCloseKeyboard;

    public static MakePostVVFragment getInstance() {
        MakePostVVFragment fragment = new MakePostVVFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_post_vv, null);
        initView(view);
        return view;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mCheckbox = (AppCompatCheckBox) view.findViewById(R.id.checkBox);
        mIvCloseKeyboard = (ImageView) view.findViewById(R.id.iv_close_keyboard);

        mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mIsNickName = isChecked;
            }
        });

        initData();
        dismissProgressDialog();
    }

    @Override
    public void initData() {
        super.initData();
    }

}
