package group2.grade15.njuse.po;

import group2.grade15.njuse.bl.promotion.Promotion;

import java.io.Serializable;

public class WebPromotionListPO implements Serializable {
	private ArrayList<Promotion> webPromotion;

	public WebPromotionListPO(ArrayList<Promotion> webPromotion){
		this.webPromotion=webPromotion;
	}

	public ArrayList<Promotion> getWebPromotion() {
		return webPromotion;
	}

}
