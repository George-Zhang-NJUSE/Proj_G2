package group2.grade15.njuse.rmi;

import group2.grade15.njuse.data.datafactory.DatabaseFactory;
import group2.grade15.njuse.dataservice.webpromotiondataservice.WebPromotionDataService;
import group2.grade15.njuse.dataservice.areadataservice.AreaDataService;
import group2.grade15.njuse.dataservice.commentdataservice.CommentDataService;
import group2.grade15.njuse.dataservice.creditdataservice.CreditDataService;
import group2.grade15.njuse.dataservice.cusotmerdataservice.CustomerDataService;
import group2.grade15.njuse.dataservice.datafactory.DataFactory;
import group2.grade15.njuse.dataservice.hoteldataservice.HotelDataService;
import group2.grade15.njuse.dataservice.hotelmanagerdataservice.HotelManagerDataService;
import group2.grade15.njuse.dataservice.hotelpromotiondataservice.HotelPromotionDataService;
import group2.grade15.njuse.dataservice.orderdataservice.OrderDataService;
import group2.grade15.njuse.dataservice.webadmindataservice.WebAdminDataService;
import group2.grade15.njuse.dataservice.webmarketerdataservice.WebMarketerDataService;
import group2.grade15.njuse.po.*;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Guo on 2016/11/26.
 */
