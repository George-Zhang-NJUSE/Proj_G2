package blservice;

import vo.HotelListVO;
import vo.SearchVO;

public interface SearchServ {
	public SearchVO createCondition (SearchVO conditionInfo);

	public HotelListVO search(SearchVO conditionVO);

}
