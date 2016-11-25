package group2.grade15.njuse.webadmindata;

import java.rmi.RemoteException;

import group2.grade15.njuse.dataservice.WebAdminDataService;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.po.WebAdminPO;
import group2.grade15.njuse.utility.ResultMessage;

public class WebAdminData implements WebAdminDataService {

	public WebAdminPO get(int webAdminId) throws RemoteException {
		return null;
	}

	@Override
	public CustomerPO getCustomerInfo(int customerID) throws RemoteException {
		return null;
	}

	@Override
	public ResultMessage modifyCustomerInfo(CustomerPO customerPO) throws RemoteException {
		return null;
	}
}
