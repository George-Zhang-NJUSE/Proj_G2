package group2.grade15.njuse.dataservice;

import group2.grade15.njuse.po.CommentListPO;
import group2.grade15.njuse.po.CommentPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public interface CommentDataService extends Remote {

    public ResultMessage add(CommentPO po) throws RemoteException;

    public ResultMessage modify(CommentPO po) throws RemoteException;

    public CommentListPO getHotelComments(int hotelID) throws RemoteException;

    public CommentListPO getCustomerComments(int customerID) throws RemoteException;

}
