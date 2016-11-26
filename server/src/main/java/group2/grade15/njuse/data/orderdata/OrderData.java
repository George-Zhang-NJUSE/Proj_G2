package group2.grade15.njuse.data.orderdata;

import group2.grade15.njuse.dataservice.OrderDataService;
import group2.grade15.njuse.po.OrderListPO;
import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.utility.IDType;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.util.Date;

/**
 * Created by George on 2016/11/13.
 */
public class OrderData implements OrderDataService {


    public OrderPO getOrder(int orderId) throws RemoteException {
        return null;
    }

    public ResultMessage add(OrderPO po) throws RemoteException {
        return null;
    }

    public ResultMessage modify(OrderPO po) throws RemoteException {
        return null;
    }

    public OrderListPO getList(int id, IDType type) throws RemoteException {
        return null;
    }

    public OrderListPO getList(Date date) throws RemoteException {
        return null;
    }
}
