package group2.grade15.njuse.data.creditdata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.creditdataservice.CreditDataService;
import group2.grade15.njuse.po.CreditPO;
import group2.grade15.njuse.utility.ChangeReason;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

public class CreditDatabaseImpl implements CreditDataService {
    private DatabaseMySql mySql = null;
    private Connection creditDatabase = null;

    public CreditDatabaseImpl(DatabaseInfo info) throws RemoteException {
        mySql = new DatabaseMySql(info);
        creditDatabase = mySql.init();
    }

    public ArrayList<CreditPO> getHistory(int customerId) throws RemoteException {
        if (creditDatabase == null) {
            creditDatabase = mySql.init();
        }

        try {
            Statement history = creditDatabase.createStatement();
            ResultSet resultSet = history.executeQuery("select * from credithistory where customerid = " + customerId);

            ArrayList<CreditPO> list = new ArrayList<CreditPO>();
            while (resultSet.next()) {
                int customer = resultSet.getInt(1);
                int credit = resultSet.getInt(2);
                int order = resultSet.getInt(3);
                double change = resultSet.getDouble(4);
                double left = resultSet.getDouble(5);
                Date time = resultSet.getDate(6);
                ChangeReason reason = ChangeReason.values()[resultSet.getInt(7)];

                CreditPO creditPO = new CreditPO(customer, order, credit, left, change, time, reason);
                list.add(creditPO);
            }

            history.close();
            creditDatabase.close();
            creditDatabase = null;

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param po
     * @return ResultMessage
     * @throws RemoteException Date应由服务器生成,并由这里更改customer的credit
     */
    public ResultMessage add(CreditPO po) throws RemoteException {
        if (creditDatabase == null) {
            creditDatabase = mySql.init();
        }

        try {
            PreparedStatement add = creditDatabase.prepareStatement("insert into credithistory values(?,?,?,?,?,?,?)");
            Statement makeID = creditDatabase.createStatement();
            ResultSet resultSet = makeID.executeQuery("select max(creditnum) from credithistory");
            int creditID = 0;
            if (resultSet.next()) {
                creditID = resultSet.getInt(1) + 1;
            } else {
                throw new SQLException();
            }
            makeID.close();

            Statement getCredit = creditDatabase.createStatement();
            ResultSet ownCredit = getCredit.executeQuery("select credit from customer where customerid = " + po.getCustomerID());
            double credit = 0.00;
            if (ownCredit.next()) {
                credit = ownCredit.getDouble(1);
            } else {
                throw new SQLException();
            }
            getCredit.close();

            java.util.Date current = new java.util.Date();
            Date time = new Date(current.getTime());

            add.setInt(1, po.getCustomerID());
            add.setInt(2, creditID);
            add.setInt(3, po.getOrderID());
            add.setDouble(4, po.getCreditChange());
            add.setDouble(5, credit + po.getCreditChange());
            add.setDate(6, time);
            add.setInt(7, po.getReason().ordinal());

            add.executeUpdate();
            add.close();

            PreparedStatement updateCredit = creditDatabase.prepareStatement("update customer set credit = ? where customerid = ?");
            updateCredit.setDouble(1, credit + po.getCreditChange());
            updateCredit.setInt(2, po.getCustomerID());

            updateCredit.executeUpdate();
            updateCredit.close();

            creditDatabase.close();
            creditDatabase = null;

            return ResultMessage.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }
}
