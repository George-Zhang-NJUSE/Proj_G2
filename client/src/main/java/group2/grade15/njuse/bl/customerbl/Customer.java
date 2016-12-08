package group2.grade15.njuse.bl.customerbl;


import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CustomerVO;

import java.rmi.RemoteException;

/**
 * Customer的职责是处理单个客户相关的业务逻辑
 * 具体的方法的定义可查看对应接口里的方法注释
 * @author Guo
 */
public class Customer implements CustomerBL {

    public CustomerVO getInfo(int customerID) {
        CustomerPO po = null;
        try {
            po = RemoteHelper.getInstance().getCustomerDataService().getCustomer(customerID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (po != null) {
            return new CustomerVO(po);
        } else {
            return null;
        }
    }

    public ResultMessage modifyInfo(CustomerVO customerVO) {
        ResultMessage result;
        try {
            result = RemoteHelper.getInstance().getCustomerDataService().modify(customerVO.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.CONNECTION_EXCEPTION;
        }
        return result;
    }

}
