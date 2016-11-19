package blservice;

import bl.promotion.Promotion;
import utility.ResultMessage;
import vo.WebPromotionListVO;

public interface WebPromotionServ {
	public Promotion createWebPromotion (Promotion promotionInfo); 
	
	public WebPromotionListVO getWebPromotionList (String hotelId); 
	
	public ResultMessage modifyWebPromotion (Promotion promotion); 

	public ResultMessage changeState(Promotion PromotionVO); 
}
