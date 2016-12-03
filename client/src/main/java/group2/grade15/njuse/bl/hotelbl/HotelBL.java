package group2.grade15.njuse.bl.hotelbl;

import group2.grade15.njuse.vo.HotelListVO;

/**
 * Created by Guo on 2016/12/3.
 */
public interface HotelBL {
    public HotelListVO getHotelListByCustomerID(int customerID);
}
