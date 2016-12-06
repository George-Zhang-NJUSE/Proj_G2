package group2.grade15.njuse.bl.webmarketerbl;

import group2.grade15.njuse.blservice.WebPromotionServ;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.WebPromotionListVO;
import group2.grade15.njuse.vo.WebPromotionVO;

/**
 * Created by Guo on 2016/11/30.
 */
public class WebPromotionProxy implements WebPromotionServ {

    @Override
    public ResultMessage createWebPromotion(WebPromotionVO webPromotionInfo) {
        return null;
    }

    @Override
    public WebPromotionListVO getWebPromotionList() {
        return null;
    }

    @Override
    public ResultMessage modifyWebPromotion(WebPromotionVO promotion) {
        return null;
    }

    @Override
    public ResultMessage changeState(WebPromotionVO promotion) {
        return null;
    }
}
