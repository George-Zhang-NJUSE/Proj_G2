package group2.grade15.njuse.dataservice.webadmindataservice;

import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by dell on 2016/11/25.
 */
public interface CustomerPartService extends Remote{
    public CustomerPO getCustomerInfo(int customerID) throws RemoteException;

    public ResultMessage modifyCustomerInfo(CustomerPO customerPO) throws RemoteException;
}
