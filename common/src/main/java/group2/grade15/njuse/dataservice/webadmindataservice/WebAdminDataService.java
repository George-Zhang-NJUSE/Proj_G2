package group2.grade15.njuse.dataservice.webadmindataservice;

import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.po.WebAdminPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public interface WebAdminDataService extends Remote{
    public WebAdminPO getWebAdmin(String webAdminId) throws RemoteException;
}
