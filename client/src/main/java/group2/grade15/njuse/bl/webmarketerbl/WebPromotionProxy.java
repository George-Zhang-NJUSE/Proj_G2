package group2.grade15.njuse.bl.webmarketerbl;

import group2.grade15.njuse.blservice.WebPromotionServ;
import group2.grade15.njuse.po.WebPromotionPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.WebPromotionListVO;
import group2.grade15.njuse.vo.WebPromotionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Guo on 2016/11/30.
 */
public class WebPromotionProxy implements WebPromotionServ {

    @Override
    public ResultMessage createWebPromotion(WebPromotionVO webPromotionInfo) {
        ResultMessage result;
        try {
            result = RemoteHelper.getInstance().getWebPromotionDataService().add(webPromotionInfo.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            result =ResultMessage.CONNECTION_EXCEPTION;
        }
        return result;
    }

    @Override
    public WebPromotionListVO getWebPromotionList() {
        ArrayList<WebPromotionPO> webPromotionPOList = new ArrayList();
        ArrayList<WebPromotionVO> webPromotionList = new ArrayList();

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
    public ResultMessage changeState(WebPromotionVO promotion) {
        ResultMessage result;

        try {
            result = RemoteHelper.getInstance().getWebPromotionDataService().modify(promotion.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.CONNECTION_EXCEPTION;
        }

        return result;    }
}
