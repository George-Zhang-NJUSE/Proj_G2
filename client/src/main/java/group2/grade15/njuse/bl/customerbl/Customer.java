package group2.grade15.njuse.bl.customerbl;


import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CustomerVO;

import java.rmi.RemoteException;

/**
 * Created by Guo on 2016/11/6.
 */
public class Customer {

    CustomerPO customerPO;

    public Customer(CustomerPO po) {
        customerPO = po;
    }

    public CustomerVO getInfo() {

        if(customerPO != null) {
            return new CustomerVO(customerPO);
        } else {
            return null;
        }
    }

    public ResultMessage modifyInfo(CustomerVO vo) {
        try {
            return RemoteHelper.getInstance().getCustomerDataService().modify(vo.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    public int getId() {
        return customerPO.getId();
    }

}
