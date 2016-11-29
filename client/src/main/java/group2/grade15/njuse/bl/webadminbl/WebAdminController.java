package group2.grade15.njuse.bl.webadminbl;

import group2.grade15.njuse.blservice.WebAdminServ;
import group2.grade15.njuse.po.WebAdminPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;

/**
 * 负责执行网站管理人员的业务逻辑
 * 只有获取网站管理人员信息的方法由自己的业务逻辑进行处理
 * 对酒店的管理采用代理的方式交给酒店代理进行真正的业务逻辑处理
 * 对用户的管理采用代理的方式交给用户代理进行真正的业务逻辑处理
 * */

public class WebAdminController implements WebAdminServ, HotelProxyBL, UserProxyBL{

	private HotelProxyBL hotelProxy;
	private UserProxyBL userProxy;
    private WebAdminPO webAdminPO;

	public WebAdminController(WebAdminPO po){
        webAdminPO = po;
		hotelProxy = new HotelProxyImpl();
		userProxy = new UserProxyImpl();
	}

	public WebAdminVO getInfo(String webAdminId) {

        WebAdminPO po = null;
		try {
			po = RemoteHelper.getInstance().getWebAdminDataService().getWebAdmin(webAdminId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if(po != null){
            return new WebAdminVO(po);
        } else {
            return null;
        }
	}

    @Override
    public ResultMessage createHotel(HotelVO hotel) {
        return hotelProxy.createHotel(hotel);
    }

    @Override
    public HotelListVO getHotelList() {
        return hotelProxy.getHotelList();
    }

    @Override
    public HotelListVO modifyHotel(HotelVO hotel) {
        return hotelProxy.modifyHotel(hotel);
    }

    @Override
    public ResultMessage deleteHotel(HotelVO hotel) {
        return hotelProxy.deleteHotel(hotel);
    }

    @Override
    public CustomerListVO getCustomerList() {
        return userProxy.getCustomerList();
    }

    @Override
    public HotelManagerListVO getHotelManagerList() {
        return userProxy.getHotelManagerList();
    }

    @Override
    public WebMarketerListVO getWebMarketerList() {
        return userProxy.getWebMarketerList();
    }

    @Override
    public ResultMessage createHotelManager(HotelManagerVO hotelManager) {
        return userProxy.createHotelManager(hotelManager);
    }

    @Override
    public ResultMessage modifyWebMarketer(WebMarketerVO webMarketer) {
        return userProxy.modifyWebMarketer(webMarketer);
    }

    @Override
    public ResultMessage createWebMarketer(WebMarketerVO webMarketer) {
        return userProxy.createWebMarketer(webMarketer);
    }

    @Override
    public ResultMessage deleteWebMarketer(WebMarketerVO webMarketer) {
        return deleteWebMarketer(webMarketer);
    }
}
