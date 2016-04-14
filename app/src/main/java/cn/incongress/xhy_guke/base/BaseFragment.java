package cn.incongress.xhy_guke.base;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.view.View;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.inter.BaseFragmentInterface;

/**
 * Created by Jacky Chen on 2016/3/24 0024.
 */
public class BaseFragment extends Fragment implements BaseFragmentInterface{

    protected ProgressDialog mProgressDialog;

    @Override
    public void initView(View view) {
        mProgressDialog = ProgressDialog.show(getActivity(),null, getString(R.string.loading));
    }
    @Override
    public void initData() {
    }

    protected void dismissProgressDialog() {
        if(mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
