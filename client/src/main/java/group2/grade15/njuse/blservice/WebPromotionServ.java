package group2.grade15.njuse.blservice;

import group2.grade15.njuse.bl.promotion.Promotion;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.WebPromotionListVO;

public interface WebPromotionServ {
	public Promotion createWebPromotion (Promotion promotionInfo); 
	
	public WebPromotionListVO getWebPromotionList (String hotelId); 
	
	public ResultMessage modifyWebPromotion (Promotion promotion); 

	public ResultMessage changeState(Promotion romotion);
}
