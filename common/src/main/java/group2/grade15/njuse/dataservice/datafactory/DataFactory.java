package group2.grade15.njuse.dataservice.datafactory;

import group2.grade15.njuse.dataservice.commentdataservice.CommentDataService;
import group2.grade15.njuse.dataservice.webpromotiondataservice.WebPromotionDataService;
import group2.grade15.njuse.dataservice.areadataservice.AreaDataService;
import group2.grade15.njuse.dataservice.creditdataservice.CreditDataService;
import group2.grade15.njuse.dataservice.cusotmerdataservice.CustomerDataService;
import group2.grade15.njuse.dataservice.hoteldataservice.HotelDataService;
import group2.grade15.njuse.dataservice.hotelmanagerdataservice.HotelManagerDataService;
import group2.grade15.njuse.dataservice.hotelpromotiondataservice.HotelPromotionDataService;
import group2.grade15.njuse.dataservice.orderdataservice.OrderDataService;
import group2.grade15.njuse.dataservice.webadmindataservice.WebAdminDataService;
import group2.grade15.njuse.dataservice.webmarketerdataservice.WebMarketerDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by dell on 2016/11/22.
 */
public interface DataFactory extends Remote {
    public CustomerDataService getCustomerDataService() throws RemoteException;

    public HotelManagerDataService getHotelManagerDataService() throws RemoteException;

    public WebMarketerDataService getWebMarketerDataService() throws RemoteException;

    public WebAdminDataService getWebAdminDataService() throws RemoteException;

    public CommentDataService getCommentDataService() throws RemoteException;

    public CreditDataService getCreditDataService() throws RemoteException;

    public AreaDataService getAreaDataService() throws RemoteException;

    public HotelDataService getHotelDataService() throws RemoteException;

    public OrderDataService getOrderDataService() throws RemoteException;

    public HotelPromotionDataService getHotelPromotionDataService() throws RemoteException;

    public WebPromotionDataService getWebPromotionDataService() throws RemoteException;
}
