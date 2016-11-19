package group2.grade15.njuse.po;

import java.io.Serializable;
import java.util.ArrayList;

public class WebMarketerListPO implements Serializable{
	private ArrayList<WebMarketerPO> list;
	
	public WebMarketerListPO(ArrayList<WebMarketerPO> list){
		this.list=list;
	}

	public ArrayList<WebMarketerPO> getList() {
		return list;
	}

}
