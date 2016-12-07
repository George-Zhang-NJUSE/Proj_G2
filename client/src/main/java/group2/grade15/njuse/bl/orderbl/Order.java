package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.bl.hotelbl.Hotel;
import group2.grade15.njuse.bl.hotelbl.HotelBL;
import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.OrderVO;
import group2.grade15.njuse.vo.RoomVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by George on 2016/11/6.
 */
public class Order {

    private HotelBL hotel;

    public Order(){
        hotel = new Hotel();
    }

    public OrderVO getInfo(int orderID) {
        OrderPO po = null;

        try {
            po = RemoteHelper.getInstance().getOrderDataService().getOrder(orderID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (po != null) {
            return new OrderVO(po);
        } else {
            return null;
        }
    }

    public ResultMessage modifyState(int orderID, OrderState state) {
        try {
            return RemoteHelper.getInstance().getOrderDataService().modifyOrder(orderID, state);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.CONNECTION_EXCEPTION;
        }
    }

    public ResultMessage savePO(OrderVO order) {
        try {
            return RemoteHelper.getInstance().getOrderDataService().addOrder(order.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.CONNECTION_EXCEPTION;
        }
    }

    public OrderVO createPO(OrderVO orderVO){
        RoomType roomType = orderVO.getType();
        int hotelID = orderVO.getHotelID();
        int roomNum = orderVO.getRoomSum();
        double roomPrice = -1;

        HotelVO hotelVO = hotel.getInfo(hotelID);
        ArrayList<RoomVO> roomList = hotelVO.getRoomList();
        for(RoomVO room : roomList){
            if(room.getType() == roomType){
                roomPrice = room.getPrice();
            }
        }

        double totalPrice = roomPrice * roomNum;

        //优惠策略的计算

        int useDpromotionID = 0;
        double newAmount = totalPrice;

        return new OrderVO(orderVO, newAmount, useDpromotionID);
    }

}
