package group2.grade15.njuse.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class HotelPromotionListVO implements Serializable {
	private ArrayList<PromotionVO> promotionList;

	public HotelPromotionListVO (ArrayList<PromotionVO> promotionList){
		this.promotionList = promotionList;
	}

	public ArrayList<PromotionVO> getHotelPromotion() {
		return promotionList;
	}
}
