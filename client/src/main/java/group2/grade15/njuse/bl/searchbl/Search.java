package group2.grade15.njuse.bl.searchbl;

import group2.grade15.njuse.blservice.SearchServ;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.SearchVO;

import java.util.ArrayList;

public class Search implements SearchServ{
	private String country,province,city,district,cbd;
	private ArrayList<HotelPO> hotelList;


	public SearchVO createCondition(SearchVO conditionInfo) {
		return null;
	}

	public HotelListVO search(SearchVO conditionVO) {
		return null;
	}
}
