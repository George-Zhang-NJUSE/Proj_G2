package group2.grade15.njuse.po;

import java.io.Serializable;
import java.util.ArrayList;

import bl.promotion.Promotion;

public class HotelPromotionListPO implements Serializable{
	private ArrayList<Promotion> hotelPromotion;
	
	public HotelPromotionListPO (ArrayList<Promotion> hotelPromotion){
		this.hotelPromotion=hotelPromotion;
	}

	public ArrayList<Promotion> getHotelPromotion() {
		return hotelPromotion;
	}
}
