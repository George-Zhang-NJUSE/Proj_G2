package group2.grade15.njuse.dataservice_driver;

import group2.grade15.njuse.dataservice.webmarketerdataservice.WebMarketerDataService;
import group2.grade15.njuse.po.WebMarketerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public class WebMarketerDataService_Driver {

    public void drive(WebMarketerDataService webMarketerDataService) {
        try {
            WebMarketerPO webMarketerPO = webMarketerDataService.getWebMarketer("wm00000000");
//          ResultMessage addInfo = webMarketerDataService.add(new WebMarketerPO());
//          ResultMessage modifyInfo = webMarketerDataService.modify(new WebMarketerPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WebMarketerDataService_Driver demo = new WebMarketerDataService_Driver();
//        demo.drive(new WebMarketerData());接口实现
    }
}
