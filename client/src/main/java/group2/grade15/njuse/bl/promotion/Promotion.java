package group2.grade15.njuse.bl.promotion;

import group2.grade15.njuse.utility.PromotionType;

public class Promotion {
	private PromotionType type;
	private int id;

	public Promotion(PromotionType type,int id){
		this.type=type;
		this.id=id;
	}
	public PromotionType getType() {
		return type;
	}
	public int getId() {
		return id;
	}
}
