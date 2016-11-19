package bl.hotelbl;

import vo.HotelListVO;
import vo.HotelVO;

import java.util.ArrayList;

/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
abstract public class HotelList {
    private ArrayList<HotelVO> hotelList;

    abstract public HotelListVO getHotelList();
}
