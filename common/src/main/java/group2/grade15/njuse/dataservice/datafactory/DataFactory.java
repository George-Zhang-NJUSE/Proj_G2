package group2.grade15.njuse.dataservice.datafactory;

import group2.grade15.njuse.dataservice.commentdataservice.CommentDataService;
import group2.grade15.njuse.dataservice.commentdataservice.CustomerComment;
import group2.grade15.njuse.dataservice.commentdataservice.HotelComment;
import group2.grade15.njuse.dataservice.creditdataservice.CreditDataService;
import group2.grade15.njuse.dataservice.cusotmerdataservice.CustomerDataService;
import group2.grade15.njuse.dataservice.hotelmanagerdataservice.HotelManagerDataService;
import group2.grade15.njuse.dataservice.webadmindataservice.CustomerPartService;
import group2.grade15.njuse.dataservice.webadmindataservice.WebAdminDataService;
import group2.grade15.njuse.dataservice.webmarketerdataservice.WebMarketerDataService;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by dell on 2016/11/22.
 */
public interface DataFactory extends Remote{
    public CustomerDataService getCustomerDataService() throws RemoteException;

    public HotelManagerDataService getHotelManagerDataService() throws RemoteException;

    public WebMarketerDataService getWebMarketerDataService() throws RemoteException;

    public WebAdminDataService getWebAdminDataService() throws RemoteException;

    public CommentDataService getCommentDataService() throws RemoteException;

    public CreditDataService getCreditDataService() throws RemoteException;

}
