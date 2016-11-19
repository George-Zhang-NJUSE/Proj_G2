package group2.grade15.njuse.po;

import java.io.Serializable;
import java.util.ArrayList;

public class CityPO implements Serializable{
	ArrayList<String> city;
	
	public CityPO(ArrayList<String> cityList){
		this.city=cityList;
	}

	public ArrayList<String> getCity() {
		return city;
	}
	
}
