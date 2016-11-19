package group2.grade15.njuse.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchVO implements Serializable{
	private String country,province,city,district,cbd;
	private ArrayList<HotelVO> hotelList;
	
	public SearchVO(String country,String province,String city,String district,String cbd,ArrayList<HotelVO> hotelList){
		this.country=country;
		this.province=province;
		this.city=city;
		this.district=district;
		this.cbd=cbd;
		this.hotelList=hotelList;
	}

	public String getCountry() {
		return country;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getDistrict() {
		return district;
	}

	public String getCbd() {
		return cbd;
	}

	public ArrayList<HotelVO> getHotelList() {
		return hotelList;
	}

}
