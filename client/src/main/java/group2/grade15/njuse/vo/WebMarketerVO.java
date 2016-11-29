package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.WebAdminPO;
import group2.grade15.njuse.po.WebMarketerPO;

import java.io.Serializable;;

public class WebMarketerVO implements Serializable{
	private String password;
	private String staffID;
	
	public WebMarketerVO(WebMarketerPO po){
		password = po.getPassword();
		staffID = po.getStaffID();
	}


	public String getPassword() {
		return password;
	}

	public String getStaffID() {
		return staffID;
	}

	public WebMarketerPO toPO(){
		return new WebMarketerPO(password, staffID);
	}
}
