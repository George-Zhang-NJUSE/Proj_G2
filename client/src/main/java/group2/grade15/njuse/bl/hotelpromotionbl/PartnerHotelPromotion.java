package group2.grade15.njuse.bl.hotelpromotionbl;

import group2.grade15.njuse.bl.orderbl.Order;
import group2.grade15.njuse.bl.orderbl.OrderBL;
import group2.grade15.njuse.bl.promotionfactory.HotelPromotionBL;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;

/**
 * Created by Guo on 2016/12/6.
 */
public class PartnerHotelPromotion implements HotelPromotionBL {
    public OrderBL order;

    public PartnerHotelPromotion(){
        order = new Order();
    }

    @Override
    public double countPrice(OrderVO orderVO, HotelPromotionVO hotelPromotionVO) {

        double totalPrice = order.getOriginalPrice(orderVO);

        if(isFit(orderVO, hotelPromotionVO)) {
            return totalPrice * hotelPromotionVO.getDiscount();
        } else {
            return totalPrice;
        }
    }

    private boolean isFit(OrderVO orderVO, HotelPromotionVO hotelPromotionVO){
        int vipID= hotelPromotionVO.getVipID();
        int customerID = orderVO.getCustomerID();

        if(vipID == customerID) {
            return false;
        } else {
            return true;
        }
    }
}
