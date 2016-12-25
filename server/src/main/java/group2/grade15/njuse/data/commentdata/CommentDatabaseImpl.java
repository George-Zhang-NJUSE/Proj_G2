package group2.grade15.njuse.data.commentdata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.commentdataservice.CommentDataService;
import group2.grade15.njuse.dataservice.commentdataservice.CustomerCommentPartService;
import group2.grade15.njuse.dataservice.commentdataservice.HotelCommentPartService;
import group2.grade15.njuse.po.CommentPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;

public class CommentDatabaseImpl implements CommentDataService {
    private DatabaseInfo info = null;

    CustomerCommentPart customerCommentPart = null;
    HotelCommentPart hotelCommentPart = null;

    public CommentDatabaseImpl(DatabaseInfo info) throws RemoteException {
        this.info = info;
    }

    public ResultMessage add(CommentPO po) throws RemoteException {
        if (customerCommentPart == null) {
            customerCommentPart = new CustomerCommentPart(info);
        }
        return customerCommentPart.add(po);
    }

    public ResultMessage modify(CommentPO po) throws RemoteException {
        if (customerCommentPart == null) {
            customerCommentPart = new CustomerCommentPart(info);
        }
        return customerCommentPart.modify(po);
    }

    public ArrayList<CommentPO> getHotelComments(int hotelID) throws RemoteException {
        if (hotelCommentPart == null) {
            hotelCommentPart = new HotelCommentPart(info);
        }
        return hotelCommentPart.getHotelComments(hotelID);
    }

    public ArrayList<CommentPO> getCustomerComments(int customerID) throws RemoteException {
        if (customerCommentPart == null) {
            customerCommentPart = new CustomerCommentPart(info);
        }
        return customerCommentPart.getCustomerComments(customerID);
    }
}
