package cn.incongress.xhy_guke.bean;

/**
 * Created by Jacky Chen on 2016/3/29 0029.
 * LaudList中的单元
 */
public class LaudListBean {
    private String userId;
    private String userPic;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    @Override
    public String toString() {
        return "LaudListBean{" +
                "userId=" + userId +
                ", userPic='" + userPic + '\'' +
                '}';
    }
}
