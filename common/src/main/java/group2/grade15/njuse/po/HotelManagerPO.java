package group2.grade15.njuse.po;

import java.io.Serializable;

public class HotelManagerPO implements Serializable{
	private int id;
	private String password;
	private String name;
	private String contact;
	
	public HotelManagerPO(int id,String password,String name,String contact){
		this.id=id;
		this.password=password;
		this.name=name;
		this.contact=contact;
	}
	
	public int getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getContact() {
		return contact;
	}
	

}
