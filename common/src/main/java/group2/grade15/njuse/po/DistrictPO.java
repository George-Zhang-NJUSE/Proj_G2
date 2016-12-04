package group2.grade15.njuse.po;

import java.io.Serializable;

public class DistrictPO implements Serializable {
    private String districtName;
    private String districtNum;

    public DistrictPO(String districtName, String districtNum) {
        this.districtName = districtName;
        this.districtNum = districtNum;
    }

    public String getDistrictName() {
        return districtName;
    }

    public String getDistrictNum() {
        return districtNum;
    }


}
