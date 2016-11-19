package group2.grade15.njuse.dataservice_driver;

import group2.grade15.njuse.dataservice.HotelManagerDataService;
import group2.grade15.njuse.po.HotelManagerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public class HotelManagerDataService_Driver {

    public void drive(HotelManagerDataService hotelManagerDataService) {
        try {
            HotelManagerPO hotelManagerPO = hotelManagerDataService.get(123456);
//            ResultMessage addInfo = hotelManagerDataService.add(new HotelManagerPO());
//            ResultMessage modifyInfo = hotelManagerDataService.modify(new HotelManagerPO());
            ResultMessage removeInfo = hotelManagerDataService.remove(12346);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HotelManagerDataService_Driver demo = new HotelManagerDataService_Driver();
//        demo.drive(new HotelManagerData());接口实现
    }
}
