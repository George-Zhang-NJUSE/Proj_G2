package group2.grade15.njuse.blservice;

import group2.grade15.njuse.po.*;

import java.util.ArrayList;

public interface SearchServ {

	public ArrayList<ProvincePO> getProvince();
	public ArrayList<CityPO> getCity(String provinceNum);
	public ArrayList<DistrictPO> getDistrict(String cityNum);
	public ArrayList<CbdPO> getCbd(String districtNum);
	public ArrayList<HotelPO> getHotel(String address);
}
