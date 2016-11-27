package group2.grade15.njuse.vo;

import group2.grade15.njuse.utility.MemberType;

import java.io.Serializable;
import java.sql.Date;

public class CustomerVO implements Serializable{
	private int id;
	private String name;
	private String password;
	private String contact;
	private Date birthday;
	private final double credit;
	private MemberType type;

	public CustomerVO(int id,String name,String password,String contact,Date birthday,double credit,MemberType type){
		this.id = id;
		this.name = name;
		this.password = password;
		this.contact = contact;
		this.birthday = birthday;
		this.credit = credit;
		this.type = type;
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

	public Date getBirthday(){
		return birthday;
	}

	public double getCredit() {
		return credit;
	}

	public MemberType getType() {
		return type;
	}
}
