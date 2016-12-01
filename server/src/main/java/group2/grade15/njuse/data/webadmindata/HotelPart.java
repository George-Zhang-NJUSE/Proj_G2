package group2.grade15.njuse.data.webadmindata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
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
     * 只注册酒店基本信息，不进行图片，房间,vip list录入，要求逻辑层在执行完本方法后立即执行酒店工作人员的注册
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
            File file= new File(path+ id );
            file.mkdir();
            PreparedStatement add=hotelPartDatabase.prepareStatement("insert into hotel values(?,?,?,?,?,?,?,?)");
            add.setInt(1,id);
            add.setString(2,hotelPO.getName());
            add.setString(3,hotelPO.getContact());
            add.setInt(4,hotelPO.getRank());
            add.setString(5,path+id);
            add.setString(6,hotelPO.getIntroduction());
            add.setString(7,hotelPO.getFacility());
            add.setString(8,hotelPO.getAddress());
            add.executeUpdate();

            add.close();
            hotelPartDatabase.close();
            hotelPartDatabase=null;

            return new HotelPO(id,hotelPO.getName(),hotelPO.getAddress(),hotelPO.getContact(),hotelPO.getIntroduction(),
                    hotelPO.getFacility(),null,hotelPO.getRank(),0.00,null);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return ArrayList<HotelPO>
     * @throws RemoteException
     * 仅返回酒店基本信息，不返回图片，简介，设施,房间，vip list的信息
     */
    @Override
    public ArrayList<HotelPO> getHotelInfo() throws RemoteException {
        if(hotelPartDatabase==null){
            hotelPartDatabase=mySql.init();
        }

        try{
            Statement getInfo=hotelPartDatabase.createStatement();
            ResultSet resultSet=getInfo.executeQuery("select * from hotel");

            ArrayList<HotelPO> list=new ArrayList<HotelPO>();
            while (resultSet.next()){
                int id=resultSet.getInt(1);
                String name=resultSet.getString(2);
                String tel=resultSet.getString(3);
                int rank=resultSet.getInt(4);
                Statement getScore=hotelPartDatabase.createStatement();
                ResultSet score=getScore.executeQuery("select avg(score) from comment where hotelid = "+id);
                double hotelscore;
                if(score.next()){
                    hotelscore=score.getDouble(1);
                }
                else{
                    hotelscore=0.00;
                }
                getScore.close();
                String address=resultSet.getString(8);//未经转换的

                HotelPO hotelPO=new HotelPO(id,name,address,tel,null,null,null,rank,
                        hotelscore,null);
                list.add(hotelPO);
            }
            return list;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param hotelPO
     * @return ResultMessage
     * @throws RemoteException
     * 仅可以更改基本信息（同上）
     */
    @Override
    public ResultMessage modifyHotelInfo(HotelPO hotelPO) throws RemoteException {
        if(hotelPartDatabase==null){
            hotelPartDatabase=mySql.init();
        }

        try{
            PreparedStatement modify=hotelPartDatabase.prepareStatement("update hotel set " +
                    "name = ?,telnum = ?,rank = ?,address = ? where hotelid = ?");
            modify.setString(1,hotelPO.getName());
            modify.setString(2,hotelPO.getContact());
            modify.setInt(3,hotelPO.getRank());
            modify.setString(4,hotelPO.getAddress());
            modify.setInt(5,hotelPO.getId());
            modify.executeUpdate();

            modify.close();
            hotelPartDatabase.close();
            hotelPartDatabase=null;

            return ResultMessage.SUCCESS;
        }catch (SQLException e){
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    /**
     * @param hotelID
     * @return ResultMessage
     * @throws RemoteException
     * 同时删除酒店工作人员
     */
    @Override
    public ResultMessage deleteHotelInfo(int hotelID) throws RemoteException {
        if(hotelPartDatabase==null){
            hotelPartDatabase=mySql.init();
        }

        try{
            Statement delete=hotelPartDatabase.createStatement();
            ResultSet resultSet=delete.executeQuery("select picture from hotel where hotelid = "+hotelID);
            String path=null;
            if(resultSet.next()){
                path=resultSet.getString(1);
            }
            else{
                throw new SQLException();
            }

            delete.executeUpdate("delete from hotel where hotelid = "+hotelID);
            delete.executeUpdate("delete from hotelmanager where hotelid = "+hotelID);

            File file=new File(path);
            String[] list=file.list();
            for(String child:list){
                File childFile=new File(file.getAbsolutePath()+"/"+child);
                childFile.delete();
            }
            file.delete();

            delete.close();
            hotelPartDatabase.close();
            hotelPartDatabase=null;

            return ResultMessage.SUCCESS;
        }catch (SQLException e){
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }
}
