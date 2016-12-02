package group2.grade15.njuse.blservice;

import group2.grade15.njuse.vo.CreditListVO;
import group2.grade15.njuse.vo.CreditVO;

public interface CreditHistoryServ {
	public CreditVO getCredit(int customerId); 

	public CreditListVO getCreditHistory (int customerId);
}
