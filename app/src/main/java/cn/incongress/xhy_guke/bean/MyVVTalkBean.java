package cn.incongress.xhy_guke.bean;

import java.util.List;

/**
 * Created by Jacky on 2016/4/13.
 * 我发布的V言V语的JAVA BEAN
 */
public class MyVVTalkBean {

    /**
     * pageState : 0
     * dataList : [{"dataId":493,"title":"警惕：脑出血竟也能表现为 TIA 症状","showName":"老年医学学组","company":"","userType":4,"isNiming":0,"readCount":1,"type":2,"isImg":0,"bgImg":"http://114.80.201.49/xhy_files/bingli/bingli_493_1/bl_pic.png","imgs":"http://114.80.201.49/xhy_files/bingli/bingli_493_1/bl_pic.png","bgWidth":0,"bgHeight":0,"commentCount":0,"laudCount":0,"userPic":"","showTime":"1分钟前"},{"dataId":472,"title":"高血压性心脏病病例分析","showName":"老年医学学组","company":"","userType":4,"isNiming":0,"readCount":78,"type":2,"isImg":0,"bgImg":"http://114.80.201.49/xhy_files/bingli/bingli_472_1/bl_pic.png","imgs":"http://114.80.201.49/xhy_files/bingli/bingli_472_1/bl_pic.png","bgWidth":0,"bgHeight":0,"commentCount":1,"laudCount":1,"userPic":"","showTime":"03-30 11:23"}]
     * state : 1
     * msg :
     */

    private int pageState;
    private int state;
    private String msg;
    /**
     * dataId : 493
     * title : 警惕：脑出血竟也能表现为 TIA 症状
     * showName : 老年医学学组
     * company :
     * userType : 4
     * isNiming : 0
     * readCount : 1
     * type : 2
     * isImg : 0
     * bgImg : http://114.80.201.49/xhy_files/bingli/bingli_493_1/bl_pic.png
     * imgs : http://114.80.201.49/xhy_files/bingli/bingli_493_1/bl_pic.png
     * bgWidth : 0
     * bgHeight : 0
     * commentCount : 0
     * laudCount : 0
     * userPic :
     * showTime : 1分钟前
     */

    private List<DataListBean> dataList;

    public int getPageState() {
        return pageState;
    }

    public void setPageState(int pageState) {
        this.pageState = pageState;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        private int dataId;
        private String title;
        private String showName;
        private String company;
        private int userType;
        private int isNiming;
        private int readCount;
        private int type;
        private int isImg;
        private String bgImg;
        private String imgs;
        private int bgWidth;
        private int bgHeight;
        private int commentCount;
        private int laudCount;
        private String userPic;
        private String showTime;

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

        public int getReadCount() {
            return readCount;
        }

        public void setReadCount(int readCount) {
            this.readCount = readCount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
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

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getLaudCount() {
            return laudCount;
        }

        public void setLaudCount(int laudCount) {
            this.laudCount = laudCount;
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
    }
}
