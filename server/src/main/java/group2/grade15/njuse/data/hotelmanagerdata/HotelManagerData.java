package group2.grade15.njuse.data.hotelmanagerdata;

import group2.grade15.njuse.dataservice.HotelManagerDataService;
import group2.grade15.njuse.po.HotelManagerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
public class HotelManagerData implements HotelManagerDataService{

    public HotelManagerPO get(int hotelManagerId) throws RemoteException {
        return null;
    }

    public ResultMessage add(HotelManagerPO po) throws RemoteException {
        return null;
    }

    public ResultMessage modify(HotelManagerPO po) throws RemoteException {
        return null;
    }

    public ResultMessage remove(int hotelManagerId) throws RemoteException {
        return null;
    }
}
