package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelPromotionListVO;
import group2.grade15.njuse.vo.HotelPromotionVO;

/**
 * Created by Guo on 2016/11/29.
 */
public interface HotelPromotionServ {
    public ResultMessage createHotelPromotion(HotelPromotionVO HotelPromotionInfo);

    public HotelPromotionListVO getHotelPromotionList(int hotelId);

    public ResultMessage modifyHotelPromotion(HotelPromotionVO promotion);

    public ResultMessage changeState(HotelPromotionVO promotionVO);
}
