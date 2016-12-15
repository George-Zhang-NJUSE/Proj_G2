package group2.grade15.njuse.dataservice.commentdataservice;

import group2.grade15.njuse.po.CommentPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by dell on 2016/11/27.
 */
public interface CommentDataService extends Remote{
    public ArrayList<CommentPO> getCustomerComments(int customerID) throws RemoteException;

    public ResultMessage add(CommentPO po) throws RemoteException;

    public ResultMessage modify(CommentPO po) throws RemoteException;

    public ArrayList<CommentPO> getHotelComments(int hotelID) throws RemoteException;
}
