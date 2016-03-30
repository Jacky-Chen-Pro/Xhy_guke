package cn.incongress.xhy_guke.bean;

/**
 * Created by Jacky Chen on 2016/3/29 0029.
 * 新增的五条数据类型Bean
 */
public class MoreXhyDataListBean {
    private int dataId;
    private int isImg;
    private String bgImg;
    private int bgWidth;
    private int bgHeight;
    private String title;
    private int userType;
    private int isNiming;
    private String showName;
    private String company;
    private int type;
    private String userPic;
    private String showTime;

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

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    @Override
    public String toString() {
        return "MoreXhyDataListBean{" +
                "dataId=" + dataId +
                ", isImg=" + isImg +
                ", bgImg='" + bgImg + '\'' +
                ", bgWidth=" + bgWidth +
                ", bgHeight=" + bgHeight +
                ", title='" + title + '\'' +
                ", userType=" + userType +
                ", isNiming=" + isNiming +
                ", showName='" + showName + '\'' +
                ", company='" + company + '\'' +
                ", type=" + type +
                ", userPic='" + userPic + '\'' +
                ", showTime='" + showTime + '\'' +
                '}';
    }
}
