package cn.incongress.xhy_guke.bean;

/**
 * Created by Jacky on 2016/4/18.
 */
public class UserByHeadBean {

    /**
     * userType : 4
     * trueName : 老年医学学组
     * hospitalName :
     * remark :
     * focusCount : 0
     * isFocus : 0
     * dataCountTrue : 8
     * state : 1
     * msg :
     */

    private int userType;
    private String trueName;
    private String hospitalName;
    private String remark;
    private int focusCount;
    private int isFocus;
    private int dataCountTrue;
    private int state;
    private String msg;
    private String userPic;

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getFocusCount() {
        return focusCount;
    }

    public void setFocusCount(int focusCount) {
        this.focusCount = focusCount;
    }

    public int getIsFocus() {
        return isFocus;
    }

    public void setIsFocus(int isFocus) {
        this.isFocus = isFocus;
    }

    public int getDataCountTrue() {
        return dataCountTrue;
    }

    public void setDataCountTrue(int dataCountTrue) {
        this.dataCountTrue = dataCountTrue;
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
}
