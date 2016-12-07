package group2.grade15.njuse.bl.hotelpromotionbl;

import group2.grade15.njuse.bl.promotionfactory.HotelPromotionBL;
import group2.grade15.njuse.vo.HotelPromotionVO;

/**
 * Created by Guo on 2016/12/6.
 */
public class MultipleHotelPromotion implements HotelPromotionBL {

    @Override
    public double countPrice(double originalPrice, HotelPromotionVO hotelPromotionVO) {
        return originalPrice * hotelPromotionVO.getDiscount();
    }
}
