package group2.grade15.njuse.data.webmarketerdata;

import group2.grade15.njuse.dataservice.WebMarketerDataService;
import group2.grade15.njuse.po.WebMarketerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;

public class WebMarketerData implements WebMarketerDataService{

	public WebMarketerPO get(int webMarketerId) throws RemoteException {
		return null;
	}

	public ResultMessage add(WebMarketerPO po) throws RemoteException {
		return null;
	}

	public ResultMessage modify(WebMarketerPO po) throws RemoteException {
		return null;
	}

	public ResultMessage remove(int webMarketerId) throws RemoteException {
		return null;
	}
}
