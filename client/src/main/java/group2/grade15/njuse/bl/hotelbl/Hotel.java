package group2.grade15.njuse.bl.hotelbl;

import group2.grade15.njuse.blservice.HotelServ;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.po.RoomPO;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.RoomVO;

import javax.xml.transform.Result;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
public class Hotel implements HotelBL {

    public ResultMessage modifyInfo(HotelVO hotel) {
        ResultMessage result;
        try {
            result = RemoteHelper.getInstance().getHotelDataService().modify(hotel.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.CONNECTION_EXCEPTION;
        }

        return result;
    }

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

    public ResultMessage modifyRoomInfo(int hotelID, RoomVO roomInfo) {
        ResultMessage result;
        try {
            result = RemoteHelper.getInstance().getHotelDataService().modifyRoom(hotelID, roomInfo.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.CONNECTION_EXCEPTION;
        }

        return result;
    }

}
