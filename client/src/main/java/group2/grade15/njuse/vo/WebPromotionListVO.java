package group2.grade15.njuse.vo;

import java.util.ArrayList;

/**
 * Created by Guo on 2016/11/29.
 */
public class WebPromotionListVO {
    private ArrayList<PromotionVO> promotionList;

    public WebPromotionListVO (ArrayList<PromotionVO> promotionList){
        this.promotionList = promotionList;
    }

    public ArrayList<PromotionVO> getWebPromotion() {
        return promotionList;
    }
}
