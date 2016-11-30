package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.PromotionPO;
import group2.grade15.njuse.utility.PromotionType;

public class PromotionVO {

    private PromotionType type;
    private int id;

	public PromotionVO(PromotionPO po) {
		type = po.getType();
	}

    public PromotionType getType() {
        return type;
    }

    public PromotionPO toPO(){
        return new PromotionPO(type);
    }

    public int getId() {
        return id;
    }
}
