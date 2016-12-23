package group2.grade15.njuse.bl.searchbl.filter;

import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.utility.SortMethod;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.SearchConditionVO;

import java.util.ArrayList;

/**
 * 根据SearchCondition中的酒店名称对获得的酒店列表进行一次筛选
 */
public class NameFilter implements FilterBL{
    @Override
    public ArrayList<HotelVO> filter(SearchConditionVO searchCondition, ArrayList<HotelVO> hotelList) {
        String name = searchCondition.getName();

        //将酒店搜索的判定条件简化成一系列新的布尔变量
        boolean isListNotNull = (hotelList != null);
        boolean isFilterName = (searchCondition.getName().length() > 0)
                && isListNotNull;

        //根据搜索条件对酒店列表进行对应的筛选
        if (isFilterName) {
            String[] keyWords = name.split(" ");
            boolean isContain = false;
            ArrayList<HotelVO> newHotelList = new ArrayList();

            for (HotelVO hotel : hotelList) {
                //判定酒店的名字是否至少含有一个关键字
                for (String word : keyWords) {
                    if (hotel.getName().contains(word)) {
                        isContain = true;
                    }
                }

                //如果含有关键字，则加入到新的酒店列表中
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
