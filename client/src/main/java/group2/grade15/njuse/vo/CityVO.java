package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.CityPO;

/**
 * Created by Guo on 2016/11/30.
 */
public class CityVO {
    private String cityName;
    private String cityNum;

    public CityVO(CityPO po) {
        cityName = po.getCityName();
        cityNum = po.getCityNum();
    }

    public CityVO(String cityName, String cityNum) {
        this.cityName = cityName;
        this.cityNum = cityNum;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityNum() {
        return cityNum;
    }

    public CityPO toPO() {
        return new CityPO(cityName, cityNum);
    }
}
