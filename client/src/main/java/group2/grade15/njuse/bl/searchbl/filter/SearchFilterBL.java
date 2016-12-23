package group2.grade15.njuse.bl.searchbl.filter;


import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.SearchConditionVO;

import java.util.ArrayList;

public interface SearchFilterBL {
    public ArrayList<HotelVO> filterByCondition(SearchConditionVO searchCondition, HotelListVO hotelListVO);
}
