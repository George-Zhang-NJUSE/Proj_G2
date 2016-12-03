package group2.grade15.njuse.data.orderdata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.OrderDataService;
import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.utility.IDType;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by George on 2016/11/13.
 */
public class OrderDatabaseImpl implements OrderDataService {
    private DatabaseMySql mySql=null;
    private Connection orderDatabase=null;

    public OrderDatabaseImpl(DatabaseInfo info) throws RemoteException{
        mySql=new DatabaseMySql(info);
        orderDatabase=mySql.init();
    }

    @Override
    public ArrayList<OrderPO> getList() throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<OrderPO> getListByCustomer(int customerID) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<OrderPO> getListByHotel(int hotelID) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage add(OrderPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modify(int orderID, OrderState state) throws RemoteException {
        return null;
    }
}
