package group2.grade15.njuse.blservice;

import group2.grade15.njuse.bl.webadminbl.HotelProxy;
import group2.grade15.njuse.bl.webadminbl.UserProxy;
import group2.grade15.njuse.vo.WebAdminVO;

public interface WebAdminServ {

	public WebAdminVO getInfo(String webAdminId);

}
