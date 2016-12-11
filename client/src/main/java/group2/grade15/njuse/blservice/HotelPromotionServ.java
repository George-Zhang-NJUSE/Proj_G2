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
    /**
     * 创建新的酒店优惠策略
     * @param  hotelPromotionInfo HotelPromotionVO型，界面层传递来的酒店优惠策略信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage createHotelPromotion(HotelPromotionVO hotelPromotionInfo);

    /**
     * 获取单个酒店优惠策略
     * @param  hotelID int型，界面层传递来的酒店ID
     * @param  hotelPromotionID int型，界面层传递来的酒店优惠策略ID
     * @return 成功返回对应的HotelPromotionVO
     *         失败返回null
     */
    public HotelPromotionVO getHotelPromotion(int hotelID, int hotelPromotionID);

    /**
     * 获取酒店的优惠策略列表
     * @param  hotelId int型，界面层传递来的酒店ID
     * @return 成功返回对应的HotelPromotionListVO
     *         失败返回null
     */
    public HotelPromotionListVO getHotelPromotionList(int hotelId);

    /**
     * 修改酒店优惠策略的信息
     * @param  promotion HotelPromotionVO型，界面层传递来修改后的酒店优惠策略信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage modifyHotelPromotion(HotelPromotionVO promotion);

    /**
     * 修改酒店优惠策略的状态
     * @param  promotionVO HotelPromotionVO型，界面层传递来含有状态修改后的酒店优惠策略信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage changeState(HotelPromotionVO promotionVO);
}
