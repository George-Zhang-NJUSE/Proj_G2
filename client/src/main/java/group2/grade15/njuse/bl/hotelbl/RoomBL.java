package group2.grade15.njuse.bl.hotelbl;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.RoomVO;

import java.sql.Timestamp;

/**
 * Created by 果宝 on 2016/12/9.
 */
public interface RoomBL {
    public ResultMessage modifyRoomInfo(int hotelID, RoomVO roomInfo);

    public int getSpareRoomNumInTime(RoomType type, int hotelID, Timestamp checkInTime, Timestamp checkOutTime);
}
