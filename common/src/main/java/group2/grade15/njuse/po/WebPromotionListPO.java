package group2.grade15.njuse.po;

import java.io.Serializable;
import java.util.ArrayList;

import bl.promotion.Promotion;

public class WebPromotionListPO implements Serializable{
	private ArrayList<Promotion> webPromotion;
	
	public WebPromotionListPO(ArrayList<Promotion> webPromotion){
		this.webPromotion=webPromotion;
	}

	public ArrayList<Promotion> getWebPromotion() {
		return webPromotion;
	}

}
