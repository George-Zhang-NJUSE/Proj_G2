package group2.grade15.njuse.bl.webpromotionbl;

import group2.grade15.njuse.bl.hotelbl.Hotel;
import group2.grade15.njuse.bl.hotelbl.HotelBL;
import group2.grade15.njuse.bl.promotionfactory.WebPromotionBL;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.*;
import jdk.internal.org.objectweb.asm.commons.Remapper;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Guo on 2016/12/6.
 */
public class AreaWebPromotion implements WebPromotionBL {

    @Override
    public double countPrice(OrderVO orderVO, WebPromotionVO webPromotionVO) {
        int roomNum = orderVO.getRoomSum();
        int hotelID = orderVO.getHotelID();
        RoomType roomType = orderVO.getType();

        HotelBL hotel = new Hotel();
        double roomPrice = -1;

        HotelVO hotelVO = hotel.getInfo(hotelID);
        ArrayList<RoomVO> roomList = hotelVO.getRoomList();
        for (RoomVO room : roomList) {
            if (room.getType() == roomType) {
                roomPrice = room.getPrice();
            }
        }

        double totalPrice = roomPrice * roomNum;

        if(isFit(orderVO, webPromotionVO)) {
            return totalPrice * webPromotionVO.getDiscount();
        } else {
            return totalPrice;
        }
    }

    private boolean isFit(OrderVO orderVO, WebPromotionVO webPromotionVO){
        String applicableAddress = webPromotionVO.getAddress();
        //获取酒店的商圈地址
        HotelPO hotelPO = null;
        int hotelID = orderVO.getHotelID();

        try {
            hotelPO = RemoteHelper.getInstance().getHotelDataService().getHotel(hotelID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        String hoteAddress = hotelPO.getAddress();
        return applicableAddress.equals(hoteAddress);
    }
}
