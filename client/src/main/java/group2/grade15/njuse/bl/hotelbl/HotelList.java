package group2.grade15.njuse.bl.hotelbl;

import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;

import java.util.ArrayList;

/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
abstract public class HotelList {
    private ArrayList<HotelVO> hotelList;

    abstract public HotelListVO getHotelList();
}
