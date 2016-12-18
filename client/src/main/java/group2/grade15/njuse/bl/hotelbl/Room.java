package group2.grade15.njuse.bl.hotelbl;

import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.po.RoomPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.RoomVO;

import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.Timestamp;

public class Room implements RoomBL{

    @Override
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

    @Override
    public int getSpareRoomNumInTime(RoomType type, int hotelID, Timestamp checkInTime, Timestamp checkOutTime){
        int nowSpareRoomNum;

        try {
            nowSpareRoomNum = RemoteHelper.getInstance().getOrderDataService().roomToBeAvailable(checkInTime, checkOutTime, type, hotelID);
        } catch (RemoteException e) {
            nowSpareRoomNum = 100000;
            e.printStackTrace();
        }

        int lastSpareRoom = 0;
        HotelPO hotel = null;
        try {
            hotel = RemoteHelper.getInstance().getHotelDataService().getHotel(hotelID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(hotel != null) {
            for (RoomPO room : hotel.getRoomList()) {
                if (room.getType() == type) {
                    lastSpareRoom = room.getSpareRoomNum();
                }
            }
        }

        int spareRoomNum = lastSpareRoom + nowSpareRoomNum;
        return spareRoomNum;
    }

}
