package group2.grade15.njuse.bl.promotionfactory;

import group2.grade15.njuse.vo.WebPromotionVO;

/**
 * Created by Guo on 2016/11/30.
 */
public class WebPromotionFactory {

    static WebPromotionFactory webPromotionFactory = null;

    private WebPromotionFactory() {

    }

    public static WebPromotionFactory getInstance() {
        if (webPromotionFactory == null) {
            webPromotionFactory = new WebPromotionFactory();
        }
        return webPromotionFactory;
    }

    public WebPromotionVO createWebPromotion(WebPromotionVO vo) {
        return null;
    }

}
