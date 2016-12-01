package group2.grade15.njuse.rmi;

import group2.grade15.njuse.data.datafactory.DatabaseFactory;
import group2.grade15.njuse.dataservice.*;
import group2.grade15.njuse.dataservice.areadataservice.AreaDataService;
import group2.grade15.njuse.dataservice.commentdataservice.CommentDataService;
import group2.grade15.njuse.dataservice.creditdataservice.CreditDataService;
import group2.grade15.njuse.dataservice.cusotmerdataservice.CustomerDataService;
import group2.grade15.njuse.dataservice.datafactory.DataFactory;
import group2.grade15.njuse.dataservice.hotelmanagerdataservice.HotelManagerDataService;
import group2.grade15.njuse.dataservice.webadmindataservice.*;
import group2.grade15.njuse.dataservice.webmarketerdataservice.WebMarketerDataService;
import group2.grade15.njuse.po.*;
import group2.grade15.njuse.utility.IDType;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Guo on 2016/11/26.
 */
public class DataRemoteObject extends UnicastRemoteObject implements CommentDataService,CustomerDataService, HotelManagerDataService,
        WebAdminDataService,WebMarketerDataService, AreaDataService, CreditDataService, HotelDataService, HotelPromotionDataService,
        OrderDataService, WebPromotionDataService, Remote {

    private DataFactory dataFactory;
    private CustomerDataService customerDataService;
    private HotelManagerDataService hotelManagerDataService;
    private WebAdminDataService webAdminDataService;
    private WebMarketerDataService webMarketerDataService;
    private AreaDataService areaDataService;
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
        commentDataService=dataFactory.getCommentDataService();
        creditDataService=dataFactory.getCreditDataService();
        areaDataService=dataFactory.getAreaDataService();
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
    public ArrayList<OrderPO> getList(int id, IDType type) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<OrderPO> getList(Date date) throws RemoteException {
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
    public ArrayList<CreditPO> getHistory(int customerId) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage add(CreditPO po) throws RemoteException {
        return null;
    }

    @Override
    public WebAdminPO getWebAdmin(String webAdminId) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<PromotionPO> getList() throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<PromotionPO> getList(int hotelId) throws RemoteException {
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
    public ResultMessage addWebMarketerInfo(WebMarketerPO webMarketerPO) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<WebMarketerPO> getWebMarketerInfo() throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modifyWebMarketerInfo(WebMarketerPO webMarketerPO) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage deleteWebMarketer(String webMarketerID) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<CustomerPO> getCustomerInfo() throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modifyCustomerInfo(CustomerPO customerPO) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<HotelManagerPO> getHotelManagerInfo() throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modifyHotelManagerInfo(HotelManagerPO hotelManagerPO) throws RemoteException {
        return null;
    }

    @Override
    public HotelManagerPO addHotelManagerInfo(HotelManagerPO hotelManagerPO) throws RemoteException {
        return null;
    }

    @Override
    public HotelPO addHotel(HotelPO hotelPO) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<HotelPO> getHotelInfo() throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modifyHotelInfo(HotelPO hotelPO) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage deleteHotelInfo(int hotelId) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<CommentPO> getCustomerComments(int customerID) throws RemoteException {
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
    public ArrayList<CommentPO> getHotelComments(int hotelID) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<ProvincePO> getProvince() {
        return null;
    }

    @Override
    public ArrayList<CityPO> getCity(String provinceNum) {
        return null;
    }

    @Override
    public ArrayList<DistrictPO> getDistrict(String cityNum) {
        return null;
    }

    @Override
    public ArrayList<CbdPO> getCbd(String districtNum) {
        return null;
    }

    @Override
    public ArrayList<HotelPO> getHotelByAddress(String address) {
        return null;
    }

    @Override
    public ArrayList<HotelPO> getHotelByName(String name) {
        return null;
    }

    /*@Override
    public ArrayList<HotelPO> getAreaList(CbdPO conditionInfo) throws RemoteException {
        return null;
    }*/
}
