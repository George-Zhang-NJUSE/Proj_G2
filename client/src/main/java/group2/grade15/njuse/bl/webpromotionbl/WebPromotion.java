package group2.grade15.njuse.bl.webpromotionbl;

import group2.grade15.njuse.bl.promotion.Promotion;
import group2.grade15.njuse.blservice.WebPromotionServ;
import group2.grade15.njuse.po.PromotionPO;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.PromotionVO;
import group2.grade15.njuse.vo.WebPromotionListVO;
import group2.grade15.njuse.vo.WebPromotionVO;

public class WebPromotion extends Promotion implements WebPromotionServ,WebPromotionBL{

	public WebPromotion(PromotionPO po) {
		super(po);
	}

	public WebPromotionListVO getWebPromotionList() {
		return null;
	}

	public WebPromotionVO createWebPromotion(PromotionVO promotionInfo) {
		return null;
	}

	public WebPromotionListVO getWebPromotionList(String hotelId) {
		return null;
	}

	public ResultMessage modifyWebPromotion(PromotionVO promotion) {
		return null;
	}

	public ResultMessage changeState(PromotionVO PromotionVO) {
		return null;
	}
}
