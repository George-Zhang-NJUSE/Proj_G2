package group2.grade15.njuse.bl.webmarketerbl;

import group2.grade15.njuse.bl.promotion.Promotion;
import group2.grade15.njuse.blservice.WebPromotionServ;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.WebPromotionListVO;

/**
 * Created by Guo on 2016/11/30.
 */
public class WebPromotionProxy implements WebPromotionServ {
    @Override
    public Promotion createWebPromotion(Promotion promotionInfo) {
        return null;
    }

    @Override
    public WebPromotionListVO getWebPromotionList(String hotelId) {
        return null;
    }

    @Override
    public ResultMessage modifyWebPromotion(Promotion promotion) {
        return null;
    }

    @Override
    public ResultMessage changeState(Promotion PromotionVO) {
        return null;
    }
}
