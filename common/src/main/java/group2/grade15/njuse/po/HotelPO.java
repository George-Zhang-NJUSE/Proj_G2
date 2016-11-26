package group2.grade15.njuse.po;

import java.io.Serializable;
import java.util.ArrayList;

public class HotelPO implements Serializable{
	private int id;
	private String name;
	private String address;
	private String contact;
	private String introduction;
	private ArrayList<String> facility;
	private ArrayList<RoomPO> roomList;
	private int rank;
	private int score;
	private ArrayList<CustomerPO> vipList; 
	
	

	public HotelPO(int id,String name,String address,String contact,String introduction,ArrayList<String> facility,ArrayList<RoomPO> roomList,int rank,int score, ArrayList<CustomerPO> vipList){
		this.id=id;
		this.name=name;
		this.address=address;
		this.contact=contact;
		this.introduction=introduction;
		this.facility=facility;
		this.roomList=roomList;
		this.rank=rank;
		this.score=score;
		this.vipList=vipList;
	}

    public int getId() {
		return id;
	}

	public ArrayList<CustomerPO> getVipList() {
		return vipList;
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

	public ArrayList<RoomPO> getRoomList() {
		return roomList;
	}

	public int getRank() {
		return rank;
	}

	public int getScore() {
		return score;
	}
	

}
