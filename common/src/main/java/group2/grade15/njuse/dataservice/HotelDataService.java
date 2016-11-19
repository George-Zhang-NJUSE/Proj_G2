package group2.grade15.njuse.dataservice;

import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.po.RoomPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public interface HotelDataService extends Remote {

    public HotelPO get(int hotelId) throws RemoteException;

    public ResultMessage add(HotelPO po) throws RemoteException;

    public ResultMessage modify(HotelPO po) throws RemoteException;

    public ResultMessage modifyRoom(int hotelId, RoomPO po) throws RemoteException;

    public ResultMessage remove(int hotelId) throws RemoteException;

}
