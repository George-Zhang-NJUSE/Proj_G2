package group2.grade15.njuse.bl.hotelpromotionbl;

import group2.grade15.njuse.bl.promotion.Promotion;
import group2.grade15.njuse.blservice.HotelPromotionServ;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelPromotionListVO;

/**
 * Created by Guo on 2016/11/13.
 */
public class HotelPromotion implements HotelPromotionServ {
    @Override
    public Promotion createHotelPromotion(Promotion promotionInfo) {
        return null;
    }

    @Override
    public HotelPromotionListVO getHotelPromotionList(String hotelId) {
        return null;
    }

    @Override
    public ResultMessage modifyHotelPromotion(Promotion promotion) {
        return null;
    }

    @Override
    public ResultMessage changeState(Promotion PromotionVO) {
        return null;
    }
}
