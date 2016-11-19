package bl.loginbl;

import blservice.LoginControllerServ;
import dataservice.CustomerDataService;
import dataservice.HotelManagerDataService;
import dataservice.WebAdminDataService;
import dataservice.WebMarketerDataService;
import utility.IDType;
import utility.ResultMessage;

public class LoginController implements LoginControllerServ {
	CustomerDataService customerData;
	HotelManagerDataService hotelManager;
	WebMarketerDataService webMarketer;
	WebAdminDataService webAdmin;
	
	@Override
	public ResultMessage login(int customerId, String password, IDType type) {
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
