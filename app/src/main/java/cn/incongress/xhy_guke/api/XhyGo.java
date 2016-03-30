package cn.incongress.xhy_guke.api;

import android.content.Context;

import com.zhy.http.okhttp.callback.StringCallback;

import cn.incongress.xhy_guke.utils.NetWorkUtils;
import cn.incongress.xhy_guke.utils.StringUtils;

/**
 * Created by Jacky Chen on 2016/3/29 0029.
 * 调用接口，业务处理类
 */
public class XhyGo {
    /** 网络连接错误 **/
    public static final int INTERNET_ERROR = 0X0001;
    /** 字段格式错误 **/
    public static final int FIELD_FORMAT_ERROR = 0X0002;
    /** 请求成功 **/
    public static final int SUCCESS = 0X0003;

    /**
     * 获取V言V语首页数据
     * @param context
     * @param lastDataId
     * @param row
     * @param topIds
     * @param stringCallback
     * @return
     */
    public static final int getMainDataListVyvy(Context context,String lastDataId,String row, String topIds,StringCallback stringCallback) {
        //先检查是否有网络
        if(NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if(StringUtils.isNotEmpty(lastDataId,row)) {
                //最后发送请求
                XhyApiClient.getMainDataListVyvy(lastDataId,row,topIds,stringCallback);
                return SUCCESS;
            }else {
                return FIELD_FORMAT_ERROR;
            }
        }else {
            return INTERNET_ERROR;
        }
    }

    public static final int getDataById(Context context, String dataId, String userId, String whereState, StringCallback stringCallback) {
        //先检查是否有网络
        if(NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if(StringUtils.isNotEmpty(dataId, userId, whereState)) {
                //最后发送请求
                XhyApiClient.getDataById(dataId, userId, whereState, stringCallback);
                return SUCCESS;
            }else {
                return FIELD_FORMAT_ERROR;
            }
        }else {
            return INTERNET_ERROR;
        }
    }


    public static final int getCommentList(Context context, String dataId, String userId, String lastCommentId, String row, StringCallback stringCallback) {
        //先检查是否有网络
        if(NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if(StringUtils.isNotEmpty(dataId, userId, lastCommentId, row)) {
                //最后发送请求
                XhyApiClient.getCommentList(dataId, userId, lastCommentId, row, stringCallback);
                return SUCCESS;
            }else {
                return FIELD_FORMAT_ERROR;
            }
        }else {
            return INTERNET_ERROR;
        }
    }

    public static final int getDataListDt (Context context, String lastDataId,String row, StringCallback stringCallback){
        //先检查是否有网络
        if(NetWorkUtils.isNetworkConnected(context)) {
            //再检查字段是否格式正确
            if(StringUtils.isNotEmpty(lastDataId, row)) {
                //最后发送请求
                XhyApiClient.getDataListDt(lastDataId, row, stringCallback);
                return SUCCESS;
            }else {
                return FIELD_FORMAT_ERROR;
            }
        }else {
            return INTERNET_ERROR;
        }
    }
}
