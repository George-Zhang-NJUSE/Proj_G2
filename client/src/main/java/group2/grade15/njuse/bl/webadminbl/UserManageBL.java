package group2.grade15.njuse.bl.webadminbl;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

/**
 * Created by 果宝 on 2016/11/27.
 */
public interface UserManageBL {
    public CustomerListVO getCustomerList();

    public HotelManagerListVO getHotelManagerList();

    public WebMarketerListVO getWebMarketerList();

    public ResultMessage createHotelManager(HotelManagerVO hotelManager);

    public ResultMessage modifyWebMarketer(WebMarketerVO webMarketer);

    public ResultMessage createWebMarketer(WebMarketerVO webMarketer);

    public ResultMessage deleteWebMarketer(WebMarketerVO webMarketer);
}
