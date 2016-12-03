package group2.grade15.njuse.vo;

import java.util.ArrayList;

/**
 * Created by Guo on 2016/11/29.
 */
public class WebPromotionListVO {
    private ArrayList<WebPromotionVO> promotionList;

    public WebPromotionListVO (ArrayList<WebPromotionVO> promotionList){
        this.promotionList = promotionList;
    }

    public ArrayList<WebPromotionVO> getWebPromotion() {
        return promotionList;
    }
}
