package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

public interface WebMarketerServ {
    public WebMarketerVO getInfo(String webMarketerId);

    public ResultMessage createWebPromotion(WebPromotionVO promotionInfo);

    public WebPromotionListVO getWebPromotionList();

    public ResultMessage modifyWebPromotion(WebPromotionVO promotion);

    public ResultMessage changeState(WebPromotionVO PromotionVO);

    public OrderListVO getAbnomalOrderList(int customerId);

    public ResultMessage modifyCredit(CreditVO Credit);

    public ResultMessage modifyState(int orderId, OrderState state);
}

