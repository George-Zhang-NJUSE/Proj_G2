package group2.grade15.njuse.bl.creditbl;

import group2.grade15.njuse.po.CreditPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CreditListVO;
import group2.grade15.njuse.vo.CreditVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Credit implements CreditModificationBL,CreditHistoryBL{

	@Override
	public ResultMessage modifyCredit(CreditVO credit) {
		ResultMessage result;
		try {
			result = RemoteHelper.getInstance().getCreditDataService().add(credit.toPO());
		} catch (RemoteException e) {
			result = ResultMessage.CONNECTION_EXCEPTION;
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public CreditVO getCredit(int customerId) {
		ArrayList<CreditPO> creditPOList = null;

		try {
			creditPOList = RemoteHelper.getInstance().getCreditDataService().getHistory(customerId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if(creditPOList != null) {
			CreditPO po = creditPOList.get(0);
			return new CreditVO(po);
		} else {
			return null;
		}
	}

	@Override
	public CreditListVO getCreditHistory(int customerId) {
		ArrayList<CreditPO> creditPOList = null;
		ArrayList<CreditVO> creditList = new ArrayList();

		try {
			creditPOList = RemoteHelper.getInstance().getCreditDataService().getHistory(customerId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if(creditPOList != null){
			for(CreditPO po : creditPOList){
				creditList.add(new CreditVO(po));
			}
		}

		return new CreditListVO(creditList);
	}

}
