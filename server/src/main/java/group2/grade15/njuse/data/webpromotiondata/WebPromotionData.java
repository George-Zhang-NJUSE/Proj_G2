package group2.grade15.njuse.data.webpromotiondata;

import group2.grade15.njuse.dataservice.WebPromotionDataService;
import group2.grade15.njuse.po.RankPO;
import group2.grade15.njuse.po.WebPromotionPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class WebPromotionData implements WebPromotionDataService{

	@Override
	public ArrayList<WebPromotionPO> getList() throws RemoteException {
		return null;
	}

	@Override
	public ResultMessage modify(WebPromotionPO po) throws RemoteException {
		return null;
	}

	@Override
	public ResultMessage remove(WebPromotionPO po) throws RemoteException {
		return null;
	}

	@Override
	public ResultMessage add(WebPromotionPO po) throws RemoteException {
		return null;
	}

	@Override
	public RankPO getRank() throws RemoteException {
		return null;
	}
}
