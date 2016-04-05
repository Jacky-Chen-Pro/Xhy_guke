package cn.incongress.xhy_guke.uis.popup;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;

import com.zhy.http.okhttp.callback.StringCallback;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.api.XhyGo;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.base.XhyApplication;
import cn.incongress.xhy_guke.utils.ToastUtils;
import okhttp3.Call;

/**
 * Created by Jacky on 2016/4/2.
 */
public class CommentPopupWindow extends BasePopupWindow implements View.OnClickListener{
    private TextView mTvCancel,mTvSend;
    private EditText mEtComment;
    private View popupView;

    //发表评论需要的一些参数
    private String mSendUserId = XhyApplication.userId;
    private String mReceiveUserId = "-1";//@人的id,默认是-1
    private String mReceiveName = "";//@人的名字
    private String mDataId = ""; //资源标示
    private String mContent = "";//评论内容

    private StringCallback mStringCallback;

    public CommentPopupWindow(Activity context,String sendUserId, String receiveUserId, String receiveName, String dataId, StringCallback stringCallback) {
        super(context);

        mTvCancel = (TextView) mPopupView.findViewById(R.id.tv_cancle);
        mTvSend = (TextView) mPopupView.findViewById(R.id.tv_send);
        mEtComment = (EditText) mPopupView.findViewById(R.id.et_comment);

        this.mSendUserId = sendUserId;
        this.mReceiveUserId = receiveUserId;
        this.mReceiveName = receiveName;
        this.mDataId = dataId;
        this.mStringCallback = stringCallback;

        mTvCancel.setOnClickListener(this);
        mTvSend.setOnClickListener(this);

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
            case R.id.tv_send:
                mContent = mEtComment.getText().toString();
                try {
                    mContent = URLEncoder.encode(mContent, Constants.ENCODDING_UTF8);
                    //发送评论
                    int result = XhyGo.goSendComment(mContext, mSendUserId, mReceiveUserId, mReceiveName, mDataId, mContent,mStringCallback);
                    if(result == XhyGo.SUCCESS) {
                        dismiss();
                    }else {
                        ToastUtils.showShorToast(mContext.getString(R.string.send_comment_fail), mContext);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                break;
        }
    }
}
