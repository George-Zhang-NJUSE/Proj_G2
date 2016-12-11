package group2.grade15.njuse.bl.hotelmanagerbl;

import group2.grade15.njuse.bl.hotelbl.Hotel;
import group2.grade15.njuse.bl.hotelbl.HotelBL;
import group2.grade15.njuse.bl.hotelbl.Room;
import group2.grade15.njuse.bl.hotelbl.RoomBL;
import group2.grade15.njuse.bl.hotelpromotionbl.HotelPromotionController;
import group2.grade15.njuse.bl.hotelpromotionbl.HotelPromotionControllerBL;
import group2.grade15.njuse.bl.orderbl.Order;
import group2.grade15.njuse.bl.orderbl.OrderBL;
import group2.grade15.njuse.blservice.HotelManagerServ;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

import java.sql.Date;

/**
 * HotelManagerController的职责是接受酒店经理界面传递来的请求
 * 整个控制器采用代理者模式
 * 将具体的业务转交持有的对应接口实现
 * 具体的方法的定义可查看对应接口里的方法注释
 * @author Guo
 */
public class HotelManagerController implements HotelManagerServ {

    private HotelManagerBL hotelManager;
    private RoomBL room;
    private HotelBL hotel;
    private HotelPromotionControllerBL hotelPromotionController;
    private OrderBL order;

    public HotelManagerController(){
        room = new Room();
        hotel = new Hotel();
        hotelManager = new HotelManager();
        hotelPromotionController = new HotelPromotionController();
        order = new Order();
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
        return room.modifyRoomInfo(hotelID, roomInfo);
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
        return order.modifyState(orderID, s);
    }

    @Override
    public HotelManagerVO getInfo(int hotelManagerId) {
        return hotelManager.getInfo(hotelManagerId);
    }

    @Override
    public ResultMessage updateTime(Date checkIn, Date checkOut, int orderID) {
        return order.updateTime(checkIn, checkOut, orderID);
    }
}
