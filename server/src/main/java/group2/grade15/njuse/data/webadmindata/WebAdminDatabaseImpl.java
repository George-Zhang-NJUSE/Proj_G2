package group2.grade15.njuse.data.webadmindata;

import java.rmi.RemoteException;
import java.sql.*;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.webadmindataservice.*;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.po.HotelManagerPO;
import group2.grade15.njuse.po.WebAdminPO;
import group2.grade15.njuse.utility.ResultMessage;

public class WebAdminDatabaseImpl implements WebAdminDataService,CustomerPartService,HotelManagerPartService,HotelPartService,WebMarketerPartService{
	private DatabaseMySql mySql=null;
	private Connection webAdminConnection=null;

	public WebAdminDatabaseImpl(DatabaseInfo info) throws RemoteException{
		mySql=new DatabaseMySql(info);
		webAdminConnection=mySql.init();
	}

	public WebAdminPO get(String webAdminId) throws RemoteException {
		if(webAdminConnection==null){
			webAdminConnection=mySql.init();
		}

		try{
			PreparedStatement getInfo=webAdminConnection.prepareStatement("select * from webadmin where" +
					"employeeid = ?");
			getInfo.setString(1,webAdminId);
			ResultSet resultSet=getInfo.executeQuery();

			resultSet.next();
			String id=webAdminId;
			String password=resultSet.getString(1);

			getInfo.close();
			webAdminConnection.close();
			webAdminConnection=null;

			WebAdminPO webAdminPO=new WebAdminPO(password,id);
			return webAdminPO;

		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
}
