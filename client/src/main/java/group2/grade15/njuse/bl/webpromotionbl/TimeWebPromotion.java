package group2.grade15.njuse.bl.webpromotionbl;

import group2.grade15.njuse.bl.hotelbl.Hotel;
import group2.grade15.njuse.bl.hotelbl.HotelBL;
import group2.grade15.njuse.bl.promotionfactory.WebPromotionBL;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.*;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Guo on 2016/12/6.
 */
public class TimeWebPromotion implements WebPromotionBL {

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
        Date createTime = orderVO.getCreateTime();

        Date startTime = webPromotionVO.getStart();
        Date endTime = webPromotionVO.getEnd();

        return createTime.after(startTime) && createTime.before(endTime);
    }
}
