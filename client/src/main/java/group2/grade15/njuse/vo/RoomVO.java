package group2.grade15.njuse.vo;

import group2.grade15.njuse.utility.RoomType;

import java.io.Serializable;

public class RoomVO implements Serializable{
	private RoomType type;
	private double price;
	private int totalRoomNum;
	private int spareRoomNum;
	
	public RoomVO(RoomType type,double price,int totalRoomNum,int spareRoomNum){
		this.type=type;
		this.price=price;
		this.totalRoomNum=totalRoomNum;
		this.spareRoomNum=spareRoomNum;
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
	

}
