package group2.grade15.njuse.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class HotelPromotionListVO implements Serializable {
	private ArrayList<WebPromotionVO> promotionList;

	public HotelPromotionListVO (ArrayList<WebPromotionVO> promotionList){
		this.promotionList = promotionList;
	}

	public ArrayList<WebPromotionVO> getHotelPromotion() {
		return promotionList;
	}
}
