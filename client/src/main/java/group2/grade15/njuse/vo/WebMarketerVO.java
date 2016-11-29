package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.WebAdminPO;

import java.io.Serializable;;

public class WebMarketerVO implements Serializable{
	private String password;
	private String staffID;
	
	public WebMarketerVO(WebAdminPO po){
		password = po.getPassword();
		staffID = po.getStaffID();
	}


	public String getPassword() {
		return password;
	}

	public String getStaffID() {
		return staffID;
	}

}
