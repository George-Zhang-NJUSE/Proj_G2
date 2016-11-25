package group2.grade15.njuse.dataservice.webmarketerdataservice;

import group2.grade15.njuse.po.WebMarketerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public interface WebMarketerDataService extends Remote {
    //这里删掉的add,remove和modify方法将在WebAdmin里实现

    public WebMarketerPO get(String webMarketerId) throws RemoteException;
}
