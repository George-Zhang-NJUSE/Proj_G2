package group2.grade15.njuse.po;

import java.io.Serializable;

public class CustomerPO implements Serializable{
	private int id;
	private String name;
	private String password;
	private String contact;
	private String identityNum;
	private final int credit;
	boolean isVIP;
	
	public CustomerPO(int id,String name,String password,String contact,String identity,int credit,boolean isVIP){
		this.id=id;
		this.name=name;
		this.password=password;
		this.contact=contact;
		this.identityNum=identity;
		this.credit=credit;
		this.isVIP=isVIP;
	}

	public String getIdentityNum() {
		return identityNum;
	}

	public boolean isVIP() {
		return isVIP;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getContact() {
		return contact;
	}

	public int getCredit() {
		return credit;
	}
	
	

}
