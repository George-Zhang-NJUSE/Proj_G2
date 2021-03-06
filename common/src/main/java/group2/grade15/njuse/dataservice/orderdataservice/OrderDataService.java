package group2.grade15.njuse.dataservice.orderdataservice;

import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by George on 2016/10/16.
 */
public interface OrderDataService extends Remote {

    public ArrayList<OrderPO> getUnexecutedList() throws RemoteException;

    public ArrayList<OrderPO> getAbnormalList() throws RemoteException;

    public OrderPO getOrder(int orderID) throws RemoteException;

    public ArrayList<OrderPO> getListByCustomer(int customerID) throws RemoteException;

    public ArrayList<OrderPO> getListByHotel(int hotelID) throws RemoteException;

    public int roomToBeAvailable(Timestamp checkIn, Timestamp checkOut, RoomType type, int hotelID) throws RemoteException;

    public ResultMessage addOrder(OrderPO po) throws RemoteException;

    public ResultMessage modifyOrder(int orderID, OrderState state) throws RemoteException;

    public ResultMessage updateTime(Timestamp checkIn,Timestamp checkOut,int orderID) throws RemoteException;

}
