package group2.grade15.njuse.data.webadmindata;

import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by dell on 2016/11/25.
 */
public interface CustomerPartService extends Remote {
    public ArrayList<CustomerPO> getCustomerInfo() throws RemoteException;

    public ResultMessage modifyCustomerInfo(CustomerPO customerPO) throws RemoteException;
}
