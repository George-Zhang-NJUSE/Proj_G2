package group2.grade15.njuse.dataservice_driver;

import group2.grade15.njuse.dataservice.creditdataservice.CreditDataService;
import group2.grade15.njuse.po.CreditPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by George on 2016/10/16.
 */
public class CreditDataService_Driver {

    public void drive(CreditDataService creditDataService) {
        try {
            ArrayList<CreditPO> history = creditDataService.getHistory(123456);
//          ResultMessage addInfo = creditDataService.add(new CreditPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CreditDataService_Driver demo= new CreditDataService_Driver();
//        demo.drive(new CreditData());接口实现
    }
}
