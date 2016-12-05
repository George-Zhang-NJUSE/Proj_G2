package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.RoomVO;

public interface HotelServ {
    public ResultMessage modifyInfo(HotelVO hotel);

    public HotelVO getInfo(int hotelID);

    public ResultMessage modifyRoomInfo(int hotelID, RoomVO roomInfo);

    /**
     * 获取用户预订过的酒店列表
     */
    public HotelListVO getBookedHotelList(int customerID);
}
