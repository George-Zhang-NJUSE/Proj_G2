package group2.grade15.njuse.dataservice.hotelmanagerdataservice;

import group2.grade15.njuse.po.HotelManagerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public interface HotelManagerDataService extends Remote{
    //这里删掉的add和remove方法将在WebAdmin里实现

    public HotelManagerPO getHotelManager(int hotelManagerId) throws RemoteException;

    public ResultMessage modify(HotelManagerPO po) throws RemoteException;

}
