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
 * 负责用户业务的管理
 * 数据的处理通过RMI直接调用WebAdminDataService
 */
public class UserManageImpl implements UserManageBL{
	
	public CustomerListVO getCustomerList(){
		ArrayList<CustomerPO> customerPOList = null;
		ArrayList<CustomerVO> customerList = new ArrayList();
		try {
			customerPOList = RemoteHelper.getInstance().getWebAdminDataService().getCustomerInfo();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if(customerPOList != null) {
			for (CustomerPO po : customerPOList) {
				customerList.add(new CustomerVO(po));
			}
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

		if(hotelManagerPOList != null) {
			for (HotelManagerPO po : hotelManagerPOList) {
				hotelManagerList.add(new HotelManagerVO(po));
			}
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

		if(webMarketerPOList != null) {
			for (WebMarketerPO po : webMarketerPOList) {
				webMarketerList.add(new WebMarketerVO(po));
			}
		}

		return new WebMarketerListVO(webMarketerList);
	}

	public ResultMessage createHotelManager(HotelManagerVO hotelManager){
		ResultMessage result;
		try {
			result = ResultMessage.SUCCESS;
			RemoteHelper.getInstance().getWebAdminDataService().addHotelManagerInfo(hotelManager.toPO());
		} catch (RemoteException e) {
			result = ResultMessage.CONNECTION_EXCEPTION;
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultMessage modifyWebMarketer(WebMarketerVO webMarketer){
		ResultMessage result;
		try {
			result = RemoteHelper.getInstance().getWebAdminDataService().modifyWebMarketerInfo(webMarketer.toPO());
		} catch (RemoteException e) {
			result = ResultMessage.CONNECTION_EXCEPTION;
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultMessage createWebMarketer(WebMarketerVO webMarketer){
		ResultMessage result;
		try {
			result = RemoteHelper.getInstance().getWebAdminDataService().addWebMarketerInfo(webMarketer.toPO());
		} catch (RemoteException e) {
			result = ResultMessage.CONNECTION_EXCEPTION;
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultMessage deleteWebMarketer(WebMarketerVO webMarketer){
		ResultMessage result;
		try {
			result = RemoteHelper.getInstance().getWebAdminDataService().deleteWebMarketer(webMarketer.getStaffID());
		} catch (RemoteException e) {
			result = ResultMessage.CONNECTION_EXCEPTION;
			e.printStackTrace();
		}
		return result;
	}
}
