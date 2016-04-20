package cn.incongress.xhy_guke.bean;

/**
 * Created by Administrator on 2015/7/9.
 */
public class CityBean {
    private int cityId;
    private int provinceId;
    private String name;
    private String pinyin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public CityBean(int cityId, String name, String pinyin, int provinceId) {
        this.cityId = cityId;
        this.name = name;
        this.pinyin = pinyin;
        this.provinceId = provinceId;
    }

    @Override
    public String toString() {
        return "CityBean{" +
                "cityId=" + cityId +
                ", provinceId=" + provinceId +
                ", name='" + name + '\'' +
                ", pinyin='" + pinyin + '\'' +
                '}';
    }
}
