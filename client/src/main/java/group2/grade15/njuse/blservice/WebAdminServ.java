package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

public interface WebAdminServ {

	public WebAdminVO getInfo(String webAdminId);

	public ResultMessage createHotel(HotelVO hotel);

	public HotelListVO getHotelList();

	public HotelListVO modifyHotel (HotelVO hotel);

	public ResultMessage deleteHotel(HotelVO hotel);

	public CustomerListVO getCustomerList();

	public HotelManagerListVO getHotelManagerList();

	public WebMarketerListVO getWebMarketerList();

	public ResultMessage createHotelManager(HotelManagerVO hotelManager);

	public ResultMessage modifyWebMarketer(WebMarketerVO webMarketer);

	public ResultMessage createWebMarketer(WebMarketerVO webMarketer);

	public ResultMessage deleteWebMarketer(WebMarketerVO webMarketer);
}
