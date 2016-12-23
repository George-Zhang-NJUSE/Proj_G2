package group2.grade15.njuse.bl.searchbl.filter;

import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.utility.SortMethod;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.RoomVO;
import group2.grade15.njuse.vo.SearchConditionVO;

import java.util.ArrayList;

/**
 * 根据SearchCondition中的房间类型对获得的酒店列表进行一次筛选
 */
public class RoomFilter implements FilterBL{
    @Override
    public ArrayList<HotelVO> filter(SearchConditionVO searchCondition, ArrayList<HotelVO> hotelList) {
        RoomType roomType = searchCondition.getRoomType();

        //将酒店搜索的判定条件简化成一系列新的布尔变量
        boolean isListNotNull = (hotelList != null);
        boolean isFilterRoom = (searchCondition.getFreeRoomNum() != 0
                || searchCondition.getRoomType() != RoomType.all)
                && isListNotNull;


        if (isFilterRoom) {
            ArrayList<HotelVO> newHotelList = new ArrayList();
            boolean isFit = false;

            for (HotelVO hotel : hotelList) {

                ArrayList<RoomVO> roomList = hotel.getRoomList();
                for(RoomVO room : roomList){
                    if( room.getType() == roomType ){
                        isFit = true;
                    }
                }

                if(isFit){
                    newHotelList.add(hotel);
                    isFit = false;
                }

            }

            return newHotelList;
        } else {
            return hotelList;
        }
    }
}
