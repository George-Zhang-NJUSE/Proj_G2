package group2.grade15.njuse.data.hoteldata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.hoteldataservice.HotelDataService;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.po.RoomPO;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
public class HotelDatabaseImpl implements HotelDataService {
    private DatabaseMySql mySql=null;
    private Connection hotelDatabase=null;
    private String[] roomName={"singlebed","doublebed","suiteroom"};

    public HotelDatabaseImpl(DatabaseInfo info) throws RemoteException{
        mySql=new DatabaseMySql(info);
        hotelDatabase=mySql.init();
    }

    public HotelPO getHotel(int hotelId) throws RemoteException {
        if(hotelDatabase==null){
            hotelDatabase=mySql.init();
        }

        try{
            Statement getInfo=hotelDatabase.createStatement();
            ResultSet resultSet=getInfo.executeQuery("select * from hotel where hotelid = "+hotelId);

            int id=hotelId;
            String name,tel;
            int rank;
            File picPath;
            double hotelScore;
            ArrayList<RoomPO> roomList=new ArrayList<RoomPO>();
            String address,introduction,facility,concreteAddress;
            byte[][] picList;
            if(resultSet.next()){
                id=resultSet.getInt(1);
                name=resultSet.getString(2);
                tel=resultSet.getString(3);
                rank=resultSet.getInt(4);
                address=resultSet.getString(5);
                introduction=resultSet.getString(6);
                facility=resultSet.getString(7);
                concreteAddress=resultSet.getString(9);
                picPath=new File(resultSet.getString(5));

                File[] allPic=picPath.listFiles();
                if(allPic.length>0) {
                    picList = new byte[allPic.length][];
                }
                else{
                    picList=null;
                }
                for(int i=0;i<allPic.length;i++){
                    picList[i]=readPic(allPic[i].getAbsolutePath());
                }

                Statement getRoom=hotelDatabase.createStatement();
                ResultSet room=getRoom.executeQuery("select * from room where hotelid = "+id);
                roomList=new ArrayList<RoomPO>();
                if(room.next()){
                    if(room.getBoolean(2)){
                        RoomPO roomPO1=new RoomPO(RoomType.bigSingleBed,room.getDouble(5),
                                room.getInt(4),room.getInt(3));
                        roomList.add(roomPO1);
                    }
                    if(room.getBoolean(6)){
                        RoomPO roomPO2=new RoomPO(RoomType.stadardDoubleBed,room.getDouble(9),
                                room.getInt(8),room.getInt(7));
                        roomList.add(roomPO2);
                    }
                    if(room.getBoolean(10)){
                        RoomPO roomPO3=new RoomPO(RoomType.suiteRoom,room.getDouble(13),
                                room.getInt(12),room.getInt(11));
                        roomList.add(roomPO3);
                    }
                }
                else{
                    throw new SQLException();
                }
                getRoom.close();

                Statement getScore=hotelDatabase.createStatement();
                ResultSet score=getScore.executeQuery("select avg(score) from comment where hotelid = "+id);
                hotelScore=0.00;
                if(score.next()){
                    hotelScore=score.getDouble(1);
                }
                else{
                    throw new SQLException();
                }
            }
            else{
                throw new SQLException();
            }

            HotelPO hotelPO=new HotelPO(id,name,address,concreteAddress,tel,introduction,facility,roomList,rank,hotelScore,picList);
            return hotelPO;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    private byte[] readPic(String path){
        try{
            File file=new File(path);
            BufferedImage temp= ImageIO.read(file);
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            ImageIO.write(temp,"jpg",bos);
            byte[] pic=bos.toByteArray();
            bos.close();
            return pic;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @param po
     * @return ResultMessage
     * @throws RemoteException
     * 只能修改地址、所属商圈、简介、设施服务、星级、联系方式
     */
    public ResultMessage modify(HotelPO po) throws RemoteException {
        if(hotelDatabase==null){
            hotelDatabase=mySql.init();
        }

        try{
            PreparedStatement modify=hotelDatabase.prepareStatement("update hotel set concreteaddress = ?," +
                    "address = ?,introduction = ?,facility = ?,rank = ? where hotelid = ?");
            modify.setString(1,po.getConcreteAddress());
            modify.setString(2,po.getAddress());
            modify.setString(3,po.getIntroduction());
            modify.setString(4,po.getFacility());
            modify.setInt(5,po.getRank());
            modify.setInt(6,po.getId());

            modify.executeUpdate();

            modify.close();
            hotelDatabase.close();
            hotelDatabase=null;

            return ResultMessage.SUCCESS;
        }catch (SQLException e){
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    public ResultMessage modifyRoom(int hotelId, RoomPO po) throws RemoteException {
        if(hotelDatabase==null){
            hotelDatabase=mySql.init();
        }

        try{//表驱动
            String name=roomName[po.getType().ordinal()];
            String price=name+"price";
            String book=name+"booked";
            String total=name+"total";

            PreparedStatement room=hotelDatabase.prepareStatement("update room set "+price+" = ?," +
                    book+" = ?,"+total+" = ? where hotelid = ?");
            room.setDouble(1,po.getPrice());
            room.setInt(2,po.getTotalRoomNum()-po.getSpareRoomNum());
            room.setInt(3,po.getTotalRoomNum());
            room.setInt(4,hotelId);

            room.executeUpdate();

            room.close();
            hotelDatabase.close();
            hotelDatabase=null;

            return ResultMessage.SUCCESS;
        }catch (SQLException e){
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ResultMessage addRoomType(int hotelID, RoomPO po) throws RemoteException {
        if(hotelDatabase==null){
            hotelDatabase=mySql.init();
        }

        try{
            String name=roomName[po.getType().ordinal()];
            String price=name+"price";
            String book=name+"booked";
            String total=name+"total";

            PreparedStatement add=hotelDatabase.prepareStatement("update room set "+name+" = ?,"+price+" = ?,"+
                    book+" = ?,"+total+" = ? where hotelid = ?");
            add.setBoolean(1,true);
            add.setDouble(2,po.getPrice());
            add.setInt(3,po.getTotalRoomNum()-po.getSpareRoomNum());
            add.setInt(4,po.getTotalRoomNum());
            add.setInt(5,hotelID);

            add.executeUpdate();

            add.close();
            hotelDatabase.close();
            hotelDatabase=null;

            return ResultMessage.SUCCESS;
        }catch (SQLException e){
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ResultMessage deleteRoomType(int hotelID, RoomType type) throws RemoteException {
        if(hotelDatabase==null){
            hotelDatabase=mySql.init();
        }

        try{
            String name=roomName[type.ordinal()];
            PreparedStatement delete=hotelDatabase.prepareStatement("update room set "+name+" = ? where hotelid = ?");
            delete.setBoolean(1,false);
            delete.setInt(2,hotelID);

            delete.executeUpdate();

            delete.close();
            hotelDatabase.close();
            hotelDatabase=null;

            return ResultMessage.SUCCESS;
        }catch (SQLException e){
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ResultMessage uploadPic(byte[][] picList, int hotelID) throws RemoteException {
        if(hotelDatabase==null){
            hotelDatabase=mySql.init();
        }

        try{
            Statement path=hotelDatabase.createStatement();
            ResultSet resultSet=path.executeQuery("select picture from hotel where hotelid = "+hotelID);
            String picPath;
            if(resultSet.next()){
                picPath=resultSet.getString(1);
            }
            else{
                throw new SQLException();
            }
            path.close();

            File directory=new File(picPath);
            File[] pics=directory.listFiles();
            int picNum=Integer.parseInt((pics[pics.length-1].getName()).split("\\.")[0])+1;
            for(int i=0;i<picList.length;i++){
                ByteArrayInputStream bis=new ByteArrayInputStream(picList[i]);
                BufferedImage bi=ImageIO.read(bis);
                ImageIO.write(bi,"jpg",new File(picPath+"/"+picNum+".jpg"));
                picNum++;
                bis.close();
            }

            return ResultMessage.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ResultMessage deletePic(int picNum, int hotelID) throws RemoteException {
        if(hotelDatabase==null){
            hotelDatabase=mySql.init();
        }

        try{
            Statement path=hotelDatabase.createStatement();
            ResultSet resultSet=path.executeQuery("select picture from hotel where hotelid = "+hotelID);
            String picPath;
            if(resultSet.next()){
                picPath=resultSet.getString(1);
            }
            else{
                throw new SQLException();
            }
            path.close();

            File directory=new File(picPath);
            File[] pics=directory.listFiles();
            pics[picNum].delete();

            return ResultMessage.SUCCESS;
        }catch (SQLException e){
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }
}
