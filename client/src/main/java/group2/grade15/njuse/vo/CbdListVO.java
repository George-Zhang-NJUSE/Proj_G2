package group2.grade15.njuse.vo;

import java.util.ArrayList;

/**
 * Created by Guo on 2016/12/2.
 */
public class CbdListVO {
    ArrayList<CbdVO> list;

    public CbdListVO(ArrayList<CbdVO> list) {
        this.list = list;
    }

    public ArrayList<CbdVO> getList() {
        return list;
    }
}
