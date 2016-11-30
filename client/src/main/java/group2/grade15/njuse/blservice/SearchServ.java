package group2.grade15.njuse.blservice;

import group2.grade15.njuse.po.*;
import group2.grade15.njuse.vo.SearchConditionVO;

import java.util.ArrayList;

public interface SearchServ {

    public ArrayList<HotelPO> getHotelBySearch(SearchConditionVO searchCondition);

	public ArrayList<ProvincePO> getProvince();

	public ArrayList<CityPO> getCity(String provinceNum);

	public ArrayList<DistrictPO> getDistrict(String cityNum);

	public ArrayList<CbdPO> getCbd(String districtNum);

	public ArrayList<HotelPO> getHotel(String address);
}
