package group2.grade15.njuse.bl.loginbl;

import group2.grade15.njuse.blservice.LoginControllerServ;
import group2.grade15.njuse.dataservice.cusotmerdataservice.CustomerDataService;
import group2.grade15.njuse.dataservice.hotelmanagerdataservice.HotelManagerDataService;
import group2.grade15.njuse.dataservice.webadmindataservice.WebAdminDataService;
import group2.grade15.njuse.dataservice.webmarketerdataservice.WebMarketerDataService;
import group2.grade15.njuse.utility.IDType;
import group2.grade15.njuse.utility.ResultMessage;

public class LoginControllerImpl implements LoginControllerServ {
	CustomerDataService customerData;
	HotelManagerDataService hotelManager;
	WebMarketerDataService webMarketer;
	WebAdminDataService webAdmin;

	public ResultMessage login(int id, String password, IDType type) {
		ResultMessage result = null;
		
		switch(type){
			case customer: result = ResultMessage.SUCCESS; break;
				
			case hotelManager: result = ResultMessage.SUCCESS; break;
			
			case webMarketer: result = ResultMessage.SUCCESS; break;
			
			case webAdmin: result = ResultMessage.SUCCESS; break;
		}
		
		return result;
	}
	
}
