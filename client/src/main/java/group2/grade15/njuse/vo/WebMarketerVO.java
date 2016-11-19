package group2.grade15.njuse.vo;

import java.io.Serializable;;

public class WebMarketerVO implements Serializable{
	private int id;
	private String password;
	private int staffID;
	
	public WebMarketerVO(int id,String password,int staffID){
		this.id=id;
		this.password=password;
		this.staffID=staffID;
	}

	public int getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public int getStaffID() {
		return staffID;
	}

}
