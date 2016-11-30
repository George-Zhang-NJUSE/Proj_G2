package group2.grade15.njuse.po;

import java.io.Serializable;
import java.util.ArrayList;

public class CityPO implements Serializable{
	private String cityName;
	private String cityNum;

	public CityPO(String cityName, String cityNum) {
		this.cityName = cityName;
		this.cityNum = cityNum;
	}

	public String getCityName() {
		return cityName;
	}

	public String getCityNum() {
		return cityNum;
	}
}
