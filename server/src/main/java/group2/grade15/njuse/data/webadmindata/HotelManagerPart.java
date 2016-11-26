package group2.grade15.njuse.data.webadmindata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.data.hotelmanagerdata.HotelManagerDatabaseImpl;
import group2.grade15.njuse.dataservice.webadmindataservice.HotelManagerPartService;
import group2.grade15.njuse.po.HotelManagerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by dell on 2016/11/25.
 */
public class HotelManagerPart implements HotelManagerPartService{
    private DatabaseInfo info=null;
    private DatabaseMySql mySql=null;
    private Connection hotelManagerPartDatabase=null;

    public HotelManagerPart(DatabaseInfo info){
        this.info=info;
    }

    @Override
    public HotelManagerPO getHotelManagerInfo(int hotelManagerID) throws RemoteException {
        HotelManagerDatabaseImpl hotelManagerDatabase=new HotelManagerDatabaseImpl(info);
        return hotelManagerDatabase.get(hotelManagerID);
    }

    /**
     * @param hotelManagerPO
     * @return ResultMessage
     * @throws RemoteException
     * 除id外其它均可以修改
     */
    @Override
    public ResultMessage modifyHotelManagerInfo(HotelManagerPO hotelManagerPO) throws RemoteException {
        if(mySql==null){
            mySql=new DatabaseMySql(info);
        }
        if(hotelManagerPartDatabase==null){
            hotelManagerPartDatabase=mySql.init();
        }

        try{
            PreparedStatement modify=hotelManagerPartDatabase.prepareStatement("update hotelmanager set " +
                    "password = ?,name = ?,telnum = ?,hotelid = ? where hotelmanagerid = "+hotelManagerPO.getId());
            modify.setString(1,hotelManagerPO.getPassword());
            modify.setString(2,hotelManagerPO.getName());
            modify.setString(3,hotelManagerPO.getContact());
            modify.setInt(4,hotelManagerPO.getHotelID());
            modify.executeUpdate();

            modify.close();
            hotelManagerPartDatabase.close();
            hotelManagerPartDatabase=null;

            return ResultMessage.SUCCESS;
        }catch (SQLException e){
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }
}
