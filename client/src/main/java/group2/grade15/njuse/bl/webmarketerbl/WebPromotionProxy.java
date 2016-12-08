package group2.grade15.njuse.bl.webmarketerbl;

import group2.grade15.njuse.bl.webpromotionbl.WebPromotionController;
import group2.grade15.njuse.bl.webpromotionbl.WebPromotionControllerBL;
import group2.grade15.njuse.blservice.WebPromotionServ;
import group2.grade15.njuse.po.WebPromotionPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.WebPromotionListVO;
import group2.grade15.njuse.vo.WebPromotionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * 网站优惠策略职责的代理者
 * 真正的任务通过层内接口WebPromotionControllerBL代理给WebPromotionController完成
 */
public class WebPromotionProxy implements WebPromotionServ {

    WebPromotionControllerBL webPromotionController;

    public WebPromotionProxy(){
        webPromotionController = new WebPromotionController();
    }

    @Override
    public ResultMessage createWebPromotion(WebPromotionVO webPromotionInfo) {
        return webPromotionController.createWebPromotion(webPromotionInfo);
    }

    @Override
    public WebPromotionListVO getWebPromotionList() {
        return webPromotionController.getWebPromotionList();
    }

    @Override
    public ResultMessage modifyWebPromotion(WebPromotionVO promotion) {
        return webPromotionController.modifyWebPromotion(promotion);
    }

    @Override
    public ResultMessage changeState(WebPromotionVO promotion) {
        return webPromotionController.changeState(promotion);
    }
}
