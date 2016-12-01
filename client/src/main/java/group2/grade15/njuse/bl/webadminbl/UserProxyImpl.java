package group2.grade15.njuse.bl.webadminbl;

import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.po.HotelManagerPO;
import group2.grade15.njuse.po.WebMarketerPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * 负责处理用户业务的代理
 * 数据的处理通过RMI直接调用WebAdminDataService
 */
public class UserProxyImpl implements UserProxyBL{
	
	public CustomerListVO getCustomerList(){
		ArrayList<CustomerPO> customerPOList = null;
		ArrayList<CustomerVO> customerList = new ArrayList();
		try {
			customerPOList = RemoteHelper.getInstance().getWebAdminDataService().getCustomerInfo();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		for(CustomerPO po : customerPOList){
			customerList.add(new CustomerVO(po));
		}

		return new CustomerListVO(customerList);
	}

	public HotelManagerListVO getHotelManagerList(){
		ArrayList<HotelManagerPO> hotelManagerPOList = null;
		ArrayList<HotelManagerVO> hotelManagerList = new ArrayList();
		try {
			hotelManagerPOList = RemoteHelper.getInstance().getWebAdminDataService().getHotelManagerInfo();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		for(HotelManagerPO po : hotelManagerPOList){
			hotelManagerList.add(new HotelManagerVO(po));
		}

		return new HotelManagerListVO(hotelManagerList);
	}
	
	public WebMarketerListVO getWebMarketerList(){
		ArrayList<WebMarketerPO> webMarketerPOList = null;
		ArrayList<WebMarketerVO> webMarketerList = new ArrayList();
		try {
			webMarketerPOList = RemoteHelper.getInstance().getWebAdminDataService().getWebMarketerInfo();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		for(WebMarketerPO po : webMarketerPOList){
			webMarketerList.add(new WebMarketerVO(po));
		}

		return new WebMarketerListVO(webMarketerList);
	}

	/***
	 * 创建酒店管理人员的方法
	 * 先根据HotelManagerVO.getHotelID获取对应的酒店ID
	 * 然后根据酒店ID和getHotel()方法获取对应的酒店
	 * 再检查该酒店是否已经拥有酒店管理人员：
	 * 如果无，就在数据库添加该酒店管理人员，并将该酒店的haveManager设置为true
	 * 否则，返回ResultMessage.EXISTENT
	 */
	public ResultMessage createHotelManager(HotelManagerVO hotelManager){
		ResultMessage result = ResultMessage.FAILED;
		try {
			RemoteHelper.getInstance().getWebAdminDataService().addHotelManagerInfo(hotelManager.toPO());
		} catch (RemoteException e) {
			result = ResultMessage.CONNECTION_EXCEPTION;
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultMessage modifyWebMarketer(WebMarketerVO webMarketer){
		ResultMessage result = ResultMessage.FAILED;
		try {
			result = RemoteHelper.getInstance().getWebAdminDataService().modifyWebMarketerInfo(webMarketer.toPO());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultMessage createWebMarketer(WebMarketerVO webMarketer){
		ResultMessage result = ResultMessage.FAILED;
		try {
			result = RemoteHelper.getInstance().getWebAdminDataService().addWebMarketerInfo(webMarketer.toPO());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultMessage deleteWebMarketer(WebMarketerVO webMarketer){
		ResultMessage result = ResultMessage.FAILED;
		try {
			result = RemoteHelper.getInstance().getWebAdminDataService().deleteWebMarketer(webMarketer.getStaffID());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;
	}
}
