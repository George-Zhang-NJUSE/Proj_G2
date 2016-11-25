package group2.grade15.njuse.bl.webadminbl;

import group2.grade15.njuse.blservice.WebAdminServ;
import group2.grade15.njuse.dataservice.webadmindataservice.WebAdminDataService;
import group2.grade15.njuse.vo.WebAdminVO;

public class WebAdminController implements WebAdminServ {
	WebAdminDataService webadminData;
	HotelProxy hotelProxy;
	UserProxy userProxy;
	
	public WebAdminController(){
		hotelProxy = new HotelProxy();
		userProxy = new UserProxy();
	}

	public WebAdminVO getInfo(int WebAdminId) {
		return null;
	}

	public HotelProxy getHotelProxy() {
		return hotelProxy;
	}

	public UserProxy getUserProxy() {
		return userProxy;
	}
	
}
