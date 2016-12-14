package group2.grade15.njuse.dataservice.creditdataservice;

import group2.grade15.njuse.po.CreditPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by George on 2016/10/16.
 */
public interface CreditDataService extends Remote {

    public ArrayList<CreditPO> getHistory(int customerId) throws RemoteException;

    public ResultMessage add(CreditPO po) throws RemoteException;

}
