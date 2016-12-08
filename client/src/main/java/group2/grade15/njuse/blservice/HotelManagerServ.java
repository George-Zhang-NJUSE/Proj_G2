package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

/**
 * 酒店经理业务逻辑的层间接口
 * 供界面层的酒店经理界面调用
 * 职责是处理酒店经理相关的逻辑功能
 * @author Guo
 */
public interface HotelManagerServ {
    /**
     * 修改酒店经理的信息
     * @param hotelManager HotelManagerVO型，界面层传递来的存有修改信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage modifyInfo(HotelManagerVO hotelManager);

    /**
     * 修改酒店的信息
     * @param hotel HotelVO型，界面层传递来的存有修改信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage modifyHotelInfo(HotelVO hotel);

    public HotelVO getHotelInfo(int hotelID);

    /**
     * 修改房间的信息
     * @param hotelID int型，界面层传递来的要修改的酒店ID
     * @param roomInfo RoomVO型，界面层传递来的存有修改信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage modifyRoomInfo(int hotelID, RoomVO roomInfo);

    /**
     * 创建新的酒店优惠策略
     * @param promotionInfo HotelPromotionVO型，界面层传递来的存有酒店优惠策略信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage createHotelPromotion(HotelPromotionVO promotionInfo);

    /**
     * 获取指定酒店对应所有的优惠策略
     * @param hotelID int型，界面层传递来的酒店ID
     * @return 成功返回含有该酒店的所有优惠策略的HotelPromotionListVO
     *         失败返回null
     */
    public HotelPromotionListVO getHotelPromotionList(int hotelID);

    /**
     * 修改酒店优惠策略
     * @param hotelPromotion HotelPromotionVO型，界面层传递来的含有修改后酒店策略信息的数据对象
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage modifyHotelPromotion(HotelPromotionVO hotelPromotion);

    public ResultMessage activateHotelPromotion(HotelPromotionVO promotionVO);

    public ResultMessage stopHotelPromotion(HotelPromotionVO promotionVO);

    public ResultMessage deleteHotelPromotion(HotelPromotionVO promotionVO);

    public ResultMessage modifyState(int orderID, OrderState s);

    public ResultMessage addCompany(int customerID);

    public ResultMessage deleteCompany(int customerID);

    public HotelManagerVO getInfo(int hotelManagerId);
}
