package bl.searchbl;

import java.util.ArrayList;

import blservice.SearchServ;
import po.HotelPO;
import vo.HotelListVO;
import vo.SearchVO;

public class Search implements SearchServ{
	private String country,province,city,district,cbd;
	private ArrayList<HotelPO> hotelList;
	
	@Override
	public SearchVO createCondition(SearchVO conditionInfo) {
		return null;
	}

	@Override
	public HotelListVO search(SearchVO conditionVO) {
		return null;
	}
	
}
