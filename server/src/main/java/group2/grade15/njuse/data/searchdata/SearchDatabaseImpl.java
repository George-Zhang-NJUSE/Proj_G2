package group2.grade15.njuse.data.searchdata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.AreaDataService;
import group2.grade15.njuse.po.*;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

public class SearchDatabaseImpl implements AreaDataService {
	private DatabaseMySql mySql=null;
	private Connection searchDatabase=null;

	public SearchDatabaseImpl(DatabaseInfo info) throws RemoteException{
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

    @Override
    public ArrayList<HotelPO> getHotel(String address) {
        return null;
    }
}
