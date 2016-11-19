package group2.grade15.njuse.dataservice_driver;

import group2.grade15.njuse.dataservice.WebPromotionDataService;
import group2.grade15.njuse.po.WebPromotionListPO;

import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public class WebPromotionDataService_Driver {

    public void drive(WebPromotionDataService webPromotionDataService) {
        try {
            WebPromotionListPO webMarketerPO = webPromotionDataService.getList();
//            ResultMessage addInfo = webPromotionDataService.add(new PromotionPO());
//            ResultMessage modifyInfo = webPromotionDataService.modify(new PromotionPO());
//            ResultMessage removeInfo = webPromotionDataService.remove(new PromotionPO());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WebPromotionDataService_Driver demo = new WebPromotionDataService_Driver();
//        demo.drive(new WebPromotionData());接口实现
    }
}
