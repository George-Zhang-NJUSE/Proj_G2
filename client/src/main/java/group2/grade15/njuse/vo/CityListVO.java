package group2.grade15.njuse.vo;

import java.util.ArrayList;

/**
 * Created by guo on 2016/12/2.
 */
public class CityListVO {
    ArrayList<CityVO> list;

    public CityListVO(ArrayList<CityVO> list) {
        this.list = list;
    }

    public ArrayList<CityVO> getList() {
        return list;
    }
}
