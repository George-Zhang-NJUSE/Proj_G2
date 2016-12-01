package group2.grade15.njuse.bl.searchbl;

import group2.grade15.njuse.blservice.SearchServ;
import group2.grade15.njuse.po.*;
import group2.grade15.njuse.vo.*;

import java.util.ArrayList;

public class Search implements SearchServ{

	@Override
	public ArrayList<HotelVO> getHotelBySearch(SearchConditionVO searchCondition) {
		return null;
	}

	@Override
	public ArrayList<ProvinceVO> getProvince() {
		return null;
	}

	@Override
	public ArrayList<CityVO> getCity(String provinceNum) {
		return null;
	}

	@Override
	public ArrayList<DistrictVO> getDistrict(String cityNum) {
		return null;
	}

	@Override
	public ArrayList<CbdVO> getCbd(String districtNum) {
		return null;
	}

	@Override
	public ArrayList<HotelVO> getHotel(String address) {
		return null;
	}
}
