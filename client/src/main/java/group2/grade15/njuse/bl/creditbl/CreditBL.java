package group2.grade15.njuse.bl.creditbl;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CreditListVO;
import group2.grade15.njuse.vo.CreditVO;

public interface CreditBL {
    public ResultMessage modifyCredit(CreditVO credit);

    public CreditVO getCredit(int customerId);

    public CreditListVO getCreditHistory(int customerId);
}
