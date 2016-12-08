package group2.grade15.njuse.bl.webpromotionbl;

import group2.grade15.njuse.bl.promotionfactory.WebPromotionBL;
import group2.grade15.njuse.vo.WebPromotionVO;

/**
 * Created by Guo on 2016/12/6.
 */
public class TimeWebPromotion implements WebPromotionBL {

    @Override
    public double countPrice(double originalPrice, WebPromotionVO webPromotionVO) {
        return originalPrice * webPromotionVO.getDiscount();
    }
}
