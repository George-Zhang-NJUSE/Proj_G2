package group2.grade15.njuse.vo;

public class PromotionVO {

    private PromotionType type;
    private int id;

	public PromotionVO(PromotionPO po) {
		type = po.getType();
        id = po.getId();
	}

	public PromotionVO(PromotionType type, int id){
        this.type = type;
        this.id = id;
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
