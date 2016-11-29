package group2.grade15.njuse.data.webadmindata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.po.HotelManagerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by dell on 2016/11/25.
 */
public class HotelManagerPart implements HotelManagerPartService{
    private DatabaseMySql mySql=null;
    private Connection hotelManagerPartDatabase=null;

    public HotelManagerPart(DatabaseInfo info){
        mySql=new DatabaseMySql(info);
        hotelManagerPartDatabase=mySql.init();
    }

    @Override
    public ArrayList<HotelManagerPO> getHotelManagerInfo() throws RemoteException {
        if(hotelManagerPartDatabase==null){
            hotelManagerPartDatabase=mySql.init();
        }

        try{
            Statement getInfo=hotelManagerPartDatabase.createStatement();
            ResultSet resultSet=getInfo.executeQuery("select * from hotelmanager");

            ArrayList<HotelManagerPO> list=new ArrayList<HotelManagerPO>();
            while(resultSet.next()){
                int id=resultSet.getInt(1);
                String password=resultSet.getString(2);
                String name=resultSet.getString(3);
                String tel=resultSet.getString(4);
                int hotelID=resultSet.getInt(5);

                HotelManagerPO hotelManagerPO=new HotelManagerPO(id,password,name,tel,hotelID);
                list.add(hotelManagerPO);
            }
            ;
            return list;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param hotelManagerPO
     * @return ResultMessage
     * @throws RemoteException
     * 除id外其它均可以修改
     */
    @Override
    public ResultMessage modifyHotelManagerInfo(HotelManagerPO hotelManagerPO) throws RemoteException {
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
