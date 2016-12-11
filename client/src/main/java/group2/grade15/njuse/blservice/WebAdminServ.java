package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

/**
 * 网站管理人员的层间接口
 * 供界面层的网站管理人员界面调用
 * 职责是处理网站管理人员相关的逻辑功能
 * @author Guo
 */
public interface WebAdminServ {
    /**
     * 根据ID获取网站管理人员的VO
     * VO中包含网站管理人员的账户信息
     * @param webAdminId int型，界面层传递的酒店管理人员
     * @return 成功含有酒店管理人员信息的WebAdminVO
     *         失败返回null
     */
    public WebAdminVO getInfo(String webAdminId);

    /**
     * 创建酒店
     * @param hotel HotelVO型，传入的参数是界面层填写好信息的酒店VO
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage createHotel(HotelVO hotel);

    /**
     * 获取所有酒店列表
     * @return 成功返回HotelListVO
     *         失败返回null
     */
    public HotelListVO getHotelList();

    /**
     * 修改酒店信息
     * @param hotel HotelVO型，传入的参数是包含所有修改后的酒店信息的酒店VO
     * @return 成功返回HotelListVO
     *         失败返回null
     */
    public HotelListVO modifyHotel(HotelVO hotel);

    /**
     * 删除酒店
     * @param hotel HotelVO型，传入的参数是需要删除的酒店VO
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage deleteHotel(HotelVO hotel);

    /**
     * 获取所有顾客列表
     * @return 成功返回CustomerListVO
     *         失败返回null
     */
    public CustomerListVO getCustomerList();

    /**
     * 获取所有酒店工作人员列表
     * @return 成功返回HotelManagerListVO
     *         失败返回null
     */
    public HotelManagerListVO getHotelManagerList();

    /**
     * 获取所有网站营销人员列表
     * @return 成功返回WebMarketerListVO
     *         失败返回null
     */
    public WebMarketerListVO getWebMarketerList();

    /**
     * 创建酒店工作人员
     * @param hotelManager HotelMangerVO型，传入的参数是酒店管理人员的VO，由界面层填写后传入逻辑层
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage createHotelManager(HotelManagerVO hotelManager);

    /**
     * 修改网站营销人员的个人信息
     * @param webMarketer WebMarketerVO型，传入的参数包含修改后信息的网站营销人员的VO
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage modifyWebMarketer(WebMarketerVO webMarketer);

    /**
     * 创建网站营销人员
     * @param webMarketer WebMarketerVO型，传入的参数是酒店营销人员的VO，由界面层填写后传入逻辑层
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage createWebMarketer(WebMarketerVO webMarketer);

    /**
     * 删除网站营销人员
     * @param webMarketer WebMarketerVO型，传入的参数是需要删除的网站营销人员VO
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage deleteWebMarketer(WebMarketerVO webMarketer);
}
