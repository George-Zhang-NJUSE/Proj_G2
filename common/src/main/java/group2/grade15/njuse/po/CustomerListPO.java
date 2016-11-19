package group2.grade15.njuse.po;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerListPO implements Serializable{
	private ArrayList<CustomerPO> list;
	
	public CustomerListPO(ArrayList<CustomerPO> list){
		this.list=list;
	}

	public ArrayList<CustomerPO> getList() {
		return list;
	}

}
