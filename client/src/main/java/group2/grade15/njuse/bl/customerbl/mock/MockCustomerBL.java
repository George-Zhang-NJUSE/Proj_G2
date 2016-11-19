package bl.customerbl.mock;

import bl.customerbl.Customer;
import bl.customerbl.CustomerBL;
import data.customerdata.CustomerData;
import dataservice.CustomerDataService;
import po.CustomerPO;
import utility.ResultMessage;
import vo.CustomerVO;

import java.rmi.RemoteException;

/**
 * Created by George on 2016/11/6.
 */
public class MockCustomerBL implements CustomerBL {

    @Override
    public CustomerVO getInfo(int customerId) {

        CustomerDataService cds = new CustomerData();
        CustomerVO info=null;

        try {
            Customer customer=new Customer(cds.get(customerId));
            info=customer.getInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return info;
    }

    @Override
    public ResultMessage modifyInfo(CustomerVO vo) {
        return ResultMessage.SUCCESS;
    }


}
