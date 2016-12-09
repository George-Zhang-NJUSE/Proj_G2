package group2.grade15.njuse.bl.hotelpromotionbl;

import group2.grade15.njuse.bl.orderbl.Order;
import group2.grade15.njuse.bl.orderbl.OrderBL;
import group2.grade15.njuse.bl.promotionfactory.HotelPromotionBL;
import group2.grade15.njuse.vo.HotelPromotionVO;
import group2.grade15.njuse.vo.OrderVO;


/**
 * Created by Guo on 2016/12/6.
 */
public class MultipleHotelPromotion implements HotelPromotionBL {
    public OrderBL order;

    public MultipleHotelPromotion(){
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
        int needRoomNum = orderVO.getRoomSum();
        return needRoomNum >= 3;
    }
}
