package group2.grade15.njuse.vo;

import java.util.ArrayList;

/**
 * Created by Guo on 2016/12/2.
 */
public class ProvinceListVO {
    ArrayList<ProvinceVO> list;

    public ProvinceListVO(ArrayList<ProvinceVO> list){
        this.list=list;
    }

    public ArrayList<ProvinceVO> getList() {
        return list;
    }
}
