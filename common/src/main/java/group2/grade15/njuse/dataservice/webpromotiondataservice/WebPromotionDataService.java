package group2.grade15.njuse.dataservice.webpromotiondataservice;

import group2.grade15.njuse.po.RankPO;
import group2.grade15.njuse.po.WebPromotionPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by George on 2016/10/16.
 */
public interface WebPromotionDataService extends Remote {

    public ArrayList<WebPromotionPO> getList() throws RemoteException;

    public ResultMessage modify(WebPromotionPO po) throws RemoteException;

    public ResultMessage remove(int promotionID) throws RemoteException;

    public ResultMessage add(WebPromotionPO po) throws RemoteException;

    public ArrayList<RankPO> getRank() throws RemoteException;

    public ResultMessage modifyRank(RankPO rankPO) throws RemoteException;
}
