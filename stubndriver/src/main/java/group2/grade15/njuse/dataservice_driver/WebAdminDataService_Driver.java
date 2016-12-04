package group2.grade15.njuse.dataservice_driver;

import group2.grade15.njuse.dataservice.webadmindataservice.WebAdminDataService;
import group2.grade15.njuse.po.WebAdminPO;

import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public class WebAdminDataService_Driver {

    public void drive(WebAdminDataService webAdminDataService) {
        try {
            WebAdminPO webAdminPO = webAdminDataService.getWebAdmin("wm00000000");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WebAdminDataService_Driver demo = new WebAdminDataService_Driver();
//        demo.drive(new WebAdminData());接口实现
    }
}
