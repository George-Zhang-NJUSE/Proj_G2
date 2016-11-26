package group2.grade15.njuse.dataservice.cusotmerdataservice;

import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public interface CustomerDataService extends Remote{

    public CustomerPO getCustomer(int customerId) throws RemoteException;

    public CustomerPO add(CustomerPO po) throws RemoteException;

    public ResultMessage modify(CustomerPO po) throws RemoteException;

}
