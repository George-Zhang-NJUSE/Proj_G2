package bl.webadminbl;

import dataservice.CustomerDataService;
import dataservice.HotelManagerDataService;
import dataservice.WebAdminDataService;
import dataservice.WebMarketerDataService;
import utility.ResultMessage;
import vo.CustomerListVO;
import vo.HotelManagerListVO;
import vo.HotelManagerVO;
import vo.WebMarketerVO;

public class UserProxy {
	CustomerDataService customerData;
	HotelManagerDataService hotelManager;
	WebMarketerDataService webMarketer;
	WebAdminDataService webAdmin;
	
	public CustomerListVO getCustomerList(){
		return null;
	}

	public HotelManagerListVO getHotelManagerList(){
		return null;
	}
	
	public WebMarketerVO getWebMarketerList(){
		return null;
	}

	public ResultMessage createHotelManager(HotelManagerVO hotelManager){
		return null;
	}
	
	public ResultMessage modifyWebMarketer(WebMarketerVO webMarketer){
		return null;
	}
	
	public ResultMessage createWebMarketer(WebMarketerVO webMarketer){
		return null;
	}
	
	public ResultMessage deleteWebMarketer(WebMarketerVO webMarketer){
		return null;
	}
}
