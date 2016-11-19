package group2.grade15.njuse.dataservice;

import group2.grade15.njuse.po.WebAdminPO;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public interface WebAdminDataService extends Remote{

    public WebAdminPO get(int webAdminId) throws RemoteException;

}
