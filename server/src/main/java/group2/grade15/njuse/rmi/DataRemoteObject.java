package group2.grade15.njuse.rmi;

import group2.grade15.njuse.data.datafactory.DatabaseFactory;
import group2.grade15.njuse.dataservice.*;
import group2.grade15.njuse.dataservice.cusotmerdataservice.CustomerDataService;
import group2.grade15.njuse.dataservice.datafactory.DataFactory;
import group2.grade15.njuse.dataservice.hotelmanagerdataservice.HotelManagerDataService;
import group2.grade15.njuse.dataservice.webadmindataservice.*;
import group2.grade15.njuse.dataservice.webmarketerdataservice.WebMarketerDataService;
import group2.grade15.njuse.po.*;
import group2.grade15.njuse.utility.IDType;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

/**
 * Created by Guo on 2016/11/26.
 */
public class DataRemoteObject extends UnicastRemoteObject implements CustomerDataService, HotelManagerDataService, CustomerPartService, HotelManagerPartService, HotelPartService, WebAdminDataService, WebMarketerPartService, WebMarketerDataService, AreaDataSevice, CommentDataService, CreditDataService, HotelDataService, HotelPromotionDataService, OrderDataService, WebPromotionDataService {

    private DataFactory dataFactory;
    private CustomerDataService customerDataService;
    private HotelManagerDataService hotelManagerDataService;
    private CustomerPartService customerPartService;
    private HotelManagerPartService hotelManagerPartService;
    private HotelPartService hotelPartService;
    private WebAdminDataService webAdminDataService;
    private WebMarketerPartService webMarketerPartService;
    private WebMarketerDataService webMarketerDataService;
    private AreaDataSevice areaDataSevice;
    private CommentDataService commentDataService;
    private CreditDataService creditDataService;
    private HotelDataService hotelDataService;
    private HotelPromotionDataService hotelPromotionDataService;
    private OrderDataService orderDataService;
    private WebPromotionDataService webPromotionDataService;

    protected DataRemoteObject() throws RemoteException {
        dataFactory = DatabaseFactory.getInstance();
        customerDataService = dataFactory.getCustomerDataService();
        hotelManagerDataService = dataFactory.getHotelManagerDataService();
        webMarketerDataService = dataFactory.getWebMarketerDataService();
        webAdminDataService = dataFactory.getWebAdminDataService();
    }

    @Override
    public CustomerPO getCustomer(int customerId) throws RemoteException {
        return customerDataService.getCustomer(customerId);
    }

    @Override
    public CustomerPO add(CustomerPO po) throws RemoteException {
        return customerDataService.add(po);
    }

    @Override
    public ResultMessage modify(CustomerPO po) throws RemoteException {
        return customerDataService.modify(po);
    }

    @Override
    public HotelPO getHotel(int hotelId) throws RemoteException {
        return null;
    }

    @Override
    public OrderPO getOrder(int orderId) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage add(OrderPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modify(OrderPO po) throws RemoteException {
        return null;
    }

    @Override
    public OrderListPO getList(int id, IDType type) throws RemoteException {
        return null;
    }

    @Override
    public OrderListPO getList(Date date) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage add(HotelPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modify(HotelPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modifyRoom(int hotelId, RoomPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage remove(int hotelId) throws RemoteException {
        return null;
    }

    @Override
    public CreditListPO getHistory(int customerId) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage add(CreditPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage add(CommentPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modify(CommentPO po) throws RemoteException {
        return null;
    }

    @Override
    public CommentListPO getHotelComments(int hotelID) throws RemoteException {
        return null;
    }

    @Override
    public CommentListPO getCustomerComments(int customerID) throws RemoteException {
        return null;
    }

    @Override
    public WebAdminPO getWebAdmin(String webAdminId) throws RemoteException {
        return null;
    }

    @Override
    public WebPromotionListPO getList() throws RemoteException {
        return null;
    }

    @Override
    public HotelPromotionListPO getList(int hotelId) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modify(PromotionPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage remove(PromotionPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage add(PromotionPO po) throws RemoteException {
        return null;
    }

    @Override
    public HotelManagerPO getHotelManager(int hotelManagerId) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modify(HotelManagerPO po) throws RemoteException {
        return null;
    }

    @Override
    public WebMarketerPO getWebMarketer(String webMarketerId) throws RemoteException {
        return null;
    }

    @Override
    public HotelListPO getAreaList(CbdPO conditionInfo) throws RemoteException {
        return null;
    }
}
