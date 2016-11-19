package group2.grade15.njuse.data.customerdata;


import group2.grade15.njuse.dataservice.CustomerDataService;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by George on 2016/11/13.
 */
public class CustomerData implements CustomerDataService {

    public CustomerPO get(int customerId) throws RemoteException {
        return null;
    }

    public ResultMessage add(CustomerPO po) throws RemoteException {
        return null;
    }

    public ResultMessage modify(CustomerPO po) throws RemoteException {
        return null;
    }
}
