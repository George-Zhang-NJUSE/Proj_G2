package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.RoomPO;
import group2.grade15.njuse.utility.RoomType;

import java.io.Serializable;

public class RoomVO implements Serializable{
	private RoomType type;
	private double price;
	private int totalRoomNum;
	private int spareRoomNum;
	
	public RoomVO(RoomPO po){
		type = po.getType();
		price = po.getPrice();
		totalRoomNum = po.getTotalRoomNum();
		spareRoomNum = po.getSpareRoomNum();
	}

	public RoomType getType() {
		return type;
	}

	public double getPrice() {
		return price;
	}

	public int getTotalRoomNum() {
		return totalRoomNum;
	}

	public int getSpareRoomNum() {
		return spareRoomNum;
	}

	public RoomPO toPO(){
		return new RoomPO(type, price, totalRoomNum, spareRoomNum);
	}
}
