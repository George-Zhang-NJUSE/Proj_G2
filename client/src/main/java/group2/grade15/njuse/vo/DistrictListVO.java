package group2.grade15.njuse.vo;

import java.util.ArrayList;

/**
 * Created by Guo on 2016/12/2.
 */
public class DistrictListVO {
    ArrayList<DistrictVO> list;

    public DistrictListVO(ArrayList<DistrictVO> list){
        this.list=list;
    }

    public ArrayList<DistrictVO> getList() {
        return list;
    }
}
