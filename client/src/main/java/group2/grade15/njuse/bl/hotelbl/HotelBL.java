package group2.grade15.njuse.bl.hotelbl;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.RoomVO;


/**
 * Created by 果宝 on 2016/12/4.
 */
public interface HotelBL {
    public ResultMessage modifyInfo(HotelVO hotel);

    public HotelVO getInfo(int hotelID);

    public ResultMessage modifyRoomInfo(int hotelID, RoomVO roomInfo);
}
