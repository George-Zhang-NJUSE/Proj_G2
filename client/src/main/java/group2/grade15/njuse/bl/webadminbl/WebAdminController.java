package group2.grade15.njuse.bl.webadminbl;

import group2.grade15.njuse.blservice.WebAdminServ;
import group2.grade15.njuse.po.WebAdminPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;

/**
 * 负责执行网站管理人员的业务逻辑，用代理模式实现，持有酒店管理和用户管理两个对象
 * 只有获取网站管理人员信息的方法由自己的业务逻辑进行处理
 * 对酒店的管理采用代理的方式交给酒店管理进行真正的业务逻辑处理
 * 对用户的管理采用代理的方式交给用户管理进行真正的业务逻辑处理
 */

public class WebAdminController implements WebAdminServ, HotelManageBL, UserManageBL {

    private HotelManageBL hotelManage;
    private UserManageBL userManage;

    public WebAdminController() {
        hotelManage = new HotelManageImpl();
        userManage = new UserManageImpl();
    }

    public WebAdminVO getInfo(String webAdminId) {

        WebAdminPO po = null;
        try {
            po = RemoteHelper.getInstance().getWebAdminDataService().getWebAdmin(webAdminId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (po != null) {
            return new WebAdminVO(po);
        } else {
            return null;
        }
    }

    @Override
    public ResultMessage createHotel(HotelVO hotel) {
        return hotelManage.createHotel(hotel);
    }

    @Override
    public HotelListVO getHotelList() {
        return hotelManage.getHotelList();
    }

    @Override
    public HotelListVO modifyHotel(HotelVO hotel) {
        return hotelManage.modifyHotel(hotel);
    }

    @Override
    public ResultMessage deleteHotel(HotelVO hotel) {
        return hotelManage.deleteHotel(hotel);
    }

    @Override
    public CustomerListVO getCustomerList() {
        return userManage.getCustomerList();
    }

    @Override
    public HotelManagerListVO getHotelManagerList() {
        return userManage.getHotelManagerList();
    }

    @Override
    public WebMarketerListVO getWebMarketerList() {
        return userManage.getWebMarketerList();
    }

    @Override
    public ResultMessage createHotelManager(HotelManagerVO hotelManager) {
        return userManage.createHotelManager(hotelManager);
    }

    @Override
    public ResultMessage modifyWebMarketer(WebMarketerVO webMarketer) {
        return userManage.modifyWebMarketer(webMarketer);
    }

    @Override
    public ResultMessage createWebMarketer(WebMarketerVO webMarketer) {
        return userManage.createWebMarketer(webMarketer);
    }

    @Override
    public ResultMessage deleteWebMarketer(WebMarketerVO webMarketer) {
        return deleteWebMarketer(webMarketer);
    }
}
