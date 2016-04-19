package cn.incongress.xhy_guke.bean;

import java.util.List;

/**
 * Created by Jacky Chen on 2016/3/29 0029.
 * 详情页Bean
 */
public class VVTalkDetailBean {
    private int userType; //用户类型
    private int createUserId; //用户的id，主要用于查询个人信息
    private String createUser;//创建人
    private String hospital;//医院
    private String author;//实际作者
    private int isNiming; //是否是昵称显示
    private int dataId; //资源唯一标识
    private String title;//标题
    private String imgs;//图片下载地址，以,隔开
    private String time;//显示时间
    private int dataVersion;//数据版本
    private String htmlUrl;//html下载地址\视频地址\课件地址
    private String content;//帖子内容
    private String dataDescribe;//主诉、简介
    private String pdfDataUrl;//pdf地址
    private String dataSize;//课件附件文件大小
    private String pdfDataSize;//Pdf附件大小
    private String dataType;//附件类型 1.ppt 2word 3pdf
    private int readCount;//阅读数

    private int isLaud; //对某篇文章是否点赞
    private int laudCount;//点赞总数
    private List<LaudListBean> laudList; //点赞榜集合
    private int isVote; //是否含有投票
    private int commentCount; //评论数
    private int isShouCang; //是否收藏
    private String authorPic;//作者头像
    private List<MoreXhyDataListBean> moreXhyDataList;// 新增的五条推荐文章集合

    public int getIsShouCang() {
        return isShouCang;
    }

    public void setIsShouCang(int isShouCang) {
        this.isShouCang = isShouCang;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getIsNiming() {
        return isNiming;
    }

    public void setIsNiming(int isNiming) {
        this.isNiming = isNiming;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(int dataVersion) {
        this.dataVersion = dataVersion;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDataDescribe() {
        return dataDescribe;
    }

    public void setDataDescribe(String dataDescribe) {
        this.dataDescribe = dataDescribe;
    }

    public String getPdfDataUrl() {
        return pdfDataUrl;
    }

    public void setPdfDataUrl(String pdfDataUrl) {
        this.pdfDataUrl = pdfDataUrl;
    }

    public String getDataSize() {
        return dataSize;
    }

    public void setDataSize(String dataSize) {
        this.dataSize = dataSize;
    }

    public String getPdfDataSize() {
        return pdfDataSize;
    }

    public void setPdfDataSize(String pdfDataSize) {
        this.pdfDataSize = pdfDataSize;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getIsLaud() {
        return isLaud;
    }

    public void setIsLaud(int isLaud) {
        this.isLaud = isLaud;
    }

    public int getLaudCount() {
        return laudCount;
    }

    public void setLaudCount(int laudCount) {
        this.laudCount = laudCount;
    }

    public List<LaudListBean> getLaudList() {
        return laudList;
    }

    public void setLaudList(List<LaudListBean> laudList) {
        this.laudList = laudList;
    }

    public int getIsVote() {
        return isVote;
    }

    public void setIsVote(int isVote) {
        this.isVote = isVote;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getAuthorPic() {
        return authorPic;
    }

    public void setAuthorPic(String authorPic) {
        this.authorPic = authorPic;
    }

    public List<MoreXhyDataListBean> getMoreXhyDataList() {
        return moreXhyDataList;
    }

    public void setMoreXhyDataList(List<MoreXhyDataListBean> moreXhyDataList) {
        this.moreXhyDataList = moreXhyDataList;
    }
}
