package group2.grade15.njuse.data.commentdata;

import group2.grade15.njuse.po.CommentPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by dell on 2016/11/27.
 */
public interface HotelComment extends Remote{

    public ArrayList<CommentPO> getHotelComments(int hotelID) throws RemoteException;
}