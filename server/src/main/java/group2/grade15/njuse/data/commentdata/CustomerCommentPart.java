package group2.grade15.njuse.data.commentdata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.po.CommentPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by dell on 2016/11/27.
 */
public class CustomerCommentPart implements CustomerComment {
    private DatabaseMySql mySql = null;
    private Connection customerCommentDatabase = null;

    public CustomerCommentPart(DatabaseInfo info) {
        mySql = new DatabaseMySql(info);
        customerCommentDatabase = mySql.init();
    }

    @Override
    public ArrayList<CommentPO> getCustomerComments(int customerID) throws RemoteException {
        if (customerCommentDatabase == null) {
            customerCommentDatabase = mySql.init();
        }

        try {
            Statement getComment = customerCommentDatabase.createStatement();
            ResultSet resultSet = getComment.executeQuery("select * from comment where customerid = " + customerID);

            ArrayList<CommentPO> list = new ArrayList<CommentPO>();
            while (resultSet.next()) {
                int customerNum = customerID;
                int hotelID = resultSet.getInt(2);
                String comment = resultSet.getString(3);
                int commentID = resultSet.getInt(4);
                int orderNum = resultSet.getInt(5);
                Date date = resultSet.getDate(6);
                double score = resultSet.getDouble(7);

                CommentPO commentPO = new CommentPO(hotelID, customerNum, date, comment, commentID, score, orderNum);
                list.add(commentPO);
            }

            getComment.close();
            customerCommentDatabase.close();
            customerCommentDatabase = null;

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param po
     * @return ResultMessage
     * @throws RemoteException Date,commentID应由服务器生成
     */
    @Override
    public ResultMessage add(CommentPO po) throws RemoteException {
        if (customerCommentDatabase == null) {
            customerCommentDatabase = mySql.init();
        }

        try {
            PreparedStatement add = customerCommentDatabase.prepareStatement("insert into comment values(?,?,?,?,?,?,?)");
            Statement makeID = customerCommentDatabase.createStatement();
            ResultSet resultSet = makeID.executeQuery("select max(commentnum) from comment");
            int commentID = 0;
            if (resultSet.next()) {
                commentID = resultSet.getInt(1) + 1;
            } else {
                throw new SQLException();
            }
            resultSet.close();

            int customer = po.getUserID();
            int hotel = po.getHotelID();
            String comment = po.getComment();
            int order = po.getOrderID();
            java.util.Date current = new java.util.Date();
            Date date = new Date(current.getTime());
            double score = po.getScore();

            add.setInt(1, customer);
            add.setInt(2, hotel);
            add.setString(3, comment);
            add.setInt(4, commentID);
            add.setInt(5, order);
            add.setDate(6, date);
            add.setDouble(7, score);

            add.executeUpdate();

            add.close();
            customerCommentDatabase.close();
            customerCommentDatabase = null;

            return ResultMessage.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    /**
     * @param po
     * @return ResultMessage
     * @throws RemoteException Date应由服务器生成,只能修改文字
     */
    @Override
    public ResultMessage modify(CommentPO po) throws RemoteException {
        if (customerCommentDatabase == null) {
            customerCommentDatabase = mySql.init();
        }

        try {
            PreparedStatement modify = customerCommentDatabase.prepareStatement("update comment set comment = ?," +
                    "time = ? " +
                    "where commentnum = ?");
            modify.setString(1, po.getComment());
            java.util.Date current = new java.util.Date();
            Date time = new Date(current.getTime());
            modify.setDate(2, time);
            modify.setInt(3, po.getCommentID());

            modify.executeUpdate();

            modify.close();
            customerCommentDatabase.close();
            customerCommentDatabase = null;

            return ResultMessage.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }
}
