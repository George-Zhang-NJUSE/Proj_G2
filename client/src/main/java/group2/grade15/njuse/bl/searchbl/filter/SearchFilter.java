package group2.grade15.njuse.bl.searchbl.filter;

import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.SearchConditionVO;

import java.util.ArrayList;

public class SearchFilter implements SearchFilterBL{

    ArrayList<FilterBL> filterBLs;

    public SearchFilter(){
        filterBLs = new ArrayList<>();

        filterBLs.add(new BookedFileter());
        filterBLs.add(new NameFilter());
        filterBLs.add(new PriceFilter());
        filterBLs.add(new RoomFilter());
        filterBLs.add(new ScoreFilter());
        filterBLs.add(new SortFilter());
        filterBLs.add(new StarFilter());
        filterBLs.add(new TimeFilter());
    }

    @Override
    public ArrayList<HotelVO> filterByCondition(SearchConditionVO searchCondition, HotelListVO hotelListVO) {
        ArrayList<HotelVO> filterList = hotelListVO.getList();

        for(FilterBL filter : filterBLs){
            filterList = filter.filter(searchCondition, filterList);
        }

        return filterList;
    }
}
