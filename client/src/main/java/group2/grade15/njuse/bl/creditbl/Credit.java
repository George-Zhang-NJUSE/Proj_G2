package group2.grade15.njuse.bl.creditbl;

import group2.grade15.njuse.po.CreditPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CreditListVO;
import group2.grade15.njuse.vo.CreditVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Credit的职责是处理信用值相关的业务
 * 具体的方法的定义可查看对应接口里的方法注释
 * @author Guo
 */
public class Credit implements CreditBL{

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

    public CreditVO getCredit(int customerId) {
        ArrayList<CreditPO> creditPOList = null;

        try {
            creditPOList = RemoteHelper.getInstance().getCreditDataService().getHistory(customerId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (creditPOList != null) {
            CreditPO po = creditPOList.get(0);
            return new CreditVO(po);
        } else {
            return null;
        }
    }

    public CreditListVO getCreditHistory(int customerId) {
        ArrayList<CreditPO> creditPOList = null;
        ArrayList<CreditVO> creditList = new ArrayList();

        try {
            creditPOList = RemoteHelper.getInstance().getCreditDataService().getHistory(customerId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (creditPOList != null) {
            creditList.addAll(creditPOList.stream().map(CreditVO::new).collect(Collectors.toList()));
        }

        return new CreditListVO(creditList);
    }
}
