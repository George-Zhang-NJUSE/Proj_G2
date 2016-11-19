package group2.grade15.njuse.po;

import java.io.Serializable;
import java.util.ArrayList;

public class CreditListPO implements Serializable{
	private ArrayList<CreditPO> creditList;
	
	public CreditListPO(ArrayList<CreditPO> creditList){
		this.creditList=creditList;
	}

	public ArrayList<CreditPO> getCreditList() {
		return creditList;
	}

}
