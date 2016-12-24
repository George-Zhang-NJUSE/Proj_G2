package group2.grade15.njuse.data.datafactory;

import group2.grade15.njuse.data.commentdata.CommentDatabaseImpl;
import group2.grade15.njuse.data.creditdata.CreditDatabaseImpl;
import group2.grade15.njuse.data.customerdata.CustomerDataBaseImpl;
import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.hoteldata.HotelDatabaseImpl;
import group2.grade15.njuse.data.hotelmanagerdata.HotelManagerDatabaseImpl;
import group2.grade15.njuse.data.hotelpromotiondata.HotelPromotionDatabaseImpl;
import group2.grade15.njuse.data.orderdata.OrderDatabaseImpl;
import group2.grade15.njuse.data.searchdata.AreaDatabaseImpl;
import group2.grade15.njuse.data.webadmindata.WebAdminDatabaseImpl;
import group2.grade15.njuse.data.webmarketerdata.WebMarketerDatabaseImpl;
import group2.grade15.njuse.data.webpromotiondata.WebPromotionDatabaseImpl;
import group2.grade15.njuse.dataservice.commentdataservice.CommentDataService;
import group2.grade15.njuse.dataservice.webpromotiondataservice.WebPromotionDataService;
import group2.grade15.njuse.dataservice.areadataservice.AreaDataService;
import group2.grade15.njuse.dataservice.creditdataservice.CreditDataService;
import group2.grade15.njuse.dataservice.cusotmerdataservice.CustomerDataService;
import group2.grade15.njuse.dataservice.datafactory.DataFactory;
import group2.grade15.njuse.dataservice.hoteldataservice.HotelDataService;
import group2.grade15.njuse.dataservice.hotelpromotiondataservice.HotelPromotionDataService;
import group2.grade15.njuse.dataservice.orderdataservice.OrderDataService;

import java.rmi.RemoteException;

/**
 * Created by dell on 2016/11/20.
 */
public class DatabaseFactory implements DataFactory {
    static DatabaseFactory dbFactory = null;
    DatabaseInfo info = new DatabaseInfo("jdbc:postgresql://localhost:5432/FirstDatabase", "postgres", "1997wyh");

    private CustomerDataBaseImpl customerDatabase = null;
    private HotelManagerDatabaseImpl hotelManagerDatabase = null;
    private WebMarketerDatabaseImpl webMarketerDatabase = null;
    private WebAdminDatabaseImpl webAdminDatabase = null;
    private CommentDatabaseImpl commentDatabase = null;
    private CreditDatabaseImpl creditDatabase = null;
    private AreaDatabaseImpl areaDatabase = null;
    private HotelDatabaseImpl hotelDataService = null;
    private OrderDatabaseImpl orderDataService = null;
    private HotelPromotionDatabaseImpl hotelPromotionDataService = null;
    private WebPromotionDatabaseImpl webPromotionDataService = null;


    private DatabaseFactory() {
    }

    public static DatabaseFactory getInstance() {
        if (dbFactory == null) {
            dbFactory = new DatabaseFactory();
        }
        return dbFactory;
    }


    public CustomerDataService getCustomerDataService() throws RemoteException {
        if (customerDatabase == null) {
            try {
                customerDatabase = new CustomerDataBaseImpl(info);
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return customerDatabase;
    }

    public HotelManagerDatabaseImpl getHotelManagerDataService() throws RemoteException {
        if (hotelManagerDatabase == null) {
            try {
                hotelManagerDatabase = new HotelManagerDatabaseImpl(info);
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return hotelManagerDatabase;
    }

    public WebMarketerDatabaseImpl getWebMarketerDataService() throws RemoteException {
        if (webMarketerDatabase == null) {
            try {
                webMarketerDatabase = new WebMarketerDatabaseImpl(info);
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return webMarketerDatabase;
    }

    @Override
    public WebAdminDatabaseImpl getWebAdminDataService() throws RemoteException {
        if (webAdminDatabase == null) {
            try {
                webAdminDatabase = new WebAdminDatabaseImpl(info);
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return webAdminDatabase;
    }

    @Override
    public CommentDataService getCommentDataService() throws RemoteException {
        if (commentDatabase == null) {
            try {
                commentDatabase = new CommentDatabaseImpl(info);
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return commentDatabase;
    }

    @Override
    public CreditDataService getCreditDataService() throws RemoteException {
        if (creditDatabase == null) {
            try {
                creditDatabase = new CreditDatabaseImpl(info);
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return creditDatabase;
    }

    @Override
    public AreaDataService getAreaDataService() throws RemoteException {
        if (areaDatabase == null) {
            try {
                areaDatabase = new AreaDatabaseImpl(info);
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return areaDatabase;
    }

    @Override
    public HotelDataService getHotelDataService() throws RemoteException {
        if (hotelDataService == null) {
            try {
                hotelDataService = new HotelDatabaseImpl(info);
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return hotelDataService;
    }

    @Override
    public OrderDataService getOrderDataService() throws RemoteException {
        if (orderDataService == null) {
            try {
                orderDataService = new OrderDatabaseImpl(info);
            } catch (RemoteException e) {
                e.printStackTrace();
                ;
                return null;
            }
        }
        return orderDataService;
    }

    @Override
    public HotelPromotionDataService getHotelPromotionDataService() throws RemoteException {
        if (hotelPromotionDataService == null) {
            try {
                hotelPromotionDataService = new HotelPromotionDatabaseImpl(info);
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return hotelPromotionDataService;
    }

    @Override
    public WebPromotionDataService getWebPromotionDataService() throws RemoteException {
        if (webPromotionDataService == null) {
            try {
                webPromotionDataService = new WebPromotionDatabaseImpl(info);
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return webPromotionDataService;
    }


}
