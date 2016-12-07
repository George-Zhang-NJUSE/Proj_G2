package group2.grade15.njuse.bl.hotelbl;

import group2.grade15.njuse.bl.orderbl.OrderList;
import group2.grade15.njuse.bl.orderbl.OrderListBL;
import group2.grade15.njuse.blservice.HotelServ;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.OrderVO;
import group2.grade15.njuse.vo.RoomVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Guo on 2016/12/4.
 */
public class HotelController implements HotelServ, GetHotelListBL {
    HotelBL hotelBL;
    OrderListBL orderListBL;

    public HotelController() {
        hotelBL = new Hotel();
        orderListBL = new OrderList();
    }

    @Override
    public ResultMessage modifyInfo(HotelVO hotel) {
        return hotelBL.modifyInfo(hotel);
    }

    @Override
    public HotelVO getInfo(int hotelID) {
        return hotelBL.getInfo(hotelID);
    }

    @Override
    public ResultMessage modifyRoomInfo(int hotelID, RoomVO roomInfo) {
        return hotelBL.modifyRoomInfo(hotelID, roomInfo);
    }

    @Override
    public HotelListVO getBookedHotelList(int customerID) {
        ArrayList<OrderVO> orderList = orderListBL.getAllOrderList(customerID).getOrderList();
        ArrayList<HotelVO> hotelList = new ArrayList();
        HashSet<Integer> hotelIDSet = new HashSet();

        for (OrderVO order : orderList) {
            hotelIDSet.add(order.getHotelID());
        }

        for (int hotelID : hotelIDSet) {
            HotelPO hotelPO = null;
            try {
                hotelPO = RemoteHelper.getInstance().getHotelDataService().getHotel(hotelID);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            if (hotelPO != null) {
                hotelList.add(new HotelVO(hotelPO));
            }
        }

        return new HotelListVO(hotelList);
    }
}
