package group2.grade15.njuse.data.webmarketerdata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.webmarketerdataservice.WebMarketerDataService;
import group2.grade15.njuse.po.WebMarketerPO;

import java.rmi.RemoteException;
import java.sql.*;

public class WebMarketerDatabaseImpl implements WebMarketerDataService{
	private DatabaseMySql mySql = null;
	private Connection hotelManagerConnection = null;

	public WebMarketerDatabaseImpl(DatabaseInfo info) throws RemoteException{
		mySql = new DatabaseMySql(info);
		hotelManagerConnection=mySql.init();
	}

	/**
	 * @param webMarketerId
	 * @return WebMarketerPO
	 * @throws RemoteException
	 */
	public WebMarketerPO getWebMarketer(String webMarketerId) throws RemoteException {
		if(hotelManagerConnection==null){
			hotelManagerConnection=mySql.init();
		}

		try{
			PreparedStatement getInfo=hotelManagerConnection.prepareStatement("select * from webmarketer where" +
					"employeeid = ?");
			getInfo.setString(1,webMarketerId);
			ResultSet rs=getInfo.executeQuery();

			rs.next();
			String id=webMarketerId;
			String password=rs.getString(1);
			WebMarketerPO webMarketerPO=new WebMarketerPO(password,id);

			getInfo.close();
			hotelManagerConnection.close();
			hotelManagerConnection=null;

			return webMarketerPO;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
}
