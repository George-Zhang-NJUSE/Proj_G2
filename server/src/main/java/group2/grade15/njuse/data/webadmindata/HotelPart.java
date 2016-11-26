package group2.grade15.njuse.data.webadmindata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.webadmindataservice.HotelPartService;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by dell on 2016/11/25.
 */
public class HotelPart implements HotelPartService{
    private DatabaseMySql mySql=null;
    private Connection hotelPartDatabase=null;

    public HotelPart(DatabaseInfo info){
        mySql=new DatabaseMySql(info);
        hotelPartDatabase=mySql.init();
    }

    @Override
    public HotelPO addHotel(HotelPO hotelPO) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<HotelPO> getHotelInfo() throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modifyHotelInfo(HotelPO hotelPO) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage deleteHotelInfo(int hotelID) throws RemoteException {
        return null;
    }
}
