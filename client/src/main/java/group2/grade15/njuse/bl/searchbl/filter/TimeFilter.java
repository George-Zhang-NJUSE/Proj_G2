package group2.grade15.njuse.bl.searchbl.filter;

import group2.grade15.njuse.bl.hotelbl.Room;
import group2.grade15.njuse.bl.hotelbl.RoomBL;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.SearchConditionVO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * 根据SearchCondition中的入住时间信息和所需房间的所剩量对获得的酒店列表进行一次筛选
 */
public class TimeFilter implements FilterBL {
    @Override
    public ArrayList<HotelVO> filter(SearchConditionVO searchCondition, ArrayList<HotelVO> hotelList) {
        RoomType type = searchCondition.getRoomType();
        Date checkInTime = searchCondition.getCheckInTime();
        Date checkOutTime = searchCondition.getCheckOutTime();
        RoomBL roomBL = new Room();
        int needRoom = searchCondition.getFreeRoomNum();

        //将酒店搜索的判定条件简化成一系列新的布尔变量
        boolean isListNotNull = (hotelList != null);
        boolean isFilterTime = (searchCondition.getCheckInTime() != null
                || searchCondition.getCheckOutTime() != null)
                && isListNotNull;

        //根据搜索条件对酒店列表进行对应的筛选
        if (isFilterTime) {
            ArrayList<HotelVO> newHotelList = new ArrayList();
            Timestamp checkIn = new Timestamp(checkInTime.getTime());
            Timestamp checkOut = new Timestamp(checkOutTime.getTime());

            for (HotelVO hotel : hotelList) {
                int spareRoomNum = 0;
                if(type != RoomType.all) {
                    spareRoomNum = roomBL.getSpareRoomNumInTime(type, hotel.getId(), checkIn, checkOut);
                } else {
                    spareRoomNum += roomBL.getSpareRoomNumInTime(RoomType.bigSingleBed, hotel.getId(), checkIn, checkOut);
                    spareRoomNum += roomBL.getSpareRoomNumInTime(RoomType.stadardDoubleBed, hotel.getId(), checkIn, checkOut);
                    spareRoomNum += roomBL.getSpareRoomNumInTime(RoomType.suiteRoom, hotel.getId(), checkIn, checkOut);
                }

                if((spareRoomNum < 10000) && (spareRoomNum >= needRoom)){
                    newHotelList.add(hotel);
                }

            }
            return newHotelList;
        } else {
            return hotelList;
        }
    }
}
