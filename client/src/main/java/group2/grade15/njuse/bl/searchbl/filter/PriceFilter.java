package group2.grade15.njuse.bl.searchbl.filter;

import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.utility.SortMethod;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.RoomVO;
import group2.grade15.njuse.vo.SearchConditionVO;

import java.util.ArrayList;

/**
 * 根据SearchCondition中的房间价格对获得的酒店列表进行一次筛选
 */
public class PriceFilter implements FilterBL{
    @Override
    public ArrayList<HotelVO> filter(SearchConditionVO searchCondition, ArrayList<HotelVO> hotelList) {
        double maxPrice = searchCondition.getMaxPrice();
        double minPrice = searchCondition.getMinPrice();

        //将酒店搜索的判定条件简化成一系列新的布尔变量
        boolean isListNotNull = (hotelList != null);
        boolean isFilterPrice = (searchCondition.getMinPrice() != 0
                || searchCondition.getMaxPrice() != 0)
                && isListNotNull;

        //根据搜索条件对酒店列表进行对应的筛选
        if (isFilterPrice) {
            ArrayList<HotelVO> newHotelList = new ArrayList();
            boolean isContain = false; //酒店是否含有该区间价位的房间的标志

            for (HotelVO hotel : hotelList) {
                ArrayList<RoomVO> roomList = hotel.getRoomList();
                for (RoomVO room : roomList) {
                    if (room.getPrice() <= maxPrice && room.getPrice() >= minPrice) {
                        isContain = true;
                    }
                }

                if (isContain) {
                    newHotelList.add(hotel);
                    isContain = false;
                }
            }
            return newHotelList;
        } else {
            return hotelList;
        }

    }
}
