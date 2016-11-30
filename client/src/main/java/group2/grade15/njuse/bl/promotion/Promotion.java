package group2.grade15.njuse.bl.promotion;

import group2.grade15.njuse.po.PromotionPO;
import group2.grade15.njuse.utility.PromotionType;

public class Promotion {
	private PromotionPO po;

	public Promotion(PromotionPO po){
		this.po = po;
	}
	public PromotionType getType() {
		return po.getType();
	}
	public int getId() {
		return po.getId();
	}
}
