package cn.incongress.xhy_guke.bean;

/**
 * V言V语
 */
public class VVTalkBean {
    private int dataId; //新闻唯一标示id
    private int isImg; //是否有图片 1 有图片 0没有图片
    private String bgImg;// 图片下载地址 1的是具体的下载地址 0返回空字符串
    private int bgWidth  ;//图片宽度
    private int bgHeight; //图片高度
    private String title;//标题
    private int userType; //创建人身份：0,普通用户（未认证）；1，大V;2,中V;3，已认证用户
    private int isNiming; //是否匿名
    private String showName;//显示名字
    private String company;//单位
    private int type;// 1新闻 2病例 3发帖4课件5视频
    private String userPic; //返回发帖人头像
    private int readCount; //阅读数
    private String showTime; //显示时间

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public int getIsImg() {
        return isImg;
    }

    public void setIsImg(int isImg) {
        this.isImg = isImg;
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }

    public int getBgWidth() {
        return bgWidth;
    }

    public void setBgWidth(int bgWidth) {
        this.bgWidth = bgWidth;
    }

    public int getBgHeight() {
        return bgHeight;
    }

    public void setBgHeight(int bgHeight) {
        this.bgHeight = bgHeight;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getIsNiming() {
        return isNiming;
    }

    public void setIsNiming(int isNiming) {
        this.isNiming = isNiming;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
