package group2.grade15.njuse.data.commentdata;

import group2.grade15.njuse.dataservice.CommentDataService;
import group2.grade15.njuse.po.CommentListPO;
import group2.grade15.njuse.po.CommentPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;

public class CommentData implements CommentDataService {


	public ResultMessage add(CommentPO po) throws RemoteException {
		return null;
	}

	public ResultMessage modify(CommentPO po) throws RemoteException {
		return null;
	}

	public CommentListPO getHotelComments(int hotelID) throws RemoteException {
		return null;
	}

	public CommentListPO getCustomerComments(int customerID) throws RemoteException {
		return null;
	}
}
