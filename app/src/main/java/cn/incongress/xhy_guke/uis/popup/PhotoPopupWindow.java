package cn.incongress.xhy_guke.uis.popup;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.http.okhttp.callback.StringCallback;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.api.XhyGo;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.base.XhyApplication;
import cn.incongress.xhy_guke.utils.ToastUtils;

/**
 * Created by Jacky on 2016/4/2.
 */
public class PhotoPopupWindow extends BasePopupWindow implements View.OnClickListener{
    private LinearLayout mLlCamera,mLlChooseFromPhoto,mLlCancel;
    private View popupView;

    private StringCallback mStringCallback;

    public PhotoPopupWindow(Activity context, StringCallback stringCallback) {
        super(context);

        mLlCamera = (LinearLayout) mPopupView.findViewById(R.id.ll_take_photo);
        mLlChooseFromPhoto = (LinearLayout) mPopupView.findViewById(R.id.ll_choose_from_camera);
        mLlCancel = (LinearLayout) mPopupView.findViewById(R.id.ll_cancel);

        this.mStringCallback = stringCallback;

        mLlCamera.setOnClickListener(this);
        mLlChooseFromPhoto.setOnClickListener(this);
        mLlCancel.setOnClickListener(this);

        setAutoShowInputMethod(true);
    }

    @Override
    protected Animation getShowAnimation() {
        return getDefaultAlphaInAnimation();
    }

    @Override
    public Animation getExitAnimation() {
        return getDefaultAlphaOutAnimation();
    }

    @Override
    public View getPopupViewById(int resId) {
        popupView= LayoutInflater.from(mContext).inflate(R.layout.popup_photo,null);
        return popupView;
    }

    @Override
    protected View getClickToDismissView() {
        return  popupView.findViewById(R.id.rl_bg);
    }

    @Override
    public View getPopupView() {
        return getPopupViewById(R.layout.popup_photo);
    }

    @Override
    public View getAnimaView() {
        return popupView.findViewById(R.id.ll_popup_container);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_cancel:
                dismiss();
                break;
            case R.id.ll_take_photo:
                ToastUtils.showShorToast("拍照", mContext);
                break;
            case R.id.ll_choose_from_camera:
                ToastUtils.showShorToast("相册", mContext);
                break;
        }
    }
}
