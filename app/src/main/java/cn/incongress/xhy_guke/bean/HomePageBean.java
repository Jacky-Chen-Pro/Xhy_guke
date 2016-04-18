package cn.incongress.xhy_guke.bean;

import java.util.List;

/**
 * Created by Jacky on 2016/4/18.
 */
public class HomePageBean {

    /**
     * state : 1
     * msg :
     * pageState : 0
     * dataList : [{"dataId":527,"title":"%E5%8C%BF%E5%90%8D%E5%8F%91%E5%B8%96","company":"","readCount":14,"showName":"老年医学学组","isNiming":0,"type":3,"isImg":1,"bgImg":"xhy_files/dataPost/dataPost_527_1/dataPost_527_1_1460962655019.jpg","bgWidth":0,"bgHeight":0,"userPic":"","timeShow":"1小时前"},{"dataId":526,"title":"%E5%8E%8B%E7%BC%A9%E5%9B%9B%E5%88%86%E4%B9%8B%E4%B8%80","company":"","readCount":8,"showName":"老年医学学组","isNiming":0,"type":3,"isImg":1,"bgImg":"xhy_files/dataPost/dataPost_526_1/dataPost_526_1_1460961415376.jpg","bgWidth":0,"bgHeight":0,"userPic":"","timeShow":"2小时前"},{"dataId":525,"title":"%E6%B5%8B%E8%AF%95%E5%8E%8B%E7%BC%A9%E6%AF%94%E4%BE%8B","company":"","readCount":11,"showName":"老年医学学组","isNiming":0,"type":3,"isImg":0,"userPic":"","timeShow":"2小时前"},{"dataId":524,"title":"%E9%9A%8F%E6%89%8B%E6%8B%8D%E4%B8%80%E6%8B%8D","company":"","readCount":14,"showName":"老年医学学组","isNiming":0,"type":3,"isImg":1,"bgImg":"xhy_files/dataPost/dataPost_524_1/dataPost_524_1_1460960353089.jpg","bgWidth":0,"bgHeight":0,"userPic":"","timeShow":"2小时前"},{"dataId":523,"title":"%E5%90%83%E7%82%B9%E6%B0%B4%E6%9E%9C","company":"","readCount":2,"showName":"老年医学学组","isNiming":0,"type":3,"isImg":1,"bgImg":"xhy_files/dataPost/dataPost_523_1/dataPost_523_1_1460960290735.jpg","bgWidth":0,"bgHeight":0,"userPic":"","timeShow":"2小时前"},{"dataId":522,"title":"%E6%88%91%E5%8F%AA%E6%98%AF%E5%8F%91%E7%82%B9%E5%9B%BE%E7%89%87%EF%BC%8C%E4%B8%8D%E8%A6%81%E6%89%93%E6%88%91","company":"","readCount":5,"showName":"老年医学学组","isNiming":0,"type":3,"isImg":1,"bgImg":"xhy_files/dataPost/dataPost_522_1/dataPost_522_1_1460960112053.jpg","bgWidth":0,"bgHeight":0,"userPic":"","timeShow":"2小时前"},{"dataId":493,"title":"警惕：脑出血竟也能表现为 TIA 症状","company":"","readCount":93,"showName":"老年医学学组","isNiming":0,"type":2,"isImg":0,"bgImg":"xhy_files/bingli/bingli_493_1/bl_pic.png","bgWidth":0,"bgHeight":0,"userPic":"","timeShow":"04-13 15:10"},{"dataId":472,"title":"高血压性心脏病病例分析","company":"","readCount":92,"showName":"老年医学学组","isNiming":0,"type":2,"isImg":0,"bgImg":"xhy_files/bingli/bingli_472_1/bl_pic.png","bgWidth":0,"bgHeight":0,"userPic":"","timeShow":"03-30 11:23"}]
     */

    private int state;
    private String msg;
    private int pageState;
    /**
     * dataId : 527
     * title : %E5%8C%BF%E5%90%8D%E5%8F%91%E5%B8%96
     * company :
     * readCount : 14
     * showName : 老年医学学组
     * isNiming : 0
     * type : 3
     * isImg : 1
     * bgImg : xhy_files/dataPost/dataPost_527_1/dataPost_527_1_1460962655019.jpg
     * bgWidth : 0
     * bgHeight : 0
     * userPic :
     * timeShow : 1小时前
     */

    private List<DataListBean> dataList;

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

    public int getPageState() {
        return pageState;
    }

    public void setPageState(int pageState) {
        this.pageState = pageState;
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
        private String company;
        private int readCount;
        private String showName;
        private int isNiming;
        private int type;
        private int isImg;
        private String bgImg;
        private int bgWidth;
        private int bgHeight;
        private String userPic;
        private String timeShow;

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

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public int getReadCount() {
            return readCount;
        }

        public void setReadCount(int readCount) {
            this.readCount = readCount;
        }

        public String getShowName() {
            return showName;
        }

        public void setShowName(String showName) {
            this.showName = showName;
        }

        public int getIsNiming() {
            return isNiming;
        }

        public void setIsNiming(int isNiming) {
            this.isNiming = isNiming;
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

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }

        public String getTimeShow() {
            return timeShow;
        }

        public void setTimeShow(String timeShow) {
            this.timeShow = timeShow;
        }
    }
}
