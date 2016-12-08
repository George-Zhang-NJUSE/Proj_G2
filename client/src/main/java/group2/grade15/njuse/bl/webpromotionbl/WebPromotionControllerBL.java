package group2.grade15.njuse.bl.webpromotionbl;

import group2.grade15.njuse.po.WebPromotionPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.WebPromotionListVO;
import group2.grade15.njuse.vo.WebPromotionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by 果宝 on 2016/12/8.
 */
public interface WebPromotionControllerBL {
    public ResultMessage createWebPromotion(WebPromotionVO promotionInfo);

    public WebPromotionListVO getWebPromotionList();

    public ResultMessage modifyWebPromotion(WebPromotionVO promotion);

    public ResultMessage changeState(WebPromotionVO promotionVO);
}
