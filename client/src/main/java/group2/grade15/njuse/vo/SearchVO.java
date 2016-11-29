package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.CbdPO;
import group2.grade15.njuse.po.CityPO;
import group2.grade15.njuse.po.DistrictPO;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchVO implements Serializable{
	private ArrayList<String> city;
	private ArrayList<String> district;
	private ArrayList<String> cbd;
	private ArrayList<HotelVO> hotelList;
	
	public SearchVO(CityPO cityPO, DistrictPO districtPO, CbdPO cbdPO, ArrayList<HotelVO> hotelList){
		city = cityPO.getCity();
		district = districtPO.getDistrict();
		cbd = cbdPO.getCbd();
		this.hotelList = hotelList;
	}

	public ArrayList<String> getCity() {
		return city;
	}

	public ArrayList<String> getDistrict() {
		return district;
	}

	public ArrayList<String> getCbd() {
		return cbd;
	}

	public ArrayList<HotelVO> getHotelList() {
		return hotelList;
	}
}
