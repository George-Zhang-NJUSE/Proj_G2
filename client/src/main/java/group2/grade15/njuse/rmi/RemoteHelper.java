package group2.grade15.njuse.rmi;

import group2.grade15.njuse.dataservice.*;
import group2.grade15.njuse.dataservice.areadataservice.AreaDataService;
import group2.grade15.njuse.dataservice.commentdataservice.CommentDataService;
import group2.grade15.njuse.dataservice.creditdataservice.CreditDataService;
import group2.grade15.njuse.dataservice.cusotmerdataservice.CustomerDataService;
import group2.grade15.njuse.dataservice.hoteldataservice.HotelDataService;
import group2.grade15.njuse.dataservice.hotelmanagerdataservice.HotelManagerDataService;
import group2.grade15.njuse.dataservice.webadmindataservice.*;
import group2.grade15.njuse.dataservice.webmarketerdataservice.WebMarketerDataService;

import java.rmi.Remote;

/**
 * Created by Guo on 2016/11/26.
 */
public class RemoteHelper {
    private Remote remote;
    private static RemoteHelper remoteHelper = new RemoteHelper();

    public static RemoteHelper getInstance(){
        return remoteHelper;
    }

    private RemoteHelper() {
    }

    public void setRemote(Remote remote){
        this.remote = remote;
    }

    public CustomerDataService getCustomerDataService(){
        return (CustomerDataService) remote;
    }

    public HotelManagerDataService getHotelManagerDataService(){
        return null;
    }

    public WebAdminDataService getWebAdminDataService(){
        return null;
    }

    public WebMarketerDataService getWebMarketerDataService(){
        return null;
    }

    public AreaDataService getAreaDataService(){
        return null;
    }

    public CommentDataService getCommentDataService(){
        return null;
    }

    public CreditDataService getCreditDataService(){
        return null;
    }

    public HotelDataService getHotelDataService(){
        return null;
    }

    public HotelPromotionDataService getHotelPromotionDataService(){
        return null;
    }

    public OrderDataService getOrderDataService(){
        return null;
    }

    public WebPromotionDataService getWebPromotionDataService(){
        return null;
    }
}
