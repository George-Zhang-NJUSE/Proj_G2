package group2.grade15.njuse.bl.hotelpromotionbl;

import group2.grade15.njuse.bl.promotion.Promotion;
import group2.grade15.njuse.blservice.HotelPromotionServ;
import group2.grade15.njuse.po.PromotionPO;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelPromotionListVO;
import group2.grade15.njuse.vo.HotelPromotionVO;
import group2.grade15.njuse.vo.PromotionVO;

/**
 * Created by Guo on 2016/11/13.
 */
public class HotelPromotion extends Promotion implements HotelPromotionBL, HotelPromotionServ {

    public HotelPromotion(PromotionPO po) {
        super(po);
    }

    @Override
    public HotelPromotionVO createHotelPromotion(PromotionVO promotionInfo) {
        return null;
    }

    @Override
    public HotelPromotionListVO getHotelPromotionList(String hotelId) {
        return null;
    }

    @Override
    public ResultMessage modifyHotelPromotion(PromotionVO promotion) {
        return null;
    }

    @Override
    public ResultMessage changeState(PromotionVO PromotionVO) {
        return null;
    }

    @Override
    public HotelPromotionListVO getHotelPromotionList() {
        return null;
    }
}
