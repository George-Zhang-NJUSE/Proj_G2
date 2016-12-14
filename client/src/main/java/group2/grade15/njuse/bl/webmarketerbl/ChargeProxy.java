package group2.grade15.njuse.bl.webmarketerbl;

import group2.grade15.njuse.bl.creditbl.CreditController;
import group2.grade15.njuse.bl.creditbl.CreditModificationBL;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CreditVO;

/**
 * 信用值充值职责的代理者
 * 真正的任务通过层内接口CreditModificationBL代理给CreditController完成
 */
public class ChargeProxy {

    private CreditModificationBL creditModificationBL;

    public ChargeProxy() {
        creditModificationBL = new CreditController();
    }

    public ResultMessage modifyCredit(CreditVO credit) {
        return creditModificationBL.modifyCredit(credit);
    }

}
