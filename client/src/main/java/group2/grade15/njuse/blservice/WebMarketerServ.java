package group2.grade15.njuse.blservice;

import group2.grade15.njuse.bl.orderbl.ModifyOrderStateBL;
import group2.grade15.njuse.bl.orderbl.OrderController;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;

public interface WebMarketerServ {
	public WebMarketerVO getInfo(String webMarketerId) ;
	
	public WebPromotionVO createWebPromotion (WebPromotionVO promotionInfo);
	
	public WebPromotionListVO getWebPromotionList (String hotelId);
	
	public ResultMessage modifyWebPromotion (WebPromotionVO promotion);
	
	public ResultMessage changeState(WebPromotionVO PromotionVO);
	
	public OrderListVO getAbnomalOrderList(int customerId);
	
	public ResultMessage modifyCredit(CreditVO Credit);

	public ResultMessage modifyState(int orderId, OrderState state);
}

