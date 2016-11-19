package blservice;

import bl.webadminbl.HotelProxy;
import bl.webadminbl.UserProxy;
import vo.WebAdminVO;

public interface WebAdminServ {
	public WebAdminVO getInfo(int WebAdminId);

	public HotelProxy getHotelProxy();

	public UserProxy getUserProxy();
}
