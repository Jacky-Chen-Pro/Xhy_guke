package cn.incongress.xhy_guke.api;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jacky Chen on 2016/3/24 0024.
 */
public class XhyApiClient {
    /** 测试服地址 **/
    private static final String HOST = "http://114.80.201.49/XhyApiV2.do";
    private static final String PROJECT_NAME = "lnyx";

    /**
     * 获取V言V语列表
     * @param lastDataId
     * @param row
     * @param topIds
     */
    public static void getMainDataListVyvy(String lastDataId,String row, String topIds,StringCallback stringCallback){
        Map<String, String> params = new HashMap<>();
        params.put("project", PROJECT_NAME);
        params.put("lastDataId",lastDataId);
        params.put("row",row);
        params.put("topIds", topIds);
        OkHttpUtils.post().url(HOST +"?getMainDataListVyvy").params(params).build().execute(stringCallback);
    }

    /**
     * 获取动态数据
     * @param lastDataId
     * @param row
     * @param stringCallback
     */
    public static void getDataListDt(String lastDataId,String row, StringCallback stringCallback){
        Map<String, String> params = new HashMap<>();
        params.put("project", PROJECT_NAME);
        params.put("lastDataId",lastDataId);
        params.put("row",row);
        OkHttpUtils.post().url(HOST +"?getDataListDt").params(params).build().execute(stringCallback);
    }

    /**
     * 获取资源详情看，分别从V言V语或者动态中跳转
     * @param dataId
     * @param userId
     * @param whereState
     * @param stringCallback
     */
    public static void getDataById(String dataId, String userId, String whereState, StringCallback stringCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("dataId", dataId);
        params.put("userId",userId);
        params.put("whereState", whereState);
        OkHttpUtils.post().url(HOST +"?getDataById").params(params).build().execute(stringCallback);
    }

    /**
     * 获得评论列表
     * @param dataId
     * @param userId
     * @param lastCommentId
     * @param row
     * @param stringCallback
     */
    public static void getCommentList(String dataId, String userId, String lastCommentId, String row, StringCallback stringCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("dataId", dataId);
        params.put("userId", userId);
        params.put("lastCommentId",lastCommentId);
        params.put("row", row);
        OkHttpUtils.post().url(HOST +"?getCommentList").params(params).build().execute(stringCallback);
    }


}