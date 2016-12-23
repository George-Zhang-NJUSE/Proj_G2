package group2.grade15.njuse.bl.searchbl.filter;

import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.utility.SortMethod;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.SearchConditionVO;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * 根据SearchCondition中的酒店星级区间对获得的酒店列表进行一次筛选
 */
public class StarFilter implements FilterBL{
    @Override
    public ArrayList<HotelVO> filter(SearchConditionVO searchCondition,ArrayList<HotelVO> hotelList) {
        int minStarLevel = searchCondition.getMinStarLevel();

        //将酒店搜索的判定条件简化成一系列新的布尔变量
        boolean isListNotNull = (hotelList != null);
        boolean isFilterStar = (searchCondition.getMinStarLevel() != 0)
                && isListNotNull;

        //根据搜索条件对酒店列表进行对应的筛选
        if (isFilterStar) {
            ArrayList<HotelVO> newHotelList = hotelList.stream()
                    .filter(hotelVO -> hotelVO.getRank() >= minStarLevel)
                    .collect(Collectors.toCollection(ArrayList::new));

            return newHotelList;
        } else {
            return hotelList;
        }
    }
}
