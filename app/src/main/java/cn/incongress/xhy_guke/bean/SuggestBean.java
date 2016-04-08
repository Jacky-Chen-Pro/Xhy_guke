package cn.incongress.xhy_guke.bean;

import java.util.List;

/**
 * Created by Jacky on 2016/4/8.
 */
public class SuggestBean {
    /**
     * tbtjList : [{"userId":16003,"userName":"王亚","hospital":"","userType":2,"userPic":""},{"userId":16010,"userName":"李武","hospital":"","userType":2,"userPic":""},{"userId":16011,"userName":"王耳","hospital":"","userType":2,"userPic":""}]
     * zvList : [{"userId":16003,"userName":"王亚","hospital":null,"userType":2,"userPic":"","focusMeCount":0,"readCount":507,"laudCount":6,"isFocus":0},{"userId":16010,"userName":"李武","hospital":null,"userType":2,"userPic":"","focusMeCount":0,"readCount":125,"laudCount":1,"isFocus":0},{"userId":16011,"userName":"王耳","hospital":null,"userType":2,"userPic":"","focusMeCount":0,"readCount":88,"laudCount":1,"isFocus":0}]
     * trList : [{"userId":16014,"userName":"李辉","hospital":null,"userType":2,"userPic":"","focusMeCount":0,"readCount":268,"laudCount":1,"isFocus":0},{"userId":16012,"userName":"张彦","hospital":null,"userType":2,"userPic":"","focusMeCount":0,"readCount":59,"laudCount":0,"isFocus":0},{"userId":16013,"userName":"王迪","hospital":null,"userType":2,"userPic":"","focusMeCount":0,"readCount":37,"laudCount":1,"isFocus":0}]
     * state : 0
     * msg :
     */

    private int state;
    private String msg;
    /**
     * userId : 16003
     * userName : 王亚
     * hospital :
     * userType : 2
     * userPic :
     */

    private List<TbtjListBean> tbtjList;
    /**
     * userId : 16003
     * userName : 王亚
     * hospital : null
     * userType : 2
     * userPic :
     * focusMeCount : 0
     * readCount : 507
     * laudCount : 6
     * isFocus : 0
     */

    private List<ZvListBean> zvList;
    /**
     * userId : 16014
     * userName : 李辉
     * hospital : null
     * userType : 2
     * userPic :
     * focusMeCount : 0
     * readCount : 268
     * laudCount : 1
     * isFocus : 0
     */

    private List<TrListBean> trList;

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

    public List<TbtjListBean> getTbtjList() {
        return tbtjList;
    }

    public void setTbtjList(List<TbtjListBean> tbtjList) {
        this.tbtjList = tbtjList;
    }

    public List<ZvListBean> getZvList() {
        return zvList;
    }

    public void setZvList(List<ZvListBean> zvList) {
        this.zvList = zvList;
    }

    public List<TrListBean> getTrList() {
        return trList;
    }

    public void setTrList(List<TrListBean> trList) {
        this.trList = trList;
    }

    public static class TbtjListBean {
        private int userId;
        private String userName;
        private String hospital;
        private int userType;
        private String userPic;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getHospital() {
            return hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }
    }

    public static class ZvListBean {
        private int userId;
        private String userName;
        private Object hospital;
        private int userType;
        private String userPic;
        private int focusMeCount;
        private int readCount;
        private int laudCount;
        private int isFocus;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getHospital() {
            return hospital;
        }

        public void setHospital(Object hospital) {
            this.hospital = hospital;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }

        public int getFocusMeCount() {
            return focusMeCount;
        }

        public void setFocusMeCount(int focusMeCount) {
            this.focusMeCount = focusMeCount;
        }

        public int getReadCount() {
            return readCount;
        }

        public void setReadCount(int readCount) {
            this.readCount = readCount;
        }

        public int getLaudCount() {
            return laudCount;
        }

        public void setLaudCount(int laudCount) {
            this.laudCount = laudCount;
        }

        public int getIsFocus() {
            return isFocus;
        }

        public void setIsFocus(int isFocus) {
            this.isFocus = isFocus;
        }
    }

    public static class TrListBean {
        private int userId;
        private String userName;
        private Object hospital;
        private int userType;
        private String userPic;
        private int focusMeCount;
        private int readCount;
        private int laudCount;
        private int isFocus;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getHospital() {
            return hospital;
        }

        public void setHospital(Object hospital) {
            this.hospital = hospital;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }

        public int getFocusMeCount() {
            return focusMeCount;
        }

        public void setFocusMeCount(int focusMeCount) {
            this.focusMeCount = focusMeCount;
        }

        public int getReadCount() {
            return readCount;
        }

        public void setReadCount(int readCount) {
            this.readCount = readCount;
        }

        public int getLaudCount() {
            return laudCount;
        }

        public void setLaudCount(int laudCount) {
            this.laudCount = laudCount;
        }

        public int getIsFocus() {
            return isFocus;
        }

        public void setIsFocus(int isFocus) {
            this.isFocus = isFocus;
        }
    }
}
