package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.DistrictPO;

/**
 * Created by Guo on 2016/11/30.
 */
public class DistrictVO {
    private String districtName;
    private String districtNum;

    public DistrictVO(DistrictPO po) {
        districtName = po.getDistrictName();
        districtNum = po.getDistrictNum();
    }

    public DistrictVO(String districtName, String districtNum) {
        this.districtName = districtName;
        this.districtNum = districtNum;
    }

    public String getDistrictName() {
        return districtName;
    }

    public String getDistrictNum() {
        return districtNum;
    }

    public DistrictPO toPO() {
        return new DistrictPO(districtName, districtNum);
    }

    public String toString(){
        return districtName;
    }
}
