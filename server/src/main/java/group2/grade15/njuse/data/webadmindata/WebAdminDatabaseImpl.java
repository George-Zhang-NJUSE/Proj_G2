package group2.grade15.njuse.data.webadmindata;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.webadmindataservice.*;
import group2.grade15.njuse.po.*;
import group2.grade15.njuse.utility.ResultMessage;

public class WebAdminDatabaseImpl implements WebAdminDataService,CustomerPartService,HotelManagerPartService,HotelPartService,WebMarketerPartService{
	private DatabaseInfo info=null;
	private DatabaseMySql mySql=null;
	private Connection webAdminConnection=null;

	private CustomerPart customerPart=null;
	private HotelManagerPart hotelManagerPart=null;
	private WebMarketerPart webMarketerPart=null;

	public WebAdminDatabaseImpl(DatabaseInfo info) throws RemoteException{
		this.info=info;
		mySql=new DatabaseMySql(info);
		webAdminConnection=mySql.init();
	}

	public WebAdminPO get(String webAdminId) throws RemoteException {
		if(webAdminConnection==null){
			webAdminConnection=mySql.init();
		}

		try{
			PreparedStatement getInfo=webAdminConnection.prepareStatement("select * from webadmin where " +
					"employeeid=?");
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

	@Override
	public ArrayList<CustomerPO> getCustomerInfo() throws RemoteException {
		if(customerPart==null){
			customerPart=new CustomerPart(info);
		}

		return customerPart.getCustomerInfo();
	}

	@Override
	public ResultMessage modifyCustomerInfo(CustomerPO customerPO) throws RemoteException {
		if(customerPart==null){
			customerPart=new CustomerPart(info);
		}

		return customerPart.modifyCustomerInfo(customerPO);
	}

	@Override
	public ArrayList<HotelManagerPO> getHotelManagerInfo() throws RemoteException {
		if(hotelManagerPart==null){
			hotelManagerPart=new HotelManagerPart(info);
		}
		return hotelManagerPart.getHotelManagerInfo();
	}

	@Override
	public ResultMessage modifyHotelManagerInfo(HotelManagerPO hotelManagerPO) throws RemoteException {
		if(hotelManagerPart==null){
			hotelManagerPart=new HotelManagerPart(info);
		}
		return hotelManagerPart.modifyHotelManagerInfo(hotelManagerPO);
	}

	@Override
	public ResultMessage addWebMarketerInfo(WebMarketerPO webMarketerPO) throws RemoteException {
		if(webMarketerPart==null){
			webMarketerPart=new WebMarketerPart(info);
		}
		return webMarketerPart.addWebMarketerInfo(webMarketerPO);
	}

	@Override
	public ArrayList<WebMarketerPO> getWebMarketerInfo() throws RemoteException {
		if(webMarketerPart==null){
			webMarketerPart=new WebMarketerPart(info);
		}

		return webMarketerPart.getWebMarketerInfo();
	}

	@Override
	public ResultMessage modifyWebMarketerInfo(WebMarketerPO webMarketerPO) throws RemoteException {
		if(webMarketerPart==null){
			webMarketerPart=new WebMarketerPart(info);
		}

		return webMarketerPart.modifyWebMarketerInfo(webMarketerPO);
	}

	@Override
	public ResultMessage deleteWebMarketer(String webMarketerID) throws RemoteException {
		if(webMarketerPart==null){
			webMarketerPart=new WebMarketerPart(info);
		}

		return webMarketerPart.deleteWebMarketer(webMarketerID);
	}
}
