package group2.grade15.njuse.dataservice;

import group2.grade15.njuse.po.HotelManagerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public interface HotelManagerDataService extends Remote{

    public HotelManagerPO get(int hotelManagerId) throws RemoteException;

    public ResultMessage add(HotelManagerPO po) throws RemoteException;

    public ResultMessage modify(HotelManagerPO po) throws RemoteException;

    public ResultMessage remove(int hotelManagerId) throws RemoteException;

}
