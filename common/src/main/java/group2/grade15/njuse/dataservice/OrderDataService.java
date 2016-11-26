package group2.grade15.njuse.dataservice;

import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.utility.IDType;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by George on 2016/10/16.
 */
public interface OrderDataService extends Remote {

    public OrderPO get(int orderId) throws RemoteException;

    public ResultMessage add(OrderPO po) throws RemoteException;

    public ResultMessage modify(OrderPO po) throws RemoteException;

    public ArrayList<OrderPO> getList(int id, IDType type) throws RemoteException;

    public ArrayList<OrderPO> getList(Date date) throws RemoteException;

}
