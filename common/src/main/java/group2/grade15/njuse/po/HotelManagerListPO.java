package group2.grade15.njuse.po;

import java.io.Serializable;
import java.util.ArrayList;

public class HotelManagerListPO implements Serializable{
	ArrayList<HotelManagerPO> list;
	public HotelManagerListPO(ArrayList<HotelManagerPO> list){
		this.list=list;
	}


	public ArrayList<HotelManagerPO> getList() {
		return list;
	}
}