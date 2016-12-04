package group2.grade15.njuse.bl.webmarketerbl;

import group2.grade15.njuse.bl.orderbl.OrderList;
import group2.grade15.njuse.bl.orderbl.OrderListBL;
import group2.grade15.njuse.blservice.WebMarketerServ;
import group2.grade15.njuse.po.WebMarketerPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;

/**
 * Created by Guo on 2016/11/30.
 */
public class WebMarketerController implements WebMarketerServ {
    private ChargeProxy chargeProxy;
    private RevokeProxy revokeProxy;
    private WebPromotionProxy webPromotionProxy;
    private OrderListBL orderList;

    public WebMarketerController() {
        chargeProxy = new ChargeProxy();
        revokeProxy = new RevokeProxy();
        webPromotionProxy = new WebPromotionProxy();
        orderList = new OrderList();
    }

    @Override
    public WebMarketerVO getInfo(String webMarketerId) {
        WebMarketerPO po = null;
        try {
            po = RemoteHelper.getInstance().getWebMarketerDataService().getWebMarketer(webMarketerId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (po != null) {
            return new WebMarketerVO(po);
        } else {
            return null;
        }
    }

    @Override
    public WebPromotionVO createWebPromotion(WebPromotionVO promotionInfo) {
        return webPromotionProxy.createWebPromotion(promotionInfo);
    }

    @Override
    public WebPromotionListVO getWebPromotionList(String hotelId) {
        return webPromotionProxy.getWebPromotionList(hotelId);
    }

    @Override
    public ResultMessage modifyWebPromotion(WebPromotionVO promotion) {
        return webPromotionProxy.modifyWebPromotion(promotion);
    }

    @Override
    public ResultMessage changeState(WebPromotionVO promotion) {
        return webPromotionProxy.changeState(promotion);
    }

    @Override
    public OrderListVO getAbnomalOrderList(int customerId) {
        return orderList.getAbnormalOrderList(customerId);
    }

    @Override
    public ResultMessage modifyCredit(CreditVO credit) {
        return chargeProxy.modifyCredit(credit);
    }

    @Override
    public ResultMessage modifyState(int orderId, OrderState state) {
        return revokeProxy.modifyState(orderId, state);
    }
}
