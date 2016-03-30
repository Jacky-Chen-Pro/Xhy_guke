package cn.incongress.xhy_guke.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.http.okhttp.callback.StringCallback;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.inter.BaseFragmentInterface;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Jacky Chen on 2016/3/24 0024.
 */
public class BaseFragment extends Fragment implements BaseFragmentInterface{
    @Override
    public void initView(View view) {
    }
    @Override
    public void initData() {
    }
}
