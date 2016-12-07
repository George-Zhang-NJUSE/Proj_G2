package group2.grade15.njuse.bl.hotelpromotionbl;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelPromotionListVO;
import group2.grade15.njuse.vo.HotelPromotionVO;


/**
 * Created by Guo on 2016/11/29.
 */
public interface HotelPromotionControllerBL {
    public ResultMessage createHotelPromotion(HotelPromotionVO promotionInfo);

    public HotelPromotionListVO getHotelPromotionList(int hotelID);

    public ResultMessage modifyHotelPromotion(HotelPromotionVO promotion);

    public ResultMessage changeState(HotelPromotionVO promotionVO);
}

