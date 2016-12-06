package group2.grade15.njuse.bl.promotionfactory;

import group2.grade15.njuse.utility.HotelPromotionType;
import group2.grade15.njuse.vo.HotelPromotionVO;

/**
 * Created by Guo on 2016/11/30.
 */
public class HotelPromotionFactory {

    static HotelPromotionFactory hotelPromotionFactory = null;

    private HotelPromotionFactory() {

    }

    public static HotelPromotionFactory getInstance() {
        if (hotelPromotionFactory == null) {
            hotelPromotionFactory = new HotelPromotionFactory();
        }
        return hotelPromotionFactory;
    }

    public HotelPromotionVO createHotelPromotion(HotelPromotionType type) {
        return null;
    }

}
