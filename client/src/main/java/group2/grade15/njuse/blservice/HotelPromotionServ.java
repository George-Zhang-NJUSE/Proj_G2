package group2.grade15.njuse.blservice;

import group2.grade15.njuse.bl.hotelpromotionbl.HotelPromotion;
import group2.grade15.njuse.bl.promotion.Promotion;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelPromotionListVO;
import group2.grade15.njuse.vo.HotelPromotionVO;
import group2.grade15.njuse.vo.PromotionVO;
import group2.grade15.njuse.vo.WebPromotionListVO;

/**
 * Created by Guo on 2016/11/29.
 */
public interface HotelPromotionServ {
    public HotelPromotionVO createHotelPromotion (PromotionVO promotionInfo);

    public HotelPromotionListVO getHotelPromotionList (String hotelId);

    public ResultMessage modifyHotelPromotion (PromotionVO promotion);

    public ResultMessage changeState(PromotionVO PromotionVO);
}
