package group2.grade15.njuse.data.webadmindata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.data.encrypt.Encrypt;
import group2.grade15.njuse.po.WebMarketerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by dell on 2016/11/25.
 */
public class WebMarketerPart{
    private DatabaseMySql mySql = null;
    private Connection webMarketerPartDatabase = null;
    private Encrypt encrypt;

    public WebMarketerPart(DatabaseInfo info) {
        mySql = new DatabaseMySql(info);
        webMarketerPartDatabase = mySql.init();
        encrypt=new Encrypt();
    }

    public ResultMessage addWebMarketerInfo(WebMarketerPO webMarketerPO) throws RemoteException {
        if (webMarketerPartDatabase == null) {
            webMarketerPartDatabase = mySql.init();
        }

        try {
            PreparedStatement add = webMarketerPartDatabase.prepareStatement("insert into webmarketer values(?,?)");
            add.setString(1, encrypt.encrypt(webMarketerPO.getPassword()));
            add.setString(2, encrypt.encrypt(webMarketerPO.getStaffID()));
            add.executeUpdate();

            add.close();
            webMarketerPartDatabase.close();
            webMarketerPartDatabase = null;

            return ResultMessage.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    public ArrayList<WebMarketerPO> getWebMarketerInfo() throws RemoteException {
        if (webMarketerPartDatabase == null) {
            webMarketerPartDatabase = mySql.init();
        }

        try {
            Statement getInfo = webMarketerPartDatabase.createStatement();
            ResultSet resultSet = getInfo.executeQuery("select * from webmarketer");

            ArrayList<WebMarketerPO> list = new ArrayList<WebMarketerPO>();
            while (resultSet.next()) {
                String password = encrypt.decrypt(resultSet.getString(1));
                String id = encrypt.decrypt(resultSet.getString(2));
                WebMarketerPO webMarketerPO = new WebMarketerPO(password, id);
                list.add(webMarketerPO);
            }

            getInfo.close();
            webMarketerPartDatabase.close();
            webMarketerPartDatabase = null;

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param webMarketerPO
     * @return
     * @throws RemoteException 只能修改密码
     */
    public ResultMessage modifyWebMarketerInfo(WebMarketerPO webMarketerPO) throws RemoteException {
        if (webMarketerPartDatabase == null) {
            webMarketerPartDatabase = mySql.init();
        }

        try {
            PreparedStatement modify = webMarketerPartDatabase.prepareStatement("update webmarketer set password = ?" +
                    "where employeeid = ?");
            modify.setString(1, encrypt.encrypt(webMarketerPO.getPassword()));
            modify.setString(2, encrypt.encrypt(webMarketerPO.getStaffID()));
            modify.executeUpdate();

            modify.close();
            webMarketerPartDatabase.close();
            webMarketerPartDatabase = null;

            return ResultMessage.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage deleteWebMarketer(String webMarketerID) throws RemoteException {
        if (webMarketerPartDatabase == null) {
            webMarketerPartDatabase = mySql.init();
        }

        try {
            PreparedStatement delete = webMarketerPartDatabase.prepareStatement("delete from webmarketer where employeeid = ?");
            delete.setString(1, encrypt.encrypt(webMarketerID));
            delete.executeUpdate();

            delete.close();
            webMarketerPartDatabase.close();
            webMarketerPartDatabase = null;

            return ResultMessage.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }
}
