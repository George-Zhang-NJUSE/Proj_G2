package group2.grade15.njuse.data.commentdata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.data.encrypt.Encrypt;
import group2.grade15.njuse.po.CommentPO;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by dell on 2016/11/27.
 */
public class HotelCommentPart{
    private DatabaseMySql mySql = null;
    private Connection hotelCommentDatabase = null;
    private Encrypt encrypt=null;

    public HotelCommentPart(DatabaseInfo info) {
        mySql = new DatabaseMySql(info);
        hotelCommentDatabase = mySql.init();
        encrypt=new Encrypt();
    }

    public ArrayList<CommentPO> getHotelComments(int hotelID) throws RemoteException {
        if (hotelCommentDatabase == null) {
            hotelCommentDatabase = mySql.init();
        }

        try {
            Statement hotelComment = hotelCommentDatabase.createStatement();
            ResultSet resultSet = hotelComment.executeQuery("select * from comment where hotelid = " + encrypt.encrypt(hotelID));
            ArrayList<CommentPO> list = new ArrayList<CommentPO>();

            while (resultSet.next()) {
                int customerID = encrypt.decrypt(resultSet.getInt(1));
                String comment = resultSet.getString(3);
                int commentNum = resultSet.getInt(4);
                int orderNum = resultSet.getInt(5);
                Date time = resultSet.getDate(6);
                double score = resultSet.getDouble(7);

                CommentPO commentPO = new CommentPO(hotelID, customerID, time, comment, commentNum, score, orderNum);
                list.add(commentPO);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
