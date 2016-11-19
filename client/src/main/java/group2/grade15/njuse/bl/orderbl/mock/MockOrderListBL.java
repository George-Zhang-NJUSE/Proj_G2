package bl.orderbl.mock;

import bl.orderbl.Order;
import bl.orderbl.OrderListBL;
import data.orderdata.OrderData;
import dataservice.OrderDataService;
import utility.IDType;
import vo.OrderListVO;

import java.rmi.RemoteException;

/**
 * Created by George on 2016/11/6.
 */
public class MockOrderListBL implements OrderListBL{

    @Override
    public OrderListVO getAbnormalOrderList(int id, IDType type) {

        OrderDataService ods = new OrderData();
        OrderListVO ablist=null;

//        try {
//            ablist=ods.getList(123456,IDType.customer);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }

        return ablist;
    }
}
