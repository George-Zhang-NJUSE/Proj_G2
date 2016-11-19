package group2.grade15.njuse.dataservice_driver;

import group2.grade15.njuse.dataservice.OrderDataService;
import group2.grade15.njuse.po.OrderListPO;
import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.utility.IDType;

import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public class OrderDataService_Driver {

    public void drive(OrderDataService orderDataService) {
        try {
            OrderListPO hotelPromotionListPO = orderDataService.getList(123456, IDType.customer);
//            ResultMessage addInfo = orderDataService.add(new OrderPO());
//            ResultMessage modifyInfo = orderDataService.modify(new OrderPO());
            OrderPO orderPO = orderDataService.get(12346);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        OrderDataService_Driver demo = new OrderDataService_Driver();
//        demo.drive(new OrderData());接口实现
    }
}
