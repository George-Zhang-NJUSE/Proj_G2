package group2.grade15.njuse.bl.searchbl;

import group2.grade15.njuse.blservice.SearchServ;
import group2.grade15.njuse.po.*;
import group2.grade15.njuse.vo.HotelListVO;

import java.util.ArrayList;

public class Search implements SearchServ{
	private String country,province,city,district,cbd;
	private ArrayList<HotelPO> hotelList;


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
