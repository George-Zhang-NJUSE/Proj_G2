package group2.grade15.njuse.bl.webpromotionbl;

import group2.grade15.njuse.blservice.WebPromotionServ;
import group2.grade15.njuse.po.WebPromotionPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.WebPromotionListVO;
import group2.grade15.njuse.vo.WebPromotionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class WebPromotionController implements WebPromotionServ, GetWebPromotionBL, WebPromotionControllerBL {

    @Override
    public ResultMessage createWebPromotion(WebPromotionVO promotionInfo) {
        ResultMessage result;

        try {
            result = RemoteHelper.getInstance().getWebPromotionDataService().add(promotionInfo.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.CONNECTION_EXCEPTION;
        }

        return result;
    }

    @Override
    public WebPromotionListVO getWebPromotionList() {
        ArrayList<WebPromotionVO> webPromotionList = new ArrayList();
        ArrayList<WebPromotionPO> webPromotionPOList = new ArrayList();

        try {
            webPromotionPOList = RemoteHelper.getInstance().getWebPromotionDataService().getList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        for(WebPromotionPO webPromotionPO : webPromotionPOList){
            webPromotionList.add(new WebPromotionVO(webPromotionPO));
        }

        return new WebPromotionListVO(webPromotionList);
    }

    @Override
    public ResultMessage modifyWebPromotion(WebPromotionVO promotion) {
        ResultMessage result;
        try {
            result = RemoteHelper.getInstance().getWebPromotionDataService().modify(promotion.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.CONNECTION_EXCEPTION;
        }

        return result;
    }

    @Override
    public ResultMessage changeState(WebPromotionVO promotionVO) {
        ResultMessage result;
        try {
            result = RemoteHelper.getInstance().getWebPromotionDataService().modify(promotionVO.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.CONNECTION_EXCEPTION;
        }

        return result;
    }
}
