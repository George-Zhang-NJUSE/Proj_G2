package group2.grade15.njuse.bl.hotelbl;

import group2.grade15.njuse.blservice.HotelServ;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.RoomVO;

import java.rmi.RemoteException;

/**
 * Created by 果宝 on 2016/12/4.
 */
public class HotelController  implements HotelServ, GetHotelListBL {
    @Override
    public ResultMessage modifyInfo(HotelVO hotel) {
        ResultMessage result = ResultMessage.FAILED;
        try {
            result = RemoteHelper.getInstance().getHotelDataService().modify(hotel.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.CONNECTION_EXCEPTION;
        }

        return result;
    }

    @Override
    public HotelVO getInfo(int hotelID) {
        HotelPO hotel = null;
        try {
            hotel = RemoteHelper.getInstance().getHotelDataService().getHotel(hotelID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(hotel != null) {
            return new HotelVO(hotel);
        } else {
            return null;
        }
    }

    @Override
    public ResultMessage modifyRoomInfo(int hotelID, RoomVO roomInfo) {
        ResultMessage result = ResultMessage.FAILED;
        try {
            result = RemoteHelper.getInstance().getHotelDataService().modifyRoom(hotelID, roomInfo.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.CONNECTION_EXCEPTION;
        }

        return result;
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
    public HotelListVO getBookedHotelList(int customerID) {
        return null;
    }
}
