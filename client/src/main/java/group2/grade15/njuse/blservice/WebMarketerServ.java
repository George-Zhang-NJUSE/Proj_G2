package blservice;

import bl.promotion.Promotion;
import utility.ResultMessage;
import vo.CreditVO;
import vo.OrderListVO;
import vo.WebMarketerVO;
import vo.WebPromotionListVO;

public interface WebMarketerServ {
	public WebMarketerVO getInfo(int WebMarketerId) ;
	
	public Promotion createWebPromotion (Promotion promotionInfo); 
	
	public WebPromotionListVO getWebPromotionList (String hotelId); 
	
	public ResultMessage modifyWebPromotion (Promotion promotion); 
	
	public ResultMessage changeState(Promotion PromotionVO); 
	
	public OrderListVO getAbnomalOrderList(int customerId);
	
	public ResultMessage modifyCredit(CreditVO Credit);

}

