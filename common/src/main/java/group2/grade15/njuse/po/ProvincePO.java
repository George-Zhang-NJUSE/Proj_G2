package group2.grade15.njuse.po;

import java.io.Serializable;
import java.util.ArrayList;

public class ProvincePO implements Serializable{
	ArrayList<String> province;
	
	public ProvincePO(ArrayList<String> provinceList){ 
		this.province=provinceList;
	}

	public ArrayList<String> getProvince() {
		return province;
	}
}
