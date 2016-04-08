package cn.incongress.xhy_guke.api;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import cn.incongress.xhy_guke.base.Constants;

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
     * @param topIds
     */
    public static void getMainDataListVyvy(int lastDataId, String topIds,StringCallback stringCallback){
        Map<String, String> params = new HashMap<>();
        params.put("project", PROJECT_NAME);
        params.put("lastDataId",lastDataId+"");
        params.put("row", Constants.PAGE_SIZE);
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


    /**
     * 内容点赞
     * @param dataId
     * @param userId
     * @param stringCallback
     */
    public static void doDataLaud(String dataId, String userId,StringCallback stringCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("dataId", dataId);
        params.put("userId", userId);
        OkHttpUtils.post().url(HOST +"?dataLaud").params(params).build().execute(stringCallback);
    }


    /**
     * 获取点赞、评论数
     * @param dataId
     * @param userId
     * @param stringCallback
     */
    public static void getDataLaud(String dataId, String userId, StringCallback stringCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("dataId", dataId);
        params.put("userId", userId);
        OkHttpUtils.post().url(HOST +"?getDataLaud").params(params).build().execute(stringCallback);
    }


    /**
     * 发表评论
     * @param sendUserId
     * @param receiveUserId
     * @param receiveName
     * @param dataId
     * @param content
     * @param stringCallback
     */
    public static void sendComment(String sendUserId, String receiveUserId, String receiveName, String dataId, String content, StringCallback stringCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("sendUserId", sendUserId);
        params.put("receiveUserId", receiveUserId);
        params.put("receiveName", receiveName);
        params.put("clientType", Constants.CLIENT_TYPE);
        params.put("dataId", dataId);
        params.put("content", content);

        OkHttpUtils.post().url(HOST +"?sendComment").params(params).build().execute(stringCallback);
    }


    /**
     * 获取推荐列表
     *
     * @param userId
     * @param stringCallback
     */
    public static void getTuiJianList(String userId, StringCallback stringCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("project", PROJECT_NAME);
        params.put("userId", userId);

        OkHttpUtils.post().url(HOST +"?getTuiJianList").params(params).build().execute(stringCallback);
    }

    /**
     * 关注取消关注
     * @param userId
     * @param focusUserId
     * @param state 1关注；0取消关注
     */
    public static void doUserFocus(String userId, String focusUserId, String state, StringCallback stringCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("focusUserId", focusUserId);
        params.put("state", state);
        params.put("project", PROJECT_NAME);

        OkHttpUtils.post().url(HOST +"?doUserFocus").params(params).build().execute(stringCallback);
    }

    /**
     * 点头像获取个人信息
     * @param userId
     * @param getUserId
     * @param stringCallback
     */
    public static void getUserByHeader(String userId, String getUserId, StringCallback stringCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("getUserId", getUserId);
        params.put("project", PROJECT_NAME);

        OkHttpUtils.post().url(HOST +"?getUserByHeader").params(params).build().execute(stringCallback);
    }

    /**
     * 点头像获取资源列表
     * @param userId
     * @param lastDataId
     * @param stringCallback
     */
    public static void getDataListByUser(String userId, String lastDataId, StringCallback stringCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("project", PROJECT_NAME);
        params.put("userId", userId);
        params.put("lastDataId", lastDataId);
        params.put("row", Constants.PAGE_SIZE);

        OkHttpUtils.post().url(HOST +"?getDataListByUser").params(params).build().execute(stringCallback);
    }

    /**
     * 上传证书
     * @param userId
     * @param zhengshu
     * @param stringCallback
     */
    public static void uploadZhengShu(String userId, File zhengshu, StringCallback stringCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", userId);

        OkHttpUtils.post().url(HOST + "?uploadZhengShu").
                params(params).addFile("zhengShuImg", "zhengShuImg[" + userId + "]", zhengshu).build().
                execute(stringCallback);
    }


    /**
     * 登录
     * @param mobile
     * @param password
     * @param stringCallback
     */
    public static void xhyClientLogin(String mobile, String password, StringCallback stringCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("project", PROJECT_NAME);
        params.put("mobile", mobile);
        params.put("password", password);

        OkHttpUtils.post().url(HOST + "?xhyClientLogin").params(params).build().execute(stringCallback);
    }

    /**
     * 注册
     * @param mobile
     * @param password
     * @param stringCallback
     */
    public static void xhyClientReg(String mobile, String password, StringCallback stringCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("project", PROJECT_NAME);
        params.put("mobile", mobile);
        params.put("password", password);

        OkHttpUtils.post().url(HOST + "?xhyClientReg").params(params).build().execute(stringCallback);
    }

    /**
     * 修改用户
     * @param userId
     * @param trueName
     * @param sex
     * @param mobilePhone
     * @param email
     * @param keshi
     * @param zhicheng
     * @param province
     * @param provinceName
     * @param city
     * @param cityName
     * @param hospital
     * @param hospitalName
     * @param nickName
     * @param univsId
     * @param univ
     * @param univYear
     * @param rzRemark
     * @param stringCallback
     */
    public static void updateUser(String userId, String trueName, String sex, String mobilePhone,
                           String email, String keshi, String zhicheng, String province,
                           String provinceName, String city, String cityName,
                           String hospital, String hospitalName,String nickName,
                           String univsId, String univ, String univYear, String rzRemark,
                           StringCallback stringCallback) {
        
        Map<String, String> params = new HashMap<String, String>();
        try {
            params.put("userId", userId);
            params.put("trueName", URLEncoder.encode(trueName, Constants.ENCODDING_UTF8));
            params.put("sex", URLEncoder.encode(sex, Constants.ENCODDING_UTF8));
            params.put("mobilePhone", URLEncoder.encode(mobilePhone, Constants.ENCODDING_UTF8));
            params.put("email", URLEncoder.encode(email, Constants.ENCODDING_UTF8));
            params.put("keshi", URLEncoder.encode(keshi, Constants.ENCODDING_UTF8));
            params.put("zhicheng", URLEncoder.encode(zhicheng, Constants.ENCODDING_UTF8));
            params.put("province", URLEncoder.encode(province, Constants.ENCODDING_UTF8));
            params.put("provinceName", URLEncoder.encode(provinceName, Constants.ENCODDING_UTF8));
            params.put("city", URLEncoder.encode(city, Constants.ENCODDING_UTF8));
            params.put("cityName", URLEncoder.encode(cityName, Constants.ENCODDING_UTF8));
            params.put("hospital", URLEncoder.encode(hospital, Constants.ENCODDING_UTF8));
            params.put("hospitalName", URLEncoder.encode(hospitalName, Constants.ENCODDING_UTF8));
            params.put("nickname", URLEncoder.encode(nickName, Constants.ENCODDING_UTF8));
            params.put("univsId", univsId);
            params.put("univ",  URLEncoder.encode(univ, Constants.ENCODDING_UTF8));
            params.put("univYear", univYear);
            params.put("rzRemark", URLEncoder.encode(rzRemark, Constants.ENCODDING_UTF8));
        }catch(Exception e) {
            e.printStackTrace();
        }

        OkHttpUtils.post().url(HOST + "?updateUser").params(params).build().execute(stringCallback);
    }

    /**
     * 我的
     *
     * 0,普通用户（未认证）；1，大V;2,中V;3，已认证用户；4，组织
     *
     * @param userId
     * @param userType
     * @param stringCallback
     */
    public static void getUserNumbers(String userId, String userType, StringCallback stringCallback){
        Map<String, String> params = new HashMap<>();
        params.put("project", PROJECT_NAME);
        params.put("userId", userId);
        params.put("userType", userType);

        OkHttpUtils.post().url(HOST + "?getUserNumbers").params(params).build().execute(stringCallback);
    }

    /**
     * 我发布的资源
     * @param userId
     * @param lastDataId
     * @param stringCallback
     */
    public static void getDataListBySelf(String userId, String lastDataId, StringCallback stringCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("project", PROJECT_NAME);
        params.put("userId", userId);
        params.put("lastDataId", lastDataId);
        params.put("row", Constants.PAGE_SIZE);

        OkHttpUtils.post().url(HOST + "?getDataListBySelf").params(params).build().execute(stringCallback);
    }

    /**
     * 我的粉丝
     * @param userId
     * @param stringCallback
     */
    public static void getFrans(String userId, StringCallback stringCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("project", PROJECT_NAME);
        params.put("userId", userId);

        OkHttpUtils.post().url(HOST + "?getFrans").params(params).build().execute(stringCallback);
    }

    /**
     * 我的关注
     * @param userId
     * @param stringCallback
     */
    public static void getFocusByMe(String userId, StringCallback stringCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("project", PROJECT_NAME);
        params.put("userId", userId);

        OkHttpUtils.post().url(HOST + "?getFocusByMe").params(params).build().execute(stringCallback);
    }

    /**
     * 收藏
     * @param userId
     * @param dataId
     * @param state
     * @param stringCallback
     */
    public static void shouCang(String userId, String dataId, String state, StringCallback stringCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("project", PROJECT_NAME);
        params.put("userId", userId);
        params.put("dataId", dataId);
        params.put("state", state);

        OkHttpUtils.post().url(HOST + "?shouCang").params(params).build().execute(stringCallback);
    }

    /**
     * 我的收藏
     * @param userId
     * @param stringCallback
     */
    public static void getXhyShouCangs(String userId, StringCallback stringCallback) {
        Map<String, String> params = new HashMap<>();
        params.put("project", PROJECT_NAME);
        params.put("userId", userId);

        OkHttpUtils.post().url(HOST + "?getXhyShouCangs").params(params).build().execute(stringCallback);
    }
}
