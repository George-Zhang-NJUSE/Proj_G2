package group2.grade15.njuse.dataservice_driver;

import group2.grade15.njuse.dataservice.HotelPromotionDataService;
import group2.grade15.njuse.po.HotelPromotionListPO;

import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public class HotelPromotionDataService_Driver {

    public void drive(HotelPromotionDataService hotelPromotionDataService) {
        try {
            HotelPromotionListPO hotelPromotionListPO = hotelPromotionDataService.getList(123456);
//            ResultMessage addInfo = hotelPromotionDataService.add(new PromotionPO());
//            ResultMessage modifyInfo = hotelPromotionDataService.modify(new PromotionPO());
//            ResultMessage removeInfo = hotelPromotionDataService.remove(12346);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HotelPromotionDataService_Driver demo = new HotelPromotionDataService_Driver();
//        demo.drive(new HotelPromotionData());接口实现
    }
}
