package group2.grade15.njuse.dataservice_driver;

import group2.grade15.njuse.dataservice.HotelDataService;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public class HotelDataService_Driver {

    public void drive(HotelDataService hotelDataService) {
        try {
            HotelPO hotelPO = hotelDataService.get(123456);
//            ResultMessage addInfo = hotelDataService.add(new HotelPO());
//            ResultMessage modifyInfo = hotelDataService.modify(new HotelPO());
//            ResultMessage modifyRoomInfo = hotelDataService.modifyRoom(123456, new RoomPO());
            ResultMessage removeInfo = hotelDataService.remove(12346);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HotelDataService_Driver demo = new HotelDataService_Driver();
//        demo.drive(new HotelData());接口实现
    }
}
