package group2.grade15.njuse.data.searchdata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.areadataservice.AreaDataService;
import group2.grade15.njuse.po.*;
import group2.grade15.njuse.utility.RoomType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

public class AreaDatabaseImpl implements AreaDataService {
	private DatabaseMySql mySql=null;
	private Connection searchDatabase=null;

	public AreaDatabaseImpl(DatabaseInfo info) throws RemoteException{
		mySql=new DatabaseMySql(info);
		searchDatabase=mySql.init();
	}

	@Override
	public ArrayList<ProvincePO> getProvince() {
	    if(searchDatabase==null){
	        searchDatabase=mySql.init();
        }

        try{
            Statement getProvince=searchDatabase.createStatement();
            ResultSet resultSet=getProvince.executeQuery("select * from province");

            ArrayList<ProvincePO> list=new ArrayList<ProvincePO>();
            while(resultSet.next()){
                String provinceName=resultSet.getString(1);
                String provinceID=resultSet.getString(2);

                ProvincePO provincePO=new ProvincePO(provinceName,provinceID);
                list.add(provincePO);
            }

            getProvince.close();
            searchDatabase.close();
            searchDatabase=null;

            return list;
        }catch (SQLException e){
	        e.printStackTrace();
	        return null;
        }
	}

	@Override
	public ArrayList<CityPO> getCity(String provinceNum) {
		if(searchDatabase==null){
		    searchDatabase=mySql.init();
        }

        try{
            PreparedStatement city=searchDatabase.prepareStatement("select cityname,citynum from city where provincenum = ?");
            city.setString(1,provinceNum);
            ResultSet resultSet=city.executeQuery();

            ArrayList<CityPO> list=new ArrayList<CityPO>();
            while(resultSet.next()){
                String name=resultSet.getString(1);
                String num=resultSet.getString(2);
                CityPO cityPO=new CityPO(name,num);
                list.add(cityPO);
            }

            city.close();
            searchDatabase.close();
            searchDatabase=null;

            return list;
        }catch (SQLException e){
		    e.printStackTrace();
		    return null;
        }
	}

	@Override
	public ArrayList<DistrictPO> getDistrict(String cityNum) {
        if(searchDatabase==null){
            searchDatabase=mySql.init();
        }

        try{
            PreparedStatement district=searchDatabase.prepareStatement("select districtname,districtnum from district where citynum = ?");
            district.setString(1,cityNum);
            ResultSet resultSet=district.executeQuery();

            ArrayList<DistrictPO> list=new ArrayList<DistrictPO>();
            while(resultSet.next()){
                String name=resultSet.getString(1);
                String num=resultSet.getString(2);
                DistrictPO districtPO=new DistrictPO(name,num);
                list.add(districtPO);
            }

            district.close();
            searchDatabase.close();
            searchDatabase=null;

            return list;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
	}

	@Override
	public ArrayList<CbdPO> getCbd(String districtNum) {
        if(searchDatabase==null){
            searchDatabase=mySql.init();
        }

        try{
            PreparedStatement cbd=searchDatabase.prepareStatement("select cbdname,cbdnum from cbd where districtnum = ?");
            cbd.setString(1,districtNum);
            ResultSet resultSet=cbd.executeQuery();

            ArrayList<CbdPO> list=new ArrayList<CbdPO>();
            while(resultSet.next()){
                String name=resultSet.getString(1);
                String num=resultSet.getString(2);
                CbdPO cbdPO=new CbdPO(name,num);
                list.add(cbdPO);
            }

            cbd.close();
            searchDatabase.close();
            searchDatabase=null;

            return list;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
	}

    /**
     * @param address
     * @return ArrayList<HotelPO>
     */
    @Override
    public ArrayList<HotelPO> getHotelByAddress(String address) {
        if(searchDatabase==null){
            searchDatabase=mySql.init();
        }

        try{
            PreparedStatement hotel=searchDatabase.prepareStatement("select * from hotel where address = ?");
            hotel.setString(1,address);
            return getHotelList(hotel);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<HotelPO> getHotelByName(String name) {
        if(searchDatabase==null){
            searchDatabase=mySql.init();
        }

        try{
            PreparedStatement hotel=searchDatabase.prepareStatement("select * from hotel where name = ?");
            hotel.setString(1,name);
            return getHotelList(hotel);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<HotelPO> getHotelList(PreparedStatement hotel){
        try{
            ResultSet resultSet=hotel.executeQuery();

            ArrayList<HotelPO> list=new ArrayList<HotelPO>();
            while(resultSet.next()){
                int id=resultSet.getInt(1);
                String name=resultSet.getString(2);
                String tel=resultSet.getString(3);
                int rank=resultSet.getInt(4);
                String address=resultSet.getString(5);
                String introduction=resultSet.getString(6);
                String facility=resultSet.getString(7);

                File picPath=new File(resultSet.getString(5));
                File[] allPic=picPath.listFiles();
                byte[][] picList;
                if(allPic.length>0) {
                    picList = new byte[allPic.length][];
                }
                else{
                    picList=null;
                }
                for(int i=0;i<allPic.length;i++){
                    picList[i]=readPic(allPic[i].getAbsolutePath());
                }

                Statement getRoom=searchDatabase.createStatement();
                ResultSet room=getRoom.executeQuery("select * from room where hotelid = "+id);
                ArrayList<RoomPO> roomList=new ArrayList<RoomPO>();
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

                Statement getScore=searchDatabase.createStatement();
                ResultSet score=getScore.executeQuery("select avg(score) from comment where hotelid = "+id);
                double hotelScore=0.00;
                if(score.next()){
                    hotelScore=score.getDouble(1);
                }
                else{
                    throw new SQLException();
                }

                HotelPO hotelPO=new HotelPO(id,name,address,tel,introduction,facility,roomList,rank,hotelScore,picList);
                list.add(hotelPO);
            }

            return list;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    private byte[] readPic(String path){
        try{
            File file=new File(path);
            BufferedImage temp=ImageIO.read(file);
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
}
