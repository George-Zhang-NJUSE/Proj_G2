package group2.grade15.njuse.bl.hotelpromotionbl;

import group2.grade15.njuse.bl.orderbl.Order;
import group2.grade15.njuse.bl.orderbl.OrderBL;
import group2.grade15.njuse.bl.promotionfactory.HotelPromotionBL;
import group2.grade15.njuse.vo.HotelPromotionVO;
import group2.grade15.njuse.vo.OrderVO;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Guo on 2016/12/6.
 */
public class TimeHotelPromotion implements HotelPromotionBL{
    public OrderBL order;

    public TimeHotelPromotion(){
        order = new Order();
    }

    @Override
    public double countPrice(OrderVO orderVO, HotelPromotionVO hotelPromotionVO) {

        double totalPrice = order.getOriginalPrice(orderVO);

        if(isFit(orderVO, hotelPromotionVO)) {
            return totalPrice * hotelPromotionVO.getDiscount();
        } else {
            return totalPrice;
        }
    }

    private boolean isFit(OrderVO orderVO, HotelPromotionVO hotelPromotionVO){
        Timestamp checkInTime = orderVO.getCheckInTime();
        Timestamp checkOutTime = orderVO.getCheckOutTime();

        Date startTime = hotelPromotionVO.getStart();
        Date endTime = hotelPromotionVO.getEnd();

        return checkInTime.after(startTime) && checkOutTime.before(endTime);
    }
}
