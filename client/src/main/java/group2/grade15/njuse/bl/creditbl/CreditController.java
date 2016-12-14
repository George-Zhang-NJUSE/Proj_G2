package group2.grade15.njuse.bl.creditbl;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CreditListVO;
import group2.grade15.njuse.vo.CreditVO;

/**
 * CreditController的职责是接受信用界面发来的请求
 * 并转交给具体的信用处理逻辑
 * 具体的方法的定义可查看对应接口里的方法注释
 * @author Guo
 */
public class CreditController implements CreditModificationBL, CreditHistoryBL {
    Credit credit;

    public CreditController() {
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
