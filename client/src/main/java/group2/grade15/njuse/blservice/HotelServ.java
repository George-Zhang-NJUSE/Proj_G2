package blservice;

import utility.ResultMessage;
import vo.HotelVO;
import vo.RoomVO;

public interface HotelServ {
	public ResultMessage modifyInfo (HotelVO hotel);

	public HotelVO getInfo (int hotelID); 

	public ResultMessage modifyRoomInfo (RoomVO roomInfo); 

	public ResultMessage addCompany(int customerID);

	public ResultMessage deleteCompany(int customerID);

}
