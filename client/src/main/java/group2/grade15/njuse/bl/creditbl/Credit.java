package bl.creditbl;

import blservice.CreditHistoryServ;
import blservice.CreditModificationServ;
import utility.ResultMessage;
import vo.CreditListVO;
import vo.CreditVO;

public class Credit implements CreditModificationServ,CreditHistoryServ{

	@Override
	public ResultMessage modifyCredit(CreditVO Credit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreditVO getCredit(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreditListVO getCreditHistory(CreditVO CreditInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}
