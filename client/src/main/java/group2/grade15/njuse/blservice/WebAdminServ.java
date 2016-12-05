package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

public interface WebAdminServ {

    /**
     * 根据ID获取网站管理人员的VO
     * VO中包含网站管理人员的账户信息
     */
    public WebAdminVO getInfo(String webAdminId);

    /**
     * 创建酒店
     * 传入的参数是界面层填写好信息的酒店VO
     */
    public ResultMessage createHotel(HotelVO hotel);

    /**
     * 获取所有酒店列表
     */
    public HotelListVO getHotelList();

    /**
     * 修改酒店信息
     * 传入的参数是包含所有修改后的酒店信息的酒店VO
     */
    public HotelListVO modifyHotel(HotelVO hotel);

    /**
     * 删除酒店
     * 传入的参数是需要删除的酒店VO
     */
    public ResultMessage deleteHotel(HotelVO hotel);

    /**
     * 获取所有顾客列表
     */
    public CustomerListVO getCustomerList();

    /**
     * 获取所有酒店工作人员列表
     */
    public HotelManagerListVO getHotelManagerList();

    /**
     * 获取所有网站营销人员列表
     */
    public WebMarketerListVO getWebMarketerList();

    /**
     * 创建酒店工作人员
     * 传入的参数是酒店管理人员的VO，由界面层填写后传入逻辑层
     */
    public ResultMessage createHotelManager(HotelManagerVO hotelManager);

    /**
     * 修改网站营销人员
     * 传入的参数包含修改后信息的网站营销人员的VO
     */
    public ResultMessage modifyWebMarketer(WebMarketerVO webMarketer);

    /**
     * 创建网站营销人员
     * 传入的参数是酒店营销人员的VO，由界面层填写后传入逻辑层
     */
    public ResultMessage createWebMarketer(WebMarketerVO webMarketer);

    /**
     * 删除网站营销人员
     * 传入的参数是需要删除的网站营销人员VO
     */
    public ResultMessage deleteWebMarketer(WebMarketerVO webMarketer);
}
