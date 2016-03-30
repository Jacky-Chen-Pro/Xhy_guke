package cn.incongress.xhy_guke.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.zhy.http.okhttp.callback.StringCallback;

import cn.incongress.xhy_guke.R;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Jacky on 2016/3/25.
 */
public class StringCallBackWithProgress extends StringCallback {
    private Context mContext;
    protected ProgressDialog mProgressDialog;

    public StringCallBackWithProgress(Context context) {
        this.mContext = context;
    }

    @Override
    public void onBefore(Request request) {
        super.onBefore(request);
//        if(mContext!= null) {
//            if(mProgressDialog == null) {
//                mProgressDialog = ProgressDialog.show(mContext, null, mContext.getString(R.string.loading),false,false);
//            }else {
//                mProgressDialog.show();
//            }
//        }
    }

    @Override
    public void onAfter() {
        super.onAfter();
//        if(mContext != null) {
//            if(mProgressDialog != null && mProgressDialog.isShowing()) {
//                mProgressDialog.dismiss();
//            }
//        }
    }

    @Override
    public void onError(Call call, Exception e) {

    }

    @Override
    public void onResponse(String response) {

    }


}
