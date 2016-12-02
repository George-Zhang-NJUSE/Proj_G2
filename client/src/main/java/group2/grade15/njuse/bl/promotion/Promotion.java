package group2.grade15.njuse.bl.promotion;

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
