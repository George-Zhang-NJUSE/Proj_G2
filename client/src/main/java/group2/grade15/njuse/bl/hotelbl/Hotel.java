package group2.grade15.njuse.bl.hotelbl;

import group2.grade15.njuse.blservice.HotelServ;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.po.RoomPO;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.RoomVO;

import java.util.ArrayList;

/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
public class Hotel implements HotelServ, HotelBL{

    @Override
    public ResultMessage modifyInfo(HotelVO hotel) {
        return null;
    }

    @Override
    public HotelVO getInfo(int hotelID) {
        return null;
    }

    @Override
    public ResultMessage modifyRoomInfo(RoomVO roomInfo) {
        return null;
    }

    @Override
    public ResultMessage addCompany(int customerID) {
        return null;
    }

    @Override
    public ResultMessage deleteCompany(int customerID) {
        return null;
    }

    @Override
    public HotelListVO getHotelListByCustomerID(int customerID) {
        return null;
    }
}
