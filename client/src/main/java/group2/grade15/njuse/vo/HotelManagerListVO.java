package group2.grade15.njuse.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class HotelManagerListVO implements Serializable{
	ArrayList<HotelManagerVO> list;
	public HotelManagerListVO(ArrayList<HotelManagerVO> list){
		this.list=list;
	}


	public ArrayList<HotelManagerVO> getList() {
		return list;
	}
}