package group2.grade15.njuse.rmi;

import group2.grade15.njuse.dataservice.WebPromotionDataService;
import group2.grade15.njuse.dataservice.areadataservice.AreaDataService;
import group2.grade15.njuse.dataservice.commentdataservice.CommentDataService;
import group2.grade15.njuse.dataservice.creditdataservice.CreditDataService;
import group2.grade15.njuse.dataservice.cusotmerdataservice.CustomerDataService;
import group2.grade15.njuse.dataservice.hoteldataservice.HotelDataService;
import group2.grade15.njuse.dataservice.hotelmanagerdataservice.HotelManagerDataService;
import group2.grade15.njuse.dataservice.hotelpromotiondataservice.HotelPromotionDataService;
import group2.grade15.njuse.dataservice.orderdataservice.OrderDataService;
import group2.grade15.njuse.dataservice.webadmindataservice.WebAdminDataService;
import group2.grade15.njuse.dataservice.webmarketerdataservice.WebMarketerDataService;

import java.rmi.Remote;

/**
 * Created by Guo on 2016/11/26.
 */
public class RemoteHelper {
    private Remote remote;
    private static RemoteHelper remoteHelper = new RemoteHelper();

    public static RemoteHelper getInstance() {
        return remoteHelper;
    }

    private RemoteHelper() {
    }

    public void setRemote(Remote remote) {
        this.remote = remote;
    }

    public CustomerDataService getCustomerDataService() {
        return (CustomerDataService) remote;
    }

    public HotelManagerDataService getHotelManagerDataService() {
        return (HotelManagerDataService) remote;
    }

    public WebAdminDataService getWebAdminDataService() {
        return (WebAdminDataService) remote;
    }

    public WebMarketerDataService getWebMarketerDataService() {
        return (WebMarketerDataService) remote;
    }

    public AreaDataService getAreaDataService() {
        return (AreaDataService) remote;
    }

    public CommentDataService getCommentDataService() {
        return (CommentDataService) remote;
    }

    public CreditDataService getCreditDataService() {
        return (CreditDataService) remote;
    }

    public HotelDataService getHotelDataService() {
        return (HotelDataService) remote;
    }

    public HotelPromotionDataService getHotelPromotionDataService() {
        return (HotelPromotionDataService) remote;
    }

    public OrderDataService getOrderDataService() {
        return (OrderDataService) remote;
    }

    public WebPromotionDataService getWebPromotionDataService() {
        return (WebPromotionDataService) remote;
    }
}
