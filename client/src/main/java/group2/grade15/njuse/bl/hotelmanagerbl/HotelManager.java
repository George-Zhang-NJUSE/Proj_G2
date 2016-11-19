package bl.hotelmanagerbl;

import blservice.HotelManagerServ;
import utility.ResultMessage;
import utility.OrderState;
import vo.*;

/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
abstract public class HotelManager implements HotelManagerServ {
    private int id;
    private String password;
    private String name;
    private String contact;

    abstract public ResultMessage modifyInfo(HotelManagerVO hotelManager);

    abstract public ResultMessage modifyHotelInfo(HotelVO hotel);

    abstract public HotelVO getHotelInfo(int hotelID);

    abstract public ResultMessage modifyRoomInfo(RoomVO roomInfo);

    abstract public HotelPromotionVO createHotelPromotion(HotelPromotionVO promotionInfo);

    abstract public HotelPromotionListVO getHotelPromotionList();

    abstract public ResultMessage modifyHotelPromotion(HotelPromotionVO hotelPromotion);

    abstract public ResultMessage activateHotelPromotion(HotelPromotionVO promotionVO);

    abstract public ResultMessage stopHotelPromotion(HotelPromotionVO promotionVO);

    abstract public ResultMessage deleteHotelPromotion(HotelPromotionVO promotionVO);

    abstract public ResultMessage modifyState(int orderID, OrderState s);

    abstract public ResultMessage addCompany(int customerID);

    abstract public ResultMessage deleteCompany(int customerID);

    abstract public HotelManagerVO getInfo(int hotelManagerId);
}
