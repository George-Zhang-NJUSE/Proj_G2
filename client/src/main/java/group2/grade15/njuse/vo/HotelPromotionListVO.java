package group2.grade15.njuse.vo;

import bl.promotion.Promotion;

import java.io.Serializable;
import java.util.ArrayList;

public class HotelPromotionListVO implements Serializable{
	private ArrayList<Promotion> hotelPromotion;
	
	public HotelPromotionListVO (ArrayList<Promotion> hotelPromotion){
		this.hotelPromotion=hotelPromotion;
	}

	public ArrayList<Promotion> getHotelPromotion() {
		return hotelPromotion;
	}
}
