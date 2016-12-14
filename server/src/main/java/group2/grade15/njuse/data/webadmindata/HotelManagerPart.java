package group2.grade15.njuse.data.webadmindata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.data.encrypt.Encrypt;
import group2.grade15.njuse.po.HotelManagerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by dell on 2016/11/25.
 */
public class HotelManagerPart implements HotelManagerPartService {
    private DatabaseMySql mySql = null;
    private Connection hotelManagerPartDatabase = null;
    private Encrypt encrypt=null;

    public HotelManagerPart(DatabaseInfo info) {
        mySql = new DatabaseMySql(info);
        hotelManagerPartDatabase = mySql.init();
        encrypt=new Encrypt();
    }

    @Override
    public ArrayList<HotelManagerPO> getHotelManagerInfo() throws RemoteException {
        if (hotelManagerPartDatabase == null) {
            hotelManagerPartDatabase = mySql.init();
        }

        try {
            Statement getInfo = hotelManagerPartDatabase.createStatement();
            ResultSet resultSet = getInfo.executeQuery("select * from hotelmanager");

            ArrayList<HotelManagerPO> list = new ArrayList<HotelManagerPO>();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String password = encrypt.decrypt(resultSet.getString(2));
                String name = encrypt.decrypt(resultSet.getString(3));
                String tel = encrypt.decrypt(resultSet.getString(4));
                int hotelID = encrypt.decrypt(resultSet.getInt(5));

                HotelManagerPO hotelManagerPO = new HotelManagerPO(id, password, name, tel, hotelID);
                list.add(hotelManagerPO);
            }
            ;
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param hotelManagerPO
     * @return ResultMessage
     * @throws RemoteException 除id外其它均可以修改
     */
    @Override
    public ResultMessage modifyHotelManagerInfo(HotelManagerPO hotelManagerPO) throws RemoteException {
        if (hotelManagerPartDatabase == null) {
            hotelManagerPartDatabase = mySql.init();
        }

        try {
            PreparedStatement modify = hotelManagerPartDatabase.prepareStatement("update hotelmanager set " +
                    "password = ?,name = ?,telnum = ?,hotelid = ? where hotelmanagerid = " + hotelManagerPO.getId());
            modify.setString(1, encrypt.encrypt(hotelManagerPO.getPassword()));
            modify.setString(2, encrypt.encrypt(hotelManagerPO.getName()));
            modify.setString(3, encrypt.encrypt(hotelManagerPO.getContact()));
            modify.setInt(4, encrypt.encrypt(hotelManagerPO.getHotelID()));
            modify.executeUpdate();

            modify.close();
            hotelManagerPartDatabase.close();
            hotelManagerPartDatabase = null;

            return ResultMessage.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    @Override
    public HotelManagerPO addHotelManagerInfo(HotelManagerPO hotelManagerPO) throws RemoteException {
        if (hotelManagerPartDatabase == null) {
            hotelManagerPartDatabase = mySql.init();
        }

        try {
            PreparedStatement add = hotelManagerPartDatabase.prepareStatement("insert into hotelmanager values(?,?,?,?,?)");
            Statement makeID = hotelManagerPartDatabase.createStatement();
            ResultSet resultSet = makeID.executeQuery("select max(hotelmanagerid) from hotelmanager");
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1) + 1;
            } else {
                throw new SQLException();
            }
            makeID.close();

            add.setInt(1, id);
            add.setString(2, encrypt.encrypt(hotelManagerPO.getPassword()));
            add.setString(3, encrypt.encrypt(hotelManagerPO.getName()));
            add.setString(4, encrypt.encrypt(hotelManagerPO.getContact()));
            add.setInt(5, encrypt.encrypt(hotelManagerPO.getHotelID()));

            add.executeUpdate();

            add.close();
            hotelManagerPartDatabase.close();
            hotelManagerPartDatabase = null;

            HotelManagerPO po = new HotelManagerPO(id, hotelManagerPO.getPassword(), hotelManagerPO.getName(),
                    hotelManagerPO.getContact(), hotelManagerPO.getHotelID());
            return po;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
