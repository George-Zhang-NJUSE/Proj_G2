package group2.grade15.njuse.bl.customerbl;

import group2.grade15.njuse.blservice.CustomerServ;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CustomerVO;

import java.rmi.RemoteException;

/**
 * Created by George on 2016/11/13.
 */
public class CustomerController implements CustomerServ {

    private CustomerBL customerBL;

    public CustomerController() {
        customerBL = new Customer();
    }

    public CustomerVO addCustomer(CustomerVO newCustomerVO) {
        try {
            CustomerPO po = RemoteHelper.getInstance().getCustomerDataService().add(newCustomerVO.toPO());
            return new CustomerVO(po);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public CustomerVO getInfo(int customerId) {
        return customerBL.getInfo(customerId);
    }

    public ResultMessage modifyInfo(CustomerVO vo) {
        return customerBL.modifyInfo(vo);
    }
}
