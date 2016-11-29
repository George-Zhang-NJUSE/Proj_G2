package group2.grade15.njuse.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerListVO implements Serializable{
	private ArrayList<CustomerVO> list;
	
	public CustomerListVO(ArrayList<CustomerVO> list){
		this.list = list;
	}

	public ArrayList<CustomerVO> getList() {
		return list;
	}

}
