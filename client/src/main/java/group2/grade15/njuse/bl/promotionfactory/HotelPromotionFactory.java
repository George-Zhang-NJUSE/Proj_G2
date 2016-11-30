package group2.grade15.njuse.bl.promotionfactory;

import group2.grade15.njuse.bl.hotelpromotionbl.HotelPromotion;
import group2.grade15.njuse.bl.promotion.Promotion;
import group2.grade15.njuse.vo.HotelPromotionVO;
import group2.grade15.njuse.vo.PromotionVO;

/**
 * Created by Guo on 2016/11/30.
 */
public class HotelPromotionFactory {

    static HotelPromotionFactory hotelPromotionFactory = null;
    private HotelPromotion hotelPromotionImpl;

    private HotelPromotionFactory(){
        hotelPromotionImpl = new HotelPromotion(null);
    }

    public static HotelPromotionFactory getInstance(){
        if(hotelPromotionFactory == null){
            hotelPromotionFactory = new HotelPromotionFactory();
        }
        return hotelPromotionFactory;
    }

    public HotelPromotionVO createHotelPromotion(PromotionVO vo) {
        return hotelPromotionImpl.createHotelPromotion(vo);
    }

}
