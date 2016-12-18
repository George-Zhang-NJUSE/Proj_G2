package group2.grade15.njuse.bl.hotelbl;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.RoomVO;

import javax.xml.transform.Result;
import java.sql.Timestamp;

/**
 * Created by Guo on 2016/12/9.
 */
public interface RoomBL {
    public ResultMessage modifyRoomInfo(int hotelID, RoomVO roomInfo);

    public ResultMessage addRoomType(int hotelID, RoomVO roomVO);

    public ResultMessage deleteRoomType(int hotelID, RoomType type);

    public int getSpareRoomNumInTime(RoomType type, int hotelID, Timestamp checkInTime, Timestamp checkOutTime);
}
