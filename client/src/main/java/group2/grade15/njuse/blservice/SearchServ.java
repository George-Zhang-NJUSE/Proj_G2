package group2.grade15.njuse.blservice;

import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.SearchVO;

public interface SearchServ {
	public SearchVO createCondition (SearchVO conditionInfo);

	public HotelListVO search(SearchVO conditionVO);

}
