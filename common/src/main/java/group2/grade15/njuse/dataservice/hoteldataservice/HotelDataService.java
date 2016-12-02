package group2.grade15.njuse.dataservice.hoteldataservice;

import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.po.RoomPO;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public interface HotelDataService extends Remote {

    public HotelPO getHotel(int hotelId) throws RemoteException;

    public ResultMessage modify(HotelPO po) throws RemoteException;

    public ResultMessage modifyRoom(int hotelId, RoomPO po) throws RemoteException;

    public ResultMessage addRoomType(int hotelID,RoomPO po) throws RemoteException;

    public ResultMessage deleteRoomType(int hotelID, RoomType type) throws RemoteException;

    public ResultMessage uploadPic(byte[][] picList, int hotelID) throws RemoteException;

    public ResultMessage deletePic(int picNum, int hotelID) throws RemoteException;
}
