package group2.grade15.njuse.data.hotelmanagerdata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.data.encrypt.Encrypt;
import group2.grade15.njuse.dataservice.hotelmanagerdataservice.HotelManagerDataService;
import group2.grade15.njuse.po.HotelManagerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.*;

/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
public class HotelManagerDatabaseImpl implements HotelManagerDataService {
    private DatabaseMySql mySql = null;
    private Connection hotelManagerConnection = null;
    private Encrypt encrypt=null;

    public HotelManagerDatabaseImpl(DatabaseInfo info) throws RemoteException {
        mySql = new DatabaseMySql(info);
        hotelManagerConnection = mySql.init();
        encrypt=new Encrypt();
    }

    /**
     * @param hotelManagerId
     * @return HotelManagerPO
     * @throws RemoteException
     */
    public HotelManagerPO getHotelManager(int hotelManagerId) throws RemoteException {
        if (hotelManagerConnection == null) {
            hotelManagerConnection = mySql.init();
        }

        int id = 0;
        String password = null, name = null, contact = null;
        int hotelID = 0;
        try {
            Statement getInfo = hotelManagerConnection.createStatement();
            ResultSet r = getInfo.executeQuery("select * from hotelmanager where hotelmanagerid = " + hotelManagerId);
            if (r.next()) {
                id = hotelManagerId;
                password = encrypt.decrypt(r.getString(2));
                name = encrypt.decrypt(r.getString(3));
                contact = encrypt.decrypt(r.getString(4));
                hotelID = encrypt.decrypt(r.getInt(5));
            } else {
                throw new SQLException();
            }

            getInfo.close();
            hotelManagerConnection.close();
            hotelManagerConnection = null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        HotelManagerPO hotelManagerPO = new HotelManagerPO(id, password, name, contact, hotelID);
        return hotelManagerPO;
    }

    /**
     * @param po
     * @return ResultMessage
     * @throws RemoteException 除id和hotelID外其它均可修改
     */
    public ResultMessage modify(HotelManagerPO po) throws RemoteException {
        if (hotelManagerConnection == null) {
            hotelManagerConnection = mySql.init();
        }

        String password = po.getPassword(), name = po.getName(), contact = po.getContact();
        try {
            PreparedStatement modify = hotelManagerConnection.prepareStatement("update hotelmanager set password = ?," +
                    "name = ?,telnum = ?" +
                    "where hotelmanagerid = " + po.getId());
            modify.setString(1, encrypt.encrypt(password));
            modify.setString(2, encrypt.encrypt(name));
            modify.setString(3, encrypt.encrypt(contact));
            modify.executeUpdate();

            modify.close();
            hotelManagerConnection.close();
            hotelManagerConnection = null;

            return ResultMessage.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }
}
