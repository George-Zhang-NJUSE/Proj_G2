package group2.grade15.njuse.vo;

import bl.promotion.Promotion;

import java.io.Serializable;
import java.util.ArrayList;

public class WebPromotionListVO implements Serializable{
	private ArrayList<Promotion> webPromotion;
	
	public WebPromotionListVO(ArrayList<Promotion> webPromotion){
		this.webPromotion=webPromotion;
	}

	public ArrayList<Promotion> getWebPromotion() {
		return webPromotion;
	}

}
