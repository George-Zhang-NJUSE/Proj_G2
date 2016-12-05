package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.ProvincePO;

/**
 * Created by Guo on 2016/12/1.
 */
public class ProvinceVO {
    private String provinceName;
    private String provinceID;

    public ProvinceVO(ProvincePO po) {
        provinceName = po.getProvinceName();
        provinceID = po.getProvinceID();
    }

    public ProvinceVO(String provinceName, String id) {
        this.provinceName = provinceName;
        this.provinceID = id;
    }

    public String getProvinceID() {
        return provinceID;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public ProvincePO toPO() {
        return new ProvincePO(provinceName, provinceID);
    }
}
