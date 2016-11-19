package blservice;

import vo.CreditListVO;
import vo.CreditVO;

public interface CreditHistoryServ {
	public CreditVO getCredit(int customerId); 

	public CreditListVO getCreditHistory (CreditVO CreditInfo);
}
