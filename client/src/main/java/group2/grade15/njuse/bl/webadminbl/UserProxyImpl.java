package group2.grade15.njuse.bl.webadminbl;

import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.po.HotelManagerPO;
import group2.grade15.njuse.po.WebMarketerPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

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

	public ResultMessage createHotelManager(HotelManagerVO hotelManager){
		return null;
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
