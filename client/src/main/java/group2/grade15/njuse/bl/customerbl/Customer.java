package group2.grade15.njuse.bl.customerbl;


import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CustomerVO;

import java.rmi.RemoteException;

/**
 * Created by Guo on 2016/11/6.
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
