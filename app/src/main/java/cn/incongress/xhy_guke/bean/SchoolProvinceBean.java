package cn.incongress.xhy_guke.bean;

/**
 * Created by Jacky on 2015/10/16.
 */
public class SchoolProvinceBean {
    private int schoolProvinceId;
    private String schoolProvinceName;

    public int getSchoolProvinceId() {
        return schoolProvinceId;
    }

    public void setSchoolProvinceId(int schoolProvinceId) {
        this.schoolProvinceId = schoolProvinceId;
    }

    public String getSchoolProvinceName() {
        return schoolProvinceName;
    }

    public void setSchoolProvinceName(String schoolProvinceName) {
        this.schoolProvinceName = schoolProvinceName;
    }

    @Override
    public String toString() {
        return "SchoolProvinceBean{" +
                "schoolProvinceId=" + schoolProvinceId +
                ", schoolProvinceName='" + schoolProvinceName + '\'' +
                '}';
    }

    public SchoolProvinceBean(int schoolProvinceId, String schoolProvinceName) {
        this.schoolProvinceId = schoolProvinceId;
        this.schoolProvinceName = schoolProvinceName;
    }
}
