package group2.grade15.njuse.bl.hotelmanagerbl;

import group2.grade15.njuse.blservice.HotelManagerServ;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.vo.*;

/**
 * Created by Guo on 2016/11/13.
 */
public class HotelManager implements HotelManagerServ {

    @Override
    public HotelManagerVO getInfo(int hotelManagerId) {
        return null;
    }

    @Override
    public ResultMessage modifyInfo(HotelManagerVO hotelManager) {
        return null;
    }

    @Override
    public ResultMessage modifyHotelInfo(HotelVO hotel) {
        return null;
    }

    @Override
    public HotelVO getHotelInfo(int hotelID) {
        return null;
    }

    @Override
    public ResultMessage modifyRoomInfo(RoomVO roomInfo) {
        return null;
    }

    @Override
    public HotelPromotionVO createHotelPromotion(HotelPromotionVO promotionInfo) {
        return null;
    }

    @Override
    public HotelPromotionListVO getHotelPromotionList() {
        return null;
    }

    @Override
    public ResultMessage modifyHotelPromotion(HotelPromotionVO hotelPromotion) {
        return null;
    }

    @Override
    public ResultMessage activateHotelPromotion(HotelPromotionVO promotionVO) {
        return null;
    }

    @Override
    public ResultMessage stopHotelPromotion(HotelPromotionVO promotionVO) {
        return null;
    }

    @Override
    public ResultMessage deleteHotelPromotion(HotelPromotionVO promotionVO) {
        return null;
    }

    @Override
    public ResultMessage modifyState(int orderID, OrderState s) {
        return null;
    }

    @Override
    public ResultMessage addCompany(int customerID) {
        return null;
    }

    @Override
    public ResultMessage deleteCompany(int customerID) {
        return null;
    }

}
