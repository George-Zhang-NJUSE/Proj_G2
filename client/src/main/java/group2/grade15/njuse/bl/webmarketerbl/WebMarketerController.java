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
 * 网站营销人员的Controller
 * 使用控制器的目的在于将网站营销人员的众多职责集合在一个类中，方便管理和调用
 * 该控制器实现了网站营销人员的接口，供界面层调用
 * 网站营销人员的方法除了getInfo外都由其他接口实现
 * 其中chargeProxy,revokeProxy,webPromotionProxy三个类都采用代理者模式，将任务进一步委托给真正的执行者
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
    public WebMarketerVO getInfo(int webMarketerId) {
        WebMarketerPO po = null;
        try {
            po = RemoteHelper.getInstance().getWebMarketerDataService().getWebMarketer(""+webMarketerId);
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
    public ResultMessage createWebPromotion(WebPromotionVO promotionInfo) {
        return webPromotionProxy.createWebPromotion(promotionInfo);
    }

    @Override
    public WebPromotionListVO getWebPromotionList() {
        return webPromotionProxy.getWebPromotionList();
    }

    @Override
    public ResultMessage modifyWebPromotion(WebPromotionVO promotion) {
        return webPromotionProxy.modifyWebPromotion(promotion);
    }

    @Override
    public ResultMessage deleteWebPromotion(int webPromotionID) {
        return webPromotionProxy.deleteWebPromotion(webPromotionID);
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
