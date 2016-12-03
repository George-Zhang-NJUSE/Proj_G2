package group2.grade15.njuse.bl.promotionfactory;

import group2.grade15.njuse.bl.webpromotionbl.WebPromotion;
import group2.grade15.njuse.vo.WebPromotionVO;

/**
 * Created by Guo on 2016/11/30.
 */
public class WebPromotionFactory {


    static WebPromotionFactory webPromotionFactory = null;
    private WebPromotion webPromotionImpl;

    private WebPromotionFactory(){
        webPromotionImpl = new WebPromotion();
    }

    public static WebPromotionFactory getInstance(){
        if(webPromotionFactory == null){
            webPromotionFactory = new WebPromotionFactory();
        }
        return webPromotionFactory;
    }

    public WebPromotionVO createWebPromotion(WebPromotionVO vo) {
        return webPromotionImpl.createWebPromotion(vo);
    }

}
