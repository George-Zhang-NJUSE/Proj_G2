package group2.grade15.njuse.po;

import java.io.Serializable;
import java.util.ArrayList;

public class HotelListPO implements Serializable{
	ArrayList<HotelPO> list;
	public HotelListPO(ArrayList<HotelPO> list){
		this.list=list;
	}
	public ArrayList<HotelPO> getList() {
		return list;
	}

}
