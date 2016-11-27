package group2.grade15.njuse.bl.webadminbl;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CustomerListVO;
import group2.grade15.njuse.vo.HotelManagerListVO;
import group2.grade15.njuse.vo.HotelManagerVO;
import group2.grade15.njuse.vo.WebMarketerVO;

/**
 * Created by 果宝 on 2016/11/27.
 */
public interface UserProxyBL {
    public CustomerListVO getCustomerList();

    public HotelManagerListVO getHotelManagerList();

    public WebMarketerVO getWebMarketerList();

    public ResultMessage createHotelManager(HotelManagerVO hotelManager);

    public ResultMessage modifyWebMarketer(WebMarketerVO webMarketer);

    public ResultMessage createWebMarketer(WebMarketerVO webMarketer);

    public ResultMessage deleteWebMarketer(WebMarketerVO webMarketer);
}
