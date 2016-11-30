package group2.grade15.njuse.bl.webmarketerbl;

import group2.grade15.njuse.bl.promotionfactory.WebPromotionFactory;
import group2.grade15.njuse.blservice.WebPromotionServ;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.PromotionVO;
import group2.grade15.njuse.vo.WebPromotionListVO;
import group2.grade15.njuse.vo.WebPromotionVO;

/**
 * Created by Guo on 2016/11/30.
 */
public class WebPromotionProxy implements WebPromotionServ {

    @Override
    public WebPromotionVO createWebPromotion(PromotionVO promotionInfo) {
        return WebPromotionFactory.getInstance().createWebPromotion(promotionInfo);
    }

    @Override
    public WebPromotionListVO getWebPromotionList(String hotelId) {
        return null;
    }

    @Override
    public ResultMessage modifyWebPromotion(PromotionVO promotion) {
        return null;
    }

    @Override
    public ResultMessage changeState(PromotionVO PromotionVO) {
        return null;
    }
}
