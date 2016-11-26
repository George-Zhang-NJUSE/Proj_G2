package group2.grade15.njuse.data.hoteldata;

import group2.grade15.njuse.dataservice.HotelDataService;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.po.RoomPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
public class HotelData implements HotelDataService {

    public HotelPO getHotel(int hotelId) throws RemoteException {
        return null;
    }

    public ResultMessage add(HotelPO po) throws RemoteException {
        return null;
    }

    public ResultMessage modify(HotelPO po) throws RemoteException {
        return null;
    }

    public ResultMessage modifyRoom(int hotelId, RoomPO po) throws RemoteException {
        return null;
    }

    public ResultMessage remove(int hotelId) throws RemoteException {
        return null;
    }
}
