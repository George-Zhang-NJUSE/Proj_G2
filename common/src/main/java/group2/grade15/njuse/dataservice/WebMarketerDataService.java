package group2.grade15.njuse.dataservice;

import group2.grade15.njuse.po.WebMarketerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public interface WebMarketerDataService extends Remote {

    public WebMarketerPO get(int webMarketerId) throws RemoteException;

    public ResultMessage add(WebMarketerPO po) throws RemoteException;

    public ResultMessage modify(WebMarketerPO po) throws RemoteException;

    public ResultMessage remove(int webMarketerId) throws RemoteException;

}
