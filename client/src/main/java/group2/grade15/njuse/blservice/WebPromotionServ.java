package group2.grade15.njuse.blservice;

import group2.grade15.njuse.bl.promotion.Promotion;
import group2.grade15.njuse.bl.webpromotionbl.WebPromotion;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.PromotionVO;
import group2.grade15.njuse.vo.WebPromotionListVO;
import group2.grade15.njuse.vo.WebPromotionVO;

public interface WebPromotionServ {
	public WebPromotionVO createWebPromotion (PromotionVO promotionInfo);
	
	public WebPromotionListVO getWebPromotionList (String hotelId); 
	
	public ResultMessage modifyWebPromotion (PromotionVO promotion);

	public ResultMessage changeState(PromotionVO romotion);
}
