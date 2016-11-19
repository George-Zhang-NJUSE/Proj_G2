package group2.grade15.njuse.vo;

import java.io.Serializable;

public class CustomerVO implements Serializable{
	private int id;
	private String name;
	private String password;
	private String contact;
	private String identityNum;
	private final int credit;
	
	public CustomerVO(int id,String name,String password,String contact,String identity,int credit){
		this.id=id;
		this.name=name;
		this.password=password;
		this.contact=contact;
		this.identityNum=identity;
		this.credit=credit;
	}

	public String getIdentityNum() {
		return identityNum;
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
