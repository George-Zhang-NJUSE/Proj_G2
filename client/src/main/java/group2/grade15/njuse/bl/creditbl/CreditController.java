package group2.grade15.njuse.bl.creditbl;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CreditListVO;
import group2.grade15.njuse.vo.CreditVO;

public class CreditController implements CreditModificationBL,CreditHistoryBL{
	Credit credit;

    public CreditController(){
        credit = new Credit();
    }

	@Override
	public ResultMessage modifyCredit(CreditVO creditVO) {
		return credit.modifyCredit(creditVO);
	}

	@Override
	public CreditVO getCredit(int customerId) {
        return credit.getCredit(customerId);
	}

	@Override
	public CreditListVO getCreditHistory(int customerId) {
		return credit.getCreditHistory(customerId);
	}

}
