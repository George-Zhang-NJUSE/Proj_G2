package group2.grade15.njuse.blservice;

import group2.grade15.njuse.bl.promotion.Promotion;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelPromotionListVO;
import group2.grade15.njuse.vo.WebPromotionListVO;

/**
 * Created by Guo on 2016/11/29.
 */
public interface HotelPromotionServ {
    public Promotion createHotelPromotion (Promotion promotionInfo);

    public HotelPromotionListVO getHotelPromotionList (String hotelId);

    public ResultMessage modifyHotelPromotion (Promotion promotion);

    public ResultMessage changeState(Promotion PromotionVO);
}
