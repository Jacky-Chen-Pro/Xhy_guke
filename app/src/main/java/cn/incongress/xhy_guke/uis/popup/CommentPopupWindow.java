package cn.incongress.xhy_guke.uis.popup;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;

import cn.incongress.xhy_guke.R;

/**
 * Created by Jacky on 2016/4/2.
 */
public class CommentPopupWindow extends BasePopupWindow implements View.OnClickListener{
    private TextView mTvCancel,mTvSend;
    private EditText mEtComment;
    private View popupView;

    public CommentPopupWindow(Activity context) {
        super(context);

        mTvCancel = (TextView) mPopupView.findViewById(R.id.tv_cancle);
        mTvSend = (TextView) mPopupView.findViewById(R.id.tv_send);
        mEtComment = (EditText) mPopupView.findViewById(R.id.et_comment);

        mTvCancel.setOnClickListener(this);

        setAutoShowInputMethod(true);
    }

    @Override
    public View getInputView() {
        return mEtComment;
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
        popupView= LayoutInflater.from(mContext).inflate(R.layout.popup_comment,null);
        return popupView;
    }

    @Override
    protected View getClickToDismissView() {
        return  popupView.findViewById(R.id.rl_bg);
    }

    @Override
    public View getPopupView() {
        return getPopupViewById(R.layout.popup_comment);
    }

    @Override
    public View getAnimaView() {
        return popupView.findViewById(R.id.ll_popup_container);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancle:
                dismiss();
                break;
        }
    }
}
