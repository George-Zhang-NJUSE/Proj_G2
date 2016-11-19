package group2.grade15.njuse.dataservice;

import group2.grade15.njuse.po.PromotionPO;
import group2.grade15.njuse.po.WebPromotionListPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public interface WebPromotionDataService extends Remote {

    public WebPromotionListPO getList() throws RemoteException;

    public ResultMessage modify(PromotionPO po) throws RemoteException;

    public ResultMessage remove(PromotionPO po) throws RemoteException;

    public ResultMessage add(PromotionPO po) throws RemoteException;

}
