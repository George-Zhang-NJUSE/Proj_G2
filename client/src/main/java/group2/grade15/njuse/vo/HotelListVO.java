package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.HotelPO;

import java.io.Serializable;
import java.util.ArrayList;

public class HotelListVO implements Serializable{
	ArrayList<HotelVO> list;

	public HotelListVO(ArrayList<HotelVO> list){
		this.list=list;
	}

	public ArrayList<HotelVO> getList() {
		return list;
	}

}
