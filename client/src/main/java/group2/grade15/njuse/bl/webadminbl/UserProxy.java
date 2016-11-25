package group2.grade15.njuse.bl.webadminbl;

import group2.grade15.njuse.dataservice.cusotmerdataservice.CustomerDataService;
import group2.grade15.njuse.dataservice.hotelmanagerdataservice.HotelManagerDataService;
import group2.grade15.njuse.dataservice.webadmindataservice.WebAdminDataService;
import group2.grade15.njuse.dataservice.webmarketerdataservice.WebMarketerDataService;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CustomerListVO;
import group2.grade15.njuse.vo.HotelManagerListVO;
import group2.grade15.njuse.vo.HotelManagerVO;
import group2.grade15.njuse.vo.WebMarketerVO;

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
