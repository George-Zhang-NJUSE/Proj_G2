package group2.grade15.njuse.po;

import group2.grade15.njuse.utility.PromotionType;

import java.io.Serializable;

public abstract class PromotionPO implements Serializable{
	private PromotionType type;
	public PromotionPO(PromotionType type){
		this.type=type;
	}
	public PromotionType getType() {
		return type;
	}
}
