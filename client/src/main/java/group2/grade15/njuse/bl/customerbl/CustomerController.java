package bl.customerbl;

import blservice.CustomerServ;
import utility.ResultMessage;
import vo.CustomerVO;

/**
 * Created by George on 2016/11/13.
 */
public class CustomerController implements CustomerBL, CustomerServ {

    @Override
    public ResultMessage addCustomer(CustomerVO newCustomerVO) {
        return null;
    }

    @Override
    public CustomerVO getInfo(int customerId) {
        return null;
    }

    @Override
    public ResultMessage modifyInfo(CustomerVO vo) {
        return null;
    }
}
