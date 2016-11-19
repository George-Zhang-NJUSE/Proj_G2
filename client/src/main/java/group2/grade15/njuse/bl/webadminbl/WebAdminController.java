package bl.webadminbl;

import blservice.WebAdminServ;
import dataservice.WebAdminDataService;
import vo.WebAdminVO;

public class WebAdminController implements WebAdminServ {
	WebAdminDataService webadminData;
	HotelProxy hotelProxy;
	UserProxy userProxy;
	
	public WebAdminController(){
		hotelProxy = new HotelProxy();
		userProxy = new UserProxy();
	}

	@Override
	public WebAdminVO getInfo(int WebAdminId) {
		return null;
	}

	@Override
	public HotelProxy getHotelProxy() {
		return hotelProxy;
	}

	@Override
	public UserProxy getUserProxy() {
		return userProxy;
	}
	
}
