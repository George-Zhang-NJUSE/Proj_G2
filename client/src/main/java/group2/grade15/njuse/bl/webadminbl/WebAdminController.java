package group2.grade15.njuse.bl.webadminbl;

import group2.grade15.njuse.blservice.WebAdminServ;
import group2.grade15.njuse.dataservice.webadmindataservice.WebAdminDataService;
import group2.grade15.njuse.po.WebAdminPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.vo.WebAdminVO;

import java.rmi.RemoteException;

public class WebAdminController implements WebAdminServ {
	private WebAdminVO webAdminVO;
	private HotelProxy hotelProxy;
	private UserProxy userProxy;
	
	public WebAdminController(){
        webAdminVO = null;
		hotelProxy = new HotelProxy();
		userProxy = new UserProxy();
	}

	public WebAdminVO getInfo(String webAdminId) {
        WebAdminPO po = null;

		try {
			po = RemoteHelper.getInstance().getWebAdminDataService().getWebAdmin(webAdminId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if(po != null) {
            return webAdminVO = new WebAdminVO(Integer.parseInt(webAdminId), po.getPassword(), Integer.parseInt(po.getStaffID()));
        } else {
            return null;
        }
	}

	public HotelProxy getHotelProxy() {
		return hotelProxy;
	}

	public UserProxy getUserProxy() {
		return userProxy;
	}
	
}
