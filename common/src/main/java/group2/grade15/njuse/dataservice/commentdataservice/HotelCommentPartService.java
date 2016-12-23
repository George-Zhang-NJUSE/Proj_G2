package group2.grade15.njuse.dataservice.commentdataservice;

import group2.grade15.njuse.po.CommentPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by dell on 2016/12/23.
 */
public interface HotelCommentPartService extends Remote{
    public ArrayList<CommentPO> getHotelComments(int hotelID) throws RemoteException;
}