public class DataRemoteObject extends UnicastRemoteObject implements CommentDataService, CustomerDataService, HotelManagerDataService,
        WebAdminDataService, WebMarketerDataService, AreaDataService, CreditDataService, HotelDataService, HotelPromotionDataService,
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
        commentDataService = dataFactory.getCommentDataService();
        creditDataService = dataFactory.getCreditDataService();
        areaDataService = dataFactory.getAreaDataService();
        hotelDataService = dataFactory.getHotelDataService();
        orderDataService = dataFactory.getOrderDataService();
        hotelPromotionDataService = dataFactory.getHotelPromotionDataService();
        webPromotionDataService = dataFactory.getWebPromotionDataService();
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
        return hotelDataService.getHotel(hotelId);
    }

    @Override
    public ArrayList<OrderPO> getUnexecutedList() throws RemoteException {
        return orderDataService.getUnexecutedList();
    }

    @Override
    public ArrayList<OrderPO> getAbnormalList() throws RemoteException {
        return orderDataService.getAbnormalList();
    }

    @Override
    public OrderPO getOrder(int orderId) throws RemoteException {
        return orderDataService.getOrder(orderId);
    }

    @Override
    public ArrayList<OrderPO> getListByCustomer(int customerID) throws RemoteException {
        return orderDataService.getListByCustomer(customerID);
    }

    @Override
    public ArrayList<OrderPO> getListByHotel(int hotelID) throws RemoteException {
        return orderDataService.getListByHotel(hotelID);
    }

    @Override
    public int roomToBeAvailable(Timestamp checkIn, Timestamp checkOut, RoomType type, int hotelID) throws RemoteException {
        return 0;
    }

    @Override
    public ResultMessage addOrder(OrderPO po) throws RemoteException {
        return orderDataService.addOrder(po);
    }

    @Override
    public ResultMessage modifyOrder(int orderID, OrderState state) throws RemoteException {
        return orderDataService.modifyOrder(orderID, state);
    }

    @Override
    public ResultMessage updateTime(Timestamp checkIn, Timestamp checkOut, int orderID) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modify(HotelPO po) throws RemoteException {
        return hotelDataService.modify(po);
    }

    @Override
    public ResultMessage modifyRoom(int hotelId, RoomPO po) throws RemoteException {
        return hotelDataService.modifyRoom(hotelId, po);
    }

    @Override
    public ResultMessage addRoomType(int hotelID, RoomPO po) throws RemoteException {
        return hotelDataService.addRoomType(hotelID, po);
    }

    @Override
    public ResultMessage deleteRoomType(int hotelID, RoomType type) throws RemoteException {
        return hotelDataService.deleteRoomType(hotelID, type);
    }

    @Override
    public ResultMessage uploadPic(byte[][] picList, int hotelID) throws RemoteException {
        return hotelDataService.uploadPic(picList, hotelID);
    }

    @Override
    public ResultMessage deletePic(int picNum, int hotelID) throws RemoteException {
        return hotelDataService.deletePic(picNum, hotelID);
    }

    @Override
    public ArrayList<CreditPO> getHistory(int customerId) throws RemoteException {
        return creditDataService.getHistory(customerId);
    }

    @Override
    public ResultMessage add(CreditPO po) throws RemoteException {
        return creditDataService.add(po);
    }

    @Override
    public WebAdminPO getWebAdmin(String webAdminId) throws RemoteException {
        return webAdminDataService.getWebAdmin(webAdminId);
    }

    @Override
    public ArrayList<WebPromotionPO> getList() throws RemoteException {
        return webPromotionDataService.getList();
    }

    @Override
    public ResultMessage modify(WebPromotionPO po) throws RemoteException {
        return webPromotionDataService.modify(po);
    }

    @Override
    public ResultMessage add(WebPromotionPO po) throws RemoteException {
        return webPromotionDataService.add(po);
    }

    @Override
    public ArrayList<RankPO> getRank() throws RemoteException {
        return webPromotionDataService.getRank();
    }

    @Override
    public ResultMessage modifyRank(RankPO rankPO) throws RemoteException {
        return webPromotionDataService.modifyRank(rankPO);
    }

    @Override
    public ArrayList<HotelPromotionPO> getList(int hotelId) throws RemoteException {
        return hotelPromotionDataService.getList(hotelId);
    }

    @Override
    public ResultMessage modify(HotelPromotionPO po) throws RemoteException {
        return hotelPromotionDataService.modify(po);
    }

    @Override
    public ResultMessage remove(int promotionID) throws RemoteException {
        return hotelPromotionDataService.remove(promotionID);
    }

    @Override
    public ResultMessage add(HotelPromotionPO po) throws RemoteException {
        return hotelPromotionDataService.add(po);
    }


    @Override
    public HotelManagerPO getHotelManager(int hotelManagerId) throws RemoteException {
        return hotelManagerDataService.getHotelManager(hotelManagerId);
    }

    @Override
    public ResultMessage modify(HotelManagerPO po) throws RemoteException {
        return hotelManagerDataService.modify(po);
    }

    @Override
    public WebMarketerPO getWebMarketer(String webMarketerId) throws RemoteException {
        return webMarketerDataService.getWebMarketer(webMarketerId);
    }

    @Override
    public ResultMessage addWebMarketerInfo(WebMarketerPO webMarketerPO) throws RemoteException {
        return webAdminDataService.addWebMarketerInfo(webMarketerPO);
    }

    @Override
    public ArrayList<WebMarketerPO> getWebMarketerInfo() throws RemoteException {
        return webAdminDataService.getWebMarketerInfo();
    }

    @Override
    public ResultMessage modifyWebMarketerInfo(WebMarketerPO webMarketerPO) throws RemoteException {
        return webAdminDataService.modifyWebMarketerInfo(webMarketerPO);
    }

    @Override
    public ResultMessage deleteWebMarketer(String webMarketerID) throws RemoteException {
        return webAdminDataService.deleteWebMarketer(webMarketerID);
    }

    @Override
    public ArrayList<CustomerPO> getCustomerInfo() throws RemoteException {
        return webAdminDataService.getCustomerInfo();
    }

    @Override
    public ResultMessage modifyCustomerInfo(CustomerPO customerPO) throws RemoteException {
        return webAdminDataService.modifyCustomerInfo(customerPO);
    }

    @Override
    public ArrayList<HotelManagerPO> getHotelManagerInfo() throws RemoteException {
        return webAdminDataService.getHotelManagerInfo();
    }

    @Override
    public ResultMessage modifyHotelManagerInfo(HotelManagerPO hotelManagerPO) throws RemoteException {
        return webAdminDataService.modifyHotelManagerInfo(hotelManagerPO);
    }

    @Override
    public HotelManagerPO addHotelManagerInfo(HotelManagerPO hotelManagerPO) throws RemoteException {
        return webAdminDataService.addHotelManagerInfo(hotelManagerPO);
    }

    @Override
    public HotelPO addHotel(HotelPO hotelPO) throws RemoteException {
        return webAdminDataService.addHotel(hotelPO);
    }

    @Override
    public ArrayList<HotelPO> getHotelInfo() throws RemoteException {
        return webAdminDataService.getHotelInfo();
    }

    @Override
    public ResultMessage modifyHotelInfo(HotelPO hotelPO) throws RemoteException {
        return webAdminDataService.modifyHotelInfo(hotelPO);
    }

    @Override
    public ResultMessage deleteHotelInfo(int hotelId) throws RemoteException {
        return webAdminDataService.deleteHotelInfo(hotelId);
    }

    @Override
    public ArrayList<CommentPO> getCustomerComments(int customerID) throws RemoteException {
        return commentDataService.getCustomerComments(customerID);
    }

    @Override
    public ResultMessage add(CommentPO po) throws RemoteException {
        return commentDataService.add(po);
    }

    @Override
    public ResultMessage modify(CommentPO po) throws RemoteException {
        return commentDataService.modify(po);
    }

    @Override
    public ArrayList<CommentPO> getHotelComments(int hotelID) throws RemoteException {
        return commentDataService.getHotelComments(hotelID);
    }

    @Override
    public ArrayList<ProvincePO> getProvince() throws RemoteException {
        return areaDataService.getProvince();
    }

    @Override
    public ArrayList<CityPO> getCity(String provinceNum) throws RemoteException {
        return areaDataService.getCity(provinceNum);
    }

    @Override
    public ArrayList<DistrictPO> getDistrict(String cityNum) throws RemoteException {
        return areaDataService.getDistrict(cityNum);
    }

    @Override
    public ArrayList<CbdPO> getCbd(String districtNum) throws RemoteException {
        return areaDataService.getCbd(districtNum);
    }

    @Override
    public ArrayList<HotelPO> getHotelByAddress(String address) throws RemoteException {
        return areaDataService.getHotelByAddress(address);
    }

    @Override
    public ArrayList<HotelPO> getHotelByName(String name) throws RemoteException {
        return areaDataService.getHotelByName(name);
    }

    /*@Override
    public ArrayList<HotelPO> getAreaList(CbdPO conditionInfo) throws RemoteException {
        return null;
    }*/
}
