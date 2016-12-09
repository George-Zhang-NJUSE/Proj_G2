package group2.grade15.njuse.bl.webpromotionbl;

import group2.grade15.njuse.bl.customerbl.Customer;
import group2.grade15.njuse.bl.customerbl.CustomerBL;
import group2.grade15.njuse.bl.orderbl.Order;
import group2.grade15.njuse.bl.orderbl.OrderBL;
import group2.grade15.njuse.bl.promotionfactory.WebPromotionBL;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.po.RankPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Guo on 2016/12/6.
 */
public class AreaWebPromotion implements WebPromotionBL {
    public OrderBL order;
    public CustomerBL customer;

    public AreaWebPromotion(){
        order = new Order();
        customer = new Customer();
    }

    @Override
    public double countPrice(OrderVO orderVO, WebPromotionVO webPromotionVO) {

        int customerRank = customer.getRank(orderVO.getCustomerID());
        double totalPrice = order.getOriginalPrice(orderVO);

        boolean isApply = isFit(orderVO, webPromotionVO) && customerRank >= webPromotionVO.getLevel();

        if(isApply) {
            return totalPrice * webPromotionVO.getDiscount();
        } else {
            return totalPrice;
        }
    }

    private boolean isFit(OrderVO orderVO, WebPromotionVO webPromotionVO){
        String applicableAddress = webPromotionVO.getAddress();
        //获取酒店的商圈地址
        HotelPO hotelPO = null;
        int hotelID = orderVO.getHotelID();

        try {
            hotelPO = RemoteHelper.getInstance().getHotelDataService().getHotel(hotelID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        String hotelAddress = hotelPO.getAddress();
        return applicableAddress.equals(hotelAddress);
    }
}
