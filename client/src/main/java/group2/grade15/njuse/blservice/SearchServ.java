package group2.grade15.njuse.blservice;

import group2.grade15.njuse.vo.*;

import java.util.ArrayList;

public interface SearchServ {

    public ArrayList<HotelVO> getHotelBySearch(SearchConditionVO searchCondition);

	public ArrayList<ProvinceVO> getProvince();

	public ArrayList<CityVO> getCity(String provinceNum);

	public ArrayList<DistrictVO> getDistrict(String cityNum);

	public ArrayList<CbdVO> getCbd(String districtNum);

	public ArrayList<HotelVO> getHotel(String address);
}
