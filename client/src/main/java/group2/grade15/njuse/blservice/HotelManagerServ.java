package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;


public interface HotelManagerServ {
	public ResultMessage modifyInfo(HotelManagerVO hotelManager) ;

	public ResultMessage modifyHotelInfo (HotelVO hotel);
	
	public HotelVO getHotelInfo (int hotelID); 

	public ResultMessage modifyRoomInfo (RoomVO roomInfo);

	public HotelPromotionVO createHotelPromotion (HotelPromotionVO promotionInfo);

	public HotelPromotionListVO getHotelPromotionList ();
	
	public ResultMessage modifyHotelPromotion (HotelPromotionVO hotelPromotion);

	public ResultMessage activateHotelPromotion (HotelPromotionVO promotionVO);
	
	public ResultMessage stopHotelPromotion (HotelPromotionVO promotionVO);
	
	public ResultMessage deleteHotelPromotion (HotelPromotionVO promotionVO);
	
	public ResultMessage modifyState(int orderID,OrderState s);

	public ResultMessage addCompany(int customerID);

	public ResultMessage deleteCompany(int customerID);

	public HotelManagerVO getInfo(int hotelManagerId);
}
