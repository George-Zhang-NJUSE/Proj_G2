package group2.grade15.njuse.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class CreditListVO implements Serializable{
	private ArrayList<CreditVO> creditList;
	
	public CreditListVO(ArrayList<CreditVO> creditList){
		this.creditList = creditList;
	}

	public ArrayList<CreditVO> getCreditList() {
		return creditList;
	}

}
