package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.CbdPO;

/**
 * Created by Guo on 2016/11/30.
 */
public class CbdVO {
    private String cbdName;
    private String cbdNum;

    public CbdVO(CbdPO po) {
        cbdName = po.getCbdName();
        cbdNum = po.getCbdNum();
    }

    public CbdVO(String cbdName, String cbdNum) {
        this.cbdName = cbdName;
        this.cbdNum = cbdNum;
    }

    public String getCbdName() {
        return cbdName;
    }

    public String getCbdNum() {
        return cbdNum;
    }

    public CbdPO toPO() {
        return new CbdPO(cbdName, cbdNum);
    }
}
