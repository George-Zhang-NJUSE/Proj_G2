package group2.grade15.njuse.bl.hotelmanagerbl;

import group2.grade15.njuse.bl.hotelbl.Hotel;
import group2.grade15.njuse.bl.hotelbl.HotelBL;
import group2.grade15.njuse.bl.hotelpromotionbl.HotelPromotionController;
import group2.grade15.njuse.bl.hotelpromotionbl.HotelPromotionControllerBL;
import group2.grade15.njuse.bl.orderbl.ModifyOrderStateBL;
import group2.grade15.njuse.bl.orderbl.OrderController;
import group2.grade15.njuse.blservice.HotelManagerServ;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

/**
 * Created by 果宝 on 2016/12/4.
 */
public class HotelManagerController implements HotelManagerServ {

    HotelManagerBL hotelManager;
    HotelBL hotel;
    HotelPromotionControllerBL hotelPromotionController;
    ModifyOrderStateBL modifyOrderState;

    public HotelManagerController(){
        hotel = new Hotel();
        hotelManager = new HotelManager();
        hotelPromotionController = new HotelPromotionController();
        modifyOrderState = new OrderController();
    }

    @Override
    public ResultMessage modifyInfo(HotelManagerVO hotelManagerVO) {
        return hotelManager.modifyInfo(hotelManagerVO);
    }

    @Override
    public ResultMessage modifyHotelInfo(HotelVO hotelVO) {
        return hotel.modifyInfo(hotelVO);
    }

    @Override
    public HotelVO getHotelInfo(int hotelID) {
        return hotel.getInfo(hotelID);
    }

    @Override
    public ResultMessage modifyRoomInfo(int hotelID, RoomVO roomInfo) {
        return hotel.modifyRoomInfo(hotelID, roomInfo);
    }

    @Override
    public ResultMessage createHotelPromotion(HotelPromotionVO promotionInfo) {
        return hotelPromotionController.createHotelPromotion(promotionInfo);
    }

    @Override
    public HotelPromotionListVO getHotelPromotionList(int hotelID) {
        return hotelPromotionController.getHotelPromotionList(hotelID);
    }

    @Override
    public ResultMessage modifyHotelPromotion(HotelPromotionVO hotelPromotion) {
        return hotelPromotionController.modifyHotelPromotion(hotelPromotion);
    }

    @Override
    public ResultMessage activateHotelPromotion(HotelPromotionVO promotionVO) {
        return hotelManager.activateHotelPromotion(promotionVO);
    }

    @Override
    public ResultMessage stopHotelPromotion(HotelPromotionVO promotionVO) {
        return hotelManager.stopHotelPromotion(promotionVO);
    }

    @Override
    public ResultMessage deleteHotelPromotion(HotelPromotionVO promotionVO) {
        return hotelManager.deleteHotelPromotion(promotionVO);
    }

    @Override
    public ResultMessage modifyState(int orderID, OrderState s) {
        return modifyOrderState.modifyState(orderID, s);
    }

    @Override
    public ResultMessage addCompany(int customerID) {
        return hotelManager.addCompany(customerID);
    }

    @Override
    public ResultMessage deleteCompany(int customerID) {
        return hotelManager.deleteCompany(customerID);
    }

    @Override
    public HotelManagerVO getInfo(int hotelManagerId) {
        return hotelManager.getInfo(hotelManagerId);
    }
}
