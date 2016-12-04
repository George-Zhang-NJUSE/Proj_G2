package group2.grade15.njuse.po;

import java.io.Serializable;

public class CbdPO implements Serializable {
    private String cbdName;
    private String cbdNum;

    public CbdPO(String cbdName, String cbdNum) {

        this.cbdName = cbdName;
        this.cbdNum = cbdNum;
    }

    public String getCbdName() {
        return cbdName;
    }

    public String getCbdNum() {
        return cbdNum;
    }
}
