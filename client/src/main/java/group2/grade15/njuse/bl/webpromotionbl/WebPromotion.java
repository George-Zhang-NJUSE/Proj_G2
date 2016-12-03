package group2.grade15.njuse.bl.webpromotionbl;

import group2.grade15.njuse.blservice.WebPromotionServ;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.WebPromotionListVO;
import group2.grade15.njuse.vo.WebPromotionVO;

import java.rmi.RemoteException;

public class WebPromotion implements WebPromotionServ,WebPromotionBL{

	public WebPromotionListVO getWebPromotionList() {
		return null;
	}

	public WebPromotionVO createWebPromotion(WebPromotionVO promotionInfo) {
		ResultMessage result = ResultMessage.FAILED;

		try {
			result = RemoteHelper.getInstance().getWebPromotionDataService().add(promotionInfo.toPO());
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return null;
	}

	public WebPromotionListVO getWebPromotionList(String hotelId) {
		return null;
	}

	public ResultMessage modifyWebPromotion(WebPromotionVO promotion) {
		return null;
	}

	public ResultMessage changeState(WebPromotionVO promotionVO) {
		return null;
	}
}
