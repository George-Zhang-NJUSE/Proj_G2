package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.po.RoomPO;

import java.io.Serializable;
import java.util.ArrayList;

public class HotelVO implements Serializable{
	private int id;
	private String name;
	private String address;
	private String contact;
	private String introduction;
	private ArrayList<String> facility;
	private ArrayList<RoomVO> roomList;
	private ArrayList<CustomerVO> vipList;
	private int rank;
	private int score;
	
	public HotelVO(int id, String name, String address, String contact, String introduction, ArrayList<String> facility, ArrayList<RoomVO> roomList, int rank, int score, ArrayList<CustomerVO> vipList){
		this.id = id;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.introduction = introduction;
		this.facility = facility;
		this.roomList = roomList;
		this.rank = rank;
		this.score = score;
		this.vipList = vipList;
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

	public ArrayList<String> getFacility() {
		return facility;
	}

	public ArrayList<RoomVO> getRoomList() {
		return roomList;
	}

	public ArrayList<CustomerVO> getVipList() {
		return vipList;
	}


	public int getRank() {
		return rank;
	}

	public int getScore() {
		return score;
	}
	

}
