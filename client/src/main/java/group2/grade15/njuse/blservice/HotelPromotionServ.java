package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelPromotionListVO;
import group2.grade15.njuse.vo.HotelPromotionVO;


/**
 * 酒店优惠策略的层间接口
 * 供界面层的酒店优惠策略界面调用
 * 职责是处理酒店优惠策略相关的逻辑功能
 * @author Guo
 */
public interface HotelPromotionServ {

    public ResultMessage createHotelPromotion(HotelPromotionVO HotelPromotionInfo);

    public HotelPromotionListVO getHotelPromotionList(int hotelId);

    public ResultMessage modifyHotelPromotion(HotelPromotionVO promotion);

    public ResultMessage changeState(HotelPromotionVO promotionVO);
}
