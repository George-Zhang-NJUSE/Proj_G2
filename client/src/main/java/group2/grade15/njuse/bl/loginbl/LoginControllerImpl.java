package group2.grade15.njuse.bl.loginbl;

import group2.grade15.njuse.blservice.LoginControllerServ;
import group2.grade15.njuse.dataservice.CustomerDataService;
import group2.grade15.njuse.dataservice.HotelManagerDataService;
import group2.grade15.njuse.dataservice.WebAdminDataService;
import group2.grade15.njuse.dataservice.WebMarketerDataService;
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
