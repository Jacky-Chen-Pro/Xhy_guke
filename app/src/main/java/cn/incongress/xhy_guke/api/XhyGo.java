package cn.incongress.xhy_guke.api;

import android.content.Context;

import com.zhy.http.okhttp.callback.StringCallback;

import org.jackyonline.refreshdemo.RefreshLayout;

import java.io.File;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.base.Constants;
import cn.incongress.xhy_guke.utils.NetWorkUtils;
import cn.incongress.xhy_guke.utils.StringUtils;
import cn.incongress.xhy_guke.utils.ToastUtils;

/**
 * Created by Jacky Chen on 2016/3/29 0029.
 * 调用接口，业务处理类
 */
public class XhyGo {
    /**
     * 网络连接错误
     **/
    public static final int INTERNET_ERROR = 0X0001;
    /**
     * 字段格式错误
     **/
    public static final int FIELD_FORMAT_ERROR = 0X0002;
    /**
     * 请求成功
     **/
    public static final int SUCCESS = 0X0003;

    /**
     * 获取V言V语首页数据
     *
     * @param context
     * @param lastDataId
     * @param topIds
     * @param stringCallback
     * @return
     */
    public static final int getMainDataListVyvy(Context context, RefreshLayout refreshLayout, int lastDataId, String topIds, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(lastDataId)) {
                //最后发送请求
                XhyApiClient.getMainDataListVyvy(lastDataId, topIds, stringCallback);
                return SUCCESS;
            } else {
                return FIELD_FORMAT_ERROR;
            }
        } else {
            refreshLayout.finishCurrentLoad();
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int getDataById(Context context, String dataId, String userId, String whereState, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(dataId, userId, whereState)) {
                //最后发送请求
                XhyApiClient.getDataById(dataId, userId, whereState, stringCallback);
                return SUCCESS;
            } else {
                return FIELD_FORMAT_ERROR;
            }
        } else {
            return INTERNET_ERROR;
        }
    }


    public static final int getCommentList(Context context, String dataId, String userId, String lastCommentId, String row, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(dataId, userId, lastCommentId, row)) {
                //最后发送请求
                XhyApiClient.getCommentList(dataId, userId, lastCommentId, row, stringCallback);
                return SUCCESS;
            } else {
                return FIELD_FORMAT_ERROR;
            }
        } else {
            return INTERNET_ERROR;
        }
    }

    public static final int getDataListDt(Context context, RefreshLayout refreshLayout, String lastDataId, String row, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(lastDataId, row)) {
                //最后发送请求
                XhyApiClient.getDataListDt(lastDataId, row, stringCallback);
                return SUCCESS;
            } else {
                return FIELD_FORMAT_ERROR;
            }
        } else {
            refreshLayout.finishCurrentLoad();
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goDataLaud(Context context, String dataId, String userId, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(dataId, userId)) {
                //最后发送请求
                XhyApiClient.doDataLaud(dataId, userId, stringCallback);
                return SUCCESS;
            } else {
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goGetDataLaud(Context context, RefreshLayout refreshLayout, String dataId, String userId, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(dataId, userId)) {
                //最后发送请求
                XhyApiClient.getDataLaud(dataId, userId, stringCallback);
                return SUCCESS;
            } else {
                return FIELD_FORMAT_ERROR;
            }
        } else {
            refreshLayout.finishCurrentLoad();
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goSendComment(Context context, String sendUserId, String receiveUserId, String receiveName, String dataId, String content, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(content)) {
                //最后发送请求
                XhyApiClient.sendComment(sendUserId, receiveUserId, receiveName, dataId, content, stringCallback);
                return SUCCESS;
            } else {
                ToastUtils.showShorToast(context.getString(R.string.comment_empty), context);
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goGetTuiJianList(Context context, String userId, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(userId)) {
                //最后发送请求
                XhyApiClient.getTuiJianList(userId, stringCallback);
                return SUCCESS;
            } else {
                ToastUtils.showShorToast(context.getString(R.string.comment_empty), context);
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goDoUserFocus(Context context, String userId, String focusUserId, String state, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(userId, focusUserId, state)) {
                //最后发送请求
                XhyApiClient.doUserFocus(userId, focusUserId, state, stringCallback);
                return SUCCESS;
            } else {
                ToastUtils.showShorToast(context.getString(R.string.comment_empty), context);
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goGetUserByHeader(Context context, String userId, String getUserId, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(userId, getUserId)) {
                //最后发送请求
                XhyApiClient.getUserByHeader(userId, getUserId, stringCallback);
                return SUCCESS;
            } else {
                ToastUtils.showShorToast(context.getString(R.string.comment_empty), context);
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goGetDataListByUser(Context context, String userId, String lastDataId, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(userId, lastDataId)) {
                //最后发送请求
                XhyApiClient.getDataListByUser(userId, lastDataId, stringCallback);
                return SUCCESS;
            } else {
                ToastUtils.showShorToast(context.getString(R.string.comment_empty), context);
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goUploadZhengShu(Context context, String userId, File zhengshu, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(userId) && zhengshu != null) {
                //最后发送请求
                XhyApiClient.uploadZhengShu(userId, zhengshu, stringCallback);
                return SUCCESS;
            } else {
                ToastUtils.showShorToast(context.getString(R.string.comment_empty), context);
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goXhyClientLogin(Context context, String mobile, String password, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(mobile, password)) {
                //最后发送请求
                XhyApiClient.xhyClientLogin(mobile, password, stringCallback);
                return SUCCESS;
            } else {
                ToastUtils.showShorToast(context.getString(R.string.comment_empty), context);
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goXhyClientReg(Context context, String mobile, String password, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(mobile, password)) {
                //最后发送请求
                XhyApiClient.xhyClientReg(mobile, password, stringCallback);
                return SUCCESS;
            } else {
                ToastUtils.showShorToast(context.getString(R.string.comment_empty), context);
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goUpdateUser(Context context, String userId, String trueName, String sex, String mobilePhone,
                                         String email, String keshi, String zhicheng, String province,
                                         String provinceName, String city, String cityName,
                                         String hospital, String hospitalName, String nickName,
                                         String univsId, String univ, String univYear, String rzRemark,
                                         StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(userId)) {
                //最后发送请求
                XhyApiClient.updateUser(userId, trueName, sex, mobilePhone,
                        email, keshi, zhicheng, province, provinceName, city, cityName,
                        hospital, hospitalName, nickName, univsId, univ, univYear, rzRemark, stringCallback);
                return SUCCESS;
            } else {
                ToastUtils.showShorToast(context.getString(R.string.comment_empty), context);
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goGetUserNumbers(Context context, String userId, String userType, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(userId)) {
                //最后发送请求
                XhyApiClient.getUserNumbers(userId, userType, stringCallback);
                return SUCCESS;
            } else {
                ToastUtils.showShorToast(context.getString(R.string.comment_empty), context);
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goGetDataListBySelf(Context context,String userId, String lastDataId, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(userId)) {
                //最后发送请求
                XhyApiClient.getDataListBySelf(userId, lastDataId, stringCallback);
                return SUCCESS;
            } else {
                ToastUtils.showShorToast(context.getString(R.string.comment_empty), context);
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goGetFrans(Context context, String userId, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(userId)) {
                //最后发送请求
                XhyApiClient.getFrans(userId, stringCallback);
                return SUCCESS;
            } else {
                ToastUtils.showShorToast(context.getString(R.string.comment_empty), context);
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goGetFocusByMe(Context context, String userId, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(userId)) {
                //最后发送请求
                XhyApiClient.getFocusByMe(userId, stringCallback);
                return SUCCESS;
            } else {
                ToastUtils.showShorToast(context.getString(R.string.comment_empty), context);
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goShouCang(Context context, String userId, String dataId, String state, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(userId,dataId, state)) {
                //最后发送请求
                XhyApiClient.shouCang(userId, dataId, state, stringCallback);
                return SUCCESS;
            } else {
                ToastUtils.showShorToast(context.getString(R.string.comment_empty), context);
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goGetXhyShouCangs(Context context,String userId, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(userId)) {
                //最后发送请求
                XhyApiClient.getXhyShouCangs(userId, stringCallback);
                return SUCCESS;
            } else {
                ToastUtils.showShorToast(context.getString(R.string.comment_empty), context);
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }


    public static final int goCreatePost(Context context, String userId, String content, String dataId,String  isNicking, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(userId)) {
                //最后发送请求
                XhyApiClient.doCreatePost(userId, content, dataId, isNicking, stringCallback);
                return SUCCESS;
            } else {
                ToastUtils.showShorToast(context.getString(R.string.comment_empty), context);
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

    public static final int goCreatePostImg(Context context, String userId, String dataId, File uploadImg,String fileName,String isNiming, StringCallback stringCallback) {
        //先检查是否有网络
        if (NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if (StringUtils.isNotEmpty(userId)) {
                //最后发送请求
                XhyApiClient.doCreatePostImg(userId, dataId,uploadImg,fileName, isNiming, stringCallback);
                return SUCCESS;
            } else {
                ToastUtils.showShorToast(context.getString(R.string.comment_empty), context);
                return FIELD_FORMAT_ERROR;
            }
        } else {
            ToastUtils.showShorToast(context.getString(R.string.internet_error), context);
            return INTERNET_ERROR;
        }
    }

}
