package group2.grade15.njuse.bl.hotelbl;

import group2.grade15.njuse.utility.RoomType;

import java.sql.Date;

/**
 * Created by Guo on 2016/12/8.
 */
public interface GetSpareRoomNumBL {
    public int getSpareRoomNumInTime(RoomType type, int hotelID, Date checkInTime, Date checkOutTime);
}
