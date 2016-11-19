package group2.grade15.njuse.data.creditdata;

import group2.grade15.njuse.dataservice.CreditDataService;
import group2.grade15.njuse.po.CreditListPO;
import group2.grade15.njuse.po.CreditPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;

public class CreditData implements CreditDataService{

	public CreditListPO getHistory(int customerId) throws RemoteException {
		return null;
	}

	public ResultMessage add(CreditPO po) throws RemoteException {
		return null;
	}
}
