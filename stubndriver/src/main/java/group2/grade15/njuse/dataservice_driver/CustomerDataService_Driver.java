package group2.grade15.njuse.dataservice_driver;

import group2.grade15.njuse.dataservice.cusotmerdataservice.CustomerDataService;
import group2.grade15.njuse.po.CustomerPO;

import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public class CustomerDataService_Driver {

    public void drive(CustomerDataService customerDataService) {
        try {
            CustomerPO customerPO = customerDataService.getCustomer(123456);
//            ResultMessage addInfo = customerDataService.add(new CustomerPO());
//            ResultMessage modifyInfo = customerDataService.modify(new CustomerPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CustomerDataService_Driver demo = new CustomerDataService_Driver();
//        demo.drive(new CustomerData());接口实现
    }
}
