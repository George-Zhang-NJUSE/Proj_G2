package group2.grade15.njuse.dataservice.webadmindataservice;

import group2.grade15.njuse.po.HotelManagerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by dell on 2016/11/25.
 */
public interface HotelManagerPartService extends Remote{
    public HotelManagerPO getHotelManagerInfo(int hotelManagerID) throws RemoteException;
    public ResultMessage modifyHotelManagerInfo(HotelManagerPO hotelManagerPO) throws RemoteException;
}
