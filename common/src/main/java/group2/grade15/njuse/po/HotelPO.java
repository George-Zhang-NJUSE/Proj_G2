package group2.grade15.njuse.po;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class HotelPO implements Serializable{
	private int id;
	private String name;
	private String address;//编码
	private String concreteAddress;//具体地址
	private String contact;
	private String introduction;
	private String facility;
	private ArrayList<RoomPO> roomList;
	private int rank;
	private double score;
	private byte[][] picture;

	public HotelPO(int id, String name, String address,String concreteAddress, String contact, String introduction, String facility,
				   ArrayList<RoomPO> roomList, int rank, double score,byte[][] picture){
		this.id=id;
		this.name=name;
		this.address=address;
		this.concreteAddress=concreteAddress;
		this.contact=contact;
		this.introduction=introduction;
		this.facility=facility;
		this.roomList=roomList;
		this.rank=rank;
		this.score=score;
		this.picture=picture;
	}

	public String getConcreteAddress() {
		return concreteAddress;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getContact() {
		return contact;
	}

	public String getIntroduction() {
		return introduction;
	}

	public String getFacility() {
		return facility;
	}

	public ArrayList<RoomPO> getRoomList() {
		return roomList;
	}

	public int getRank() {
		return rank;
	}

	public double getScore() {
		return score;
	}

	public byte[][] getPicture() {
		return picture;
	}
}
