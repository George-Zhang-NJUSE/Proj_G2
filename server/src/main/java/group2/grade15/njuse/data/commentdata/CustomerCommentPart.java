package group2.grade15.njuse.data.commentdata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.commentdataservice.CustomerComment;
import group2.grade15.njuse.po.CommentPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by dell on 2016/11/27.
 */
public class CustomerCommentPart implements CustomerComment{
    private DatabaseMySql mySql=null;
    private Connection customerCommentDatabase=null;

    public CustomerCommentPart(DatabaseInfo info){
        mySql=new DatabaseMySql(info);
        customerCommentDatabase=mySql.init();
    }

    @Override
    public ArrayList<CommentPO> getCustomerComments(int customerID) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage add(CommentPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modify(CommentPO po) throws RemoteException {
        return null;
    }
}
