package group2.grade15.njuse.data.webadmindata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.webadmindataservice.HotelPartService;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.io.File;
import java.rmi.RemoteException;
import java.sql.*;
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

    /**
     * @param hotelPO
     * @return HotelPO
     * @throws RemoteException
     * 只注册酒店基本信息，不进行图片，房间录入，要求逻辑层在执行完本方法后立即执行酒店工作人员的注册
     * 酒店的地址要通过search方法获得
     */
    @Override
    public HotelPO addHotel(HotelPO hotelPO) throws RemoteException {
        if(hotelPartDatabase==null){
            hotelPartDatabase=mySql.init();
        }

        try{
            Statement getID=hotelPartDatabase.createStatement();
            ResultSet resultSet=getID.executeQuery("select max(hotelid) from hotel");
            int id=0;
            if(resultSet.next()){
                id=resultSet.getInt(1)+1;
            }
            getID.close();

            String path="D:/hotelpicture/";
            File file= new File(path+ hotelPO.getId()+"/");
            file.mkdir();
            PreparedStatement add=hotelPartDatabase.prepareStatement("insert into hotel values(?,?,?,?,DEFAULT,?,?,?,?)");
            add.setInt(1,id);
            add.setString(2,hotelPO.getName());
            add.setString(3,hotelPO.getContact());
            add.setInt(4,hotelPO.getRank());
            add.setString(5,path+hotelPO.getId()+"/");
            add.setString(6,hotelPO.getIntroduction());
            add.setString(7,hotelPO.getAddress());
            add.setString(8,hotelPO.getFacility());
            add.executeUpdate();

            add.close();
            hotelPartDatabase.close();
            hotelPartDatabase=null;

            return new HotelPO(id,hotelPO.getName(),hotelPO.getAddress(),hotelPO.getContact(),hotelPO.getIntroduction(),
                    hotelPO.getFacility(),null,hotelPO.getRank(),hotelPO.getScore(),null);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
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
