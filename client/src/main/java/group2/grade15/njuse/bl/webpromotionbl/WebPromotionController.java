package group2.grade15.njuse.bl.webpromotionbl;

import group2.grade15.njuse.blservice.WebPromotionServ;
import group2.grade15.njuse.po.WebPromotionPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelPromotionListVO;
import group2.grade15.njuse.vo.HotelPromotionVO;
import group2.grade15.njuse.vo.WebPromotionListVO;
import group2.grade15.njuse.vo.WebPromotionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
    public WebPromotionVO getWebPromotion(int webPromotionID) {
        WebPromotionListVO webPromotionListVO = getWebPromotionList();
        ArrayList<WebPromotionVO> webPromotionList = webPromotionListVO.getWebPromotionList();

        for(WebPromotionVO webPromotionVO : webPromotionList){
            if(webPromotionVO.getPromotionID() == webPromotionID){
                return webPromotionVO;
            }
        }

        return null;
    }

    @Override
    public WebPromotionListVO getWebPromotionList() {
        ArrayList<WebPromotionPO> webPromotionPOList = new ArrayList();

        try {
            webPromotionPOList = RemoteHelper.getInstance().getWebPromotionDataService().getList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        ArrayList<WebPromotionVO> webPromotionList = new ArrayList();
        webPromotionList.addAll(webPromotionPOList.stream()
                                                  .map(WebPromotionVO::new)
                                                  .collect(Collectors.toList()));

        if(webPromotionList.size() != 0) {
            return new WebPromotionListVO(webPromotionList);
        } else {
            return null;
        }
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

    @Override
    public ResultMessage deleteWebPromotion(int webPromotionID) {
        ResultMessage result;
        try {
            result = RemoteHelper.getInstance().getWebPromotionDataService().remove(webPromotionID);
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.CONNECTION_EXCEPTION;
        }

        return result;
    }


}
