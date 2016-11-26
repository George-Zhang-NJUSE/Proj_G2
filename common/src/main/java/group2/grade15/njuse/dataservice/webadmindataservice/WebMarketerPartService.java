package group2.grade15.njuse.dataservice.webadmindataservice;

import group2.grade15.njuse.po.WebMarketerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by dell on 2016/11/25.
 */
public interface WebMarketerPartService extends Remote{
    public ResultMessage addWebMarketerInfo(WebMarketerPO webMarketerPO) throws RemoteException;
    public ArrayList<WebMarketerPO> getWebMarketerInfo() throws RemoteException;
    public ResultMessage modifyWebMarketerInfo(WebMarketerPO webMarketerPO) throws RemoteException;
    public ResultMessage deleteWebMarketer(String webMarketerID) throws RemoteException;
}
