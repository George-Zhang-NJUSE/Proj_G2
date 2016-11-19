package group2.grade15.njuse.po;

import java.io.Serializable;
import java.util.ArrayList;

public class DistrictPO implements Serializable{
	ArrayList<String> district;
	
	public DistrictPO(ArrayList<String> districtList){
		this.district=districtList;
	}

	public ArrayList<String> getDistrict() {
		return district;
	}
}
