package group2.grade15.njuse.bl.hotelbl;

import group2.grade15.njuse.bl.orderbl.OrderList;
import group2.grade15.njuse.bl.orderbl.OrderListBL;
import group2.grade15.njuse.blservice.HotelServ;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.po.RoomPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.OrderVO;
import group2.grade15.njuse.vo.RoomVO;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * HotelController的职责是接受酒店管理界面发来的请求
 * 并转交给具体的酒店管理逻辑处理
 * 具体的方法的定义可查看对应接口里的方法注释
 * @author Guo
 */
public class HotelController implements HotelServ, GetHotelListBL{
    HotelBL hotelBL;
    RoomBL roomBL;
    OrderListBL orderListBL;

    public HotelController() {
        hotelBL = new Hotel();
        roomBL = new Room();
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
        return roomBL.modifyRoomInfo(hotelID, roomInfo);
    }

    @Override
    public HotelListVO getBookedHotelList(int customerID) {
        ArrayList<OrderVO> orderList = orderListBL.getAllOrderListByCustomerID(customerID).getOrderList();
        ArrayList<HotelVO> hotelList = new ArrayList();

        HashSet<Integer> hotelIDSet = orderList.stream()
                                      .map(OrderVO::getHotelID)
                                      .collect(Collectors.toCollection(HashSet::new));

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
