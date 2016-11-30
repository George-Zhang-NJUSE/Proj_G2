package group2.grade15.njuse.bl.searchbl;

import group2.grade15.njuse.blservice.SearchServ;
import group2.grade15.njuse.po.*;
import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.SearchConditionVO;

import java.util.ArrayList;

public class Search implements SearchServ{

	@Override
	public ArrayList<HotelPO> getHotelBySearch(SearchConditionVO searchCondition) {
		return null;
	}

	@Override
	public ArrayList<ProvincePO> getProvince() {
		return null;
	}

	@Override
	public ArrayList<CityPO> getCity(String provinceNum) {
		return null;
	}

	@Override
	public ArrayList<DistrictPO> getDistrict(String cityNum) {
		return null;
	}

	@Override
	public ArrayList<CbdPO> getCbd(String districtNum) {
		return null;
	}

	@Override
	public ArrayList<HotelPO> getHotel(String address) {
		return null;
	}
}
