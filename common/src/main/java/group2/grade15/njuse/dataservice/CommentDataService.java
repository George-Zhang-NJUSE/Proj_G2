package group2.grade15.njuse.dataservice;

import group2.grade15.njuse.po.CommentPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by George on 2016/10/16.
 */
public interface CommentDataService extends Remote {

    public ResultMessage add(CommentPO po) throws RemoteException;

    public ResultMessage modify(CommentPO po) throws RemoteException;

    public ArrayList<CommentPO> getHotelComments(int hotelID) throws RemoteException;

    public ArrayList<CommentPO> getCustomerComments(int customerID) throws RemoteException;

}
