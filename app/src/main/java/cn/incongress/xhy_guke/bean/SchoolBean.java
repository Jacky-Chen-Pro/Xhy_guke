package cn.incongress.xhy_guke.bean;

/**
 * Created by Jacky on 2015/10/16.
 */
public class SchoolBean {
    private int schoolId;
    private String schoolName;
    private int schoolProvinceId;

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getSchoolProvinceId() {
        return schoolProvinceId;
    }

    public void setSchoolProvinceId(int schoolProvinceId) {
        this.schoolProvinceId = schoolProvinceId;
    }

    @Override
    public String toString() {
        return "SchoolBean{" +
                "schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                ", schoolProvinceId=" + schoolProvinceId +
                '}';
    }

    public SchoolBean(int schoolId, String schoolName, int schoolProvinceId) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.schoolProvinceId = schoolProvinceId;
    }
}
