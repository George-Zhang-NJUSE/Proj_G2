package group2.grade15.njuse.po;

import java.io.Serializable;

public class ProvincePO implements Serializable {
    String provinceName;
    String provinceID;

    public ProvincePO(String province, String id) {
        this.provinceName = province;
        this.provinceID = id;
    }

    public String getProvinceID() {
        return provinceID;
    }

    public String getProvinceName() {
        return provinceName;
    }
}
