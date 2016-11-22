package group2.grade15.njuse.data.hotelmanagerdata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.HotelManagerDataService;
import group2.grade15.njuse.po.HotelManagerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
public class HotelManagerDatabaseImpl implements HotelManagerDataService{
    private DatabaseMySql mySql=null;
    private Connection hotelManagerConnection=null;

    public HotelManagerDatabaseImpl(DatabaseInfo info) throws RemoteException{
        mySql=new DatabaseMySql(info);
        hotelManagerConnection=mySql.init();
    }

    public HotelManagerPO get(int hotelManagerId) throws RemoteException {
        if(hotelManagerConnection==null){
            hotelManagerConnection=mySql.init();
        }

        int id=0;
        String password=null,name=null,contact=null;
        int hotelID=0;
        try{
            Statement getInfo=hotelManagerConnection.createStatement();
            ResultSet r=getInfo.executeQuery("select * from hotelmanager where hotelmanagerid = "+hotelManagerId);
            if(r.next()){
                id=hotelManagerId;
                password=r.getString(2);
                name=r.getString(3);
                contact=r.getString(4);
                hotelID=r.getInt(5);
            }
            else{
                throw new SQLException();
            }

            getInfo.close();
            hotelManagerConnection.close();
            hotelManagerConnection=null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        HotelManagerPO hotelManagerPO=new HotelManagerPO(id,password,name,contact,hotelID);
        return hotelManagerPO;
    }

    public ResultMessage add(HotelManagerPO po) throws RemoteException {
        return null;
    }

    public ResultMessage modify(HotelManagerPO po) throws RemoteException {
        return null;
    }

    public ResultMessage remove(int hotelManagerId) throws RemoteException {
        return null;
    }
}
