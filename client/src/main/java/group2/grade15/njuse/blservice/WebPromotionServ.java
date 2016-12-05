package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.WebPromotionListVO;
import group2.grade15.njuse.vo.WebPromotionVO;

public interface WebPromotionServ {
    public WebPromotionVO createWebPromotion(WebPromotionVO webPromotionInfo);

    public WebPromotionListVO getWebPromotionList(String hotelId);

    public ResultMessage modifyWebPromotion(WebPromotionVO promotion);

    public ResultMessage changeState(WebPromotionVO promotion);
}
