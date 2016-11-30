package group2.grade15.njuse.blservice;

import group2.grade15.njuse.bl.promotion.Promotion;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CreditVO;
import group2.grade15.njuse.vo.OrderListVO;
import group2.grade15.njuse.vo.WebMarketerVO;
import group2.grade15.njuse.vo.WebPromotionListVO;

public interface WebMarketerServ {
	public WebMarketerVO getInfo(String webMarketerId) ;
	
	public Promotion createWebPromotion (Promotion promotionInfo); 
	
	public WebPromotionListVO getWebPromotionList (String hotelId);
	
	public ResultMessage modifyWebPromotion (Promotion promotion); 
	
	public ResultMessage changeState(Promotion PromotionVO); 
	
	public OrderListVO getAbnomalOrderList(int customerId);
	
	public ResultMessage modifyCredit(CreditVO Credit);

}

