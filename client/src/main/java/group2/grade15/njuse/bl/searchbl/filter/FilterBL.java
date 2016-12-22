package group2.grade15.njuse.bl.searchbl.filter;

import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.SearchConditionVO;

import java.util.ArrayList;

public interface FilterBL {
    public ArrayList<HotelVO> filter(SearchConditionVO searchCondition, ArrayList<HotelVO> hotelList);
}
