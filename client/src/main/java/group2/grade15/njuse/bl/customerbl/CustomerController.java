package group2.grade15.njuse.bl.customerbl;

import group2.grade15.njuse.blservice.CustomerServ;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CreditListVO;
import group2.grade15.njuse.vo.CustomerVO;

import java.rmi.RemoteException;

/**
 * CustomerController的职责是接客户界面发来的请求
 * 并转交给具体的客户业务逻辑处理
 * 具体的方法的定义可查看对应接口里的方法注释
 * @author Guo
 */
public class CustomerController implements CustomerServ {

    private CustomerBL customerBL;

    public CustomerController() {
        customerBL = new Customer();
    }

    public CustomerVO addCustomer(CustomerVO newCustomerVO) {
        try {
            CustomerPO po = RemoteHelper.getInstance().getCustomerDataService().add(newCustomerVO.toPO());
            if(po != null) {
                return new CustomerVO(po);
            } else {
                return null;
            }
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

    @Override
    public CreditListVO getCreditHistory(int customerID) {
        return customerBL.getCreditHistory(customerID);
    }
}
