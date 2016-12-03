package group2.grade15.njuse.dataservice;

import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.utility.IDType;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by George on 2016/10/16.
 */
public interface OrderDataService extends Remote {

    public ArrayList<OrderPO> getList() throws RemoteException;//用于网站管理人员

    public ArrayList<OrderPO> getListByCustomer(int customerID) throws RemoteException;//用于customer

    public ArrayList<OrderPO> getListByHotel(int hotelID) throws RemoteException;//用于hotel

    public ResultMessage add(OrderPO po) throws RemoteException;

    public ResultMessage modify(int orderID, OrderState state) throws RemoteException;//只能修改状态



}
