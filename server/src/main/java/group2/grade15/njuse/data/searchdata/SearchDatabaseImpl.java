package group2.grade15.njuse.data.searchdata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.AreaDataService;
import group2.grade15.njuse.po.*;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchDatabaseImpl implements AreaDataService {
	private DatabaseMySql mySql=null;
	private Connection searchDatabese=null;

	public SearchDatabaseImpl(DatabaseInfo info) throws RemoteException{
		mySql=new DatabaseMySql(info);
		searchDatabese=mySql.init();
	}

	@Override
	public ArrayList<ProvincePO> getProvince() {
	    if(searchDatabese==null){
	        searchDatabese=mySql.init();
        }

        try{
            Statement getProvince=searchDatabese.createStatement();
            ResultSet resultSet=getProvince.executeQuery("select * from province");

            ArrayList<ProvincePO> list=new ArrayList<ProvincePO>();
            while(resultSet.next()){
                String provinceName=resultSet.getString(1);
                String provinceID=resultSet.getString(2);

                ProvincePO provincePO=new ProvincePO(provinceName,provinceID);
                list.add(provincePO);
            }

            getProvince.close();
            searchDatabese.close();
            searchDatabese=null;

            return list;
        }catch (SQLException e){
	        e.printStackTrace();
	        return null;
        }
	}

	@Override
	public ArrayList<CityPO> getCity(String provinceNum) {
		return null;
	}

	@Override
	public ArrayList<DistrictPO> getDistrict(String cityNum) {
		return null;
	}

	@Override
	public ArrayList<CbdPO> getCbd(String districtNum) {
		return null;
	}
}
