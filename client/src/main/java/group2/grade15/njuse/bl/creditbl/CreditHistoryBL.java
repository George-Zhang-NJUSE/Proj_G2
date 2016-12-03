package group2.grade15.njuse.bl.creditbl;

import group2.grade15.njuse.vo.CreditListVO;
import group2.grade15.njuse.vo.CreditVO;

/**
 * Created by Guo on 2016/12/4.
 */
public interface CreditHistoryBL {
    public CreditVO getCredit(int customerId);

    public CreditListVO getCreditHistory (int customerId);
}
