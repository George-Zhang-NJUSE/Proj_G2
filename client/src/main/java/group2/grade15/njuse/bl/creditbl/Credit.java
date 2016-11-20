package group2.grade15.njuse.bl.creditbl;

import group2.grade15.njuse.blservice.CreditHistoryServ;
import group2.grade15.njuse.blservice.CreditModificationServ;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CreditListVO;
import group2.grade15.njuse.vo.CreditVO;

public class Credit implements CreditModificationServ,CreditHistoryServ{


	public ResultMessage modifyCredit(CreditVO Credit) {
		return null;
	}

	public CreditVO getCredit(int customerId) {
		return null;
	}

	public CreditListVO getCreditHistory(CreditVO CreditInfo) {
		return null;
	}
}
