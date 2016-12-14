package group2.grade15.njuse.bl.webpromotionbl;

import group2.grade15.njuse.bl.customerbl.Customer;
import group2.grade15.njuse.bl.customerbl.CustomerBL;
import group2.grade15.njuse.bl.orderbl.Order;
import group2.grade15.njuse.bl.orderbl.OrderBL;
import group2.grade15.njuse.bl.promotionfactory.WebPromotionBL;
import group2.grade15.njuse.po.RankPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Guo on 2016/12/6.
 */
public class LevelWebPromotion implements WebPromotionBL {
    public OrderBL order;
    public CustomerBL customer;

    public LevelWebPromotion(){
        order = new Order();
        customer = new Customer();
    }

    @Override
    public double countPrice(OrderVO orderVO, WebPromotionVO webPromotionVO) {

        int customerID = orderVO.getCustomerID();
        double totalPrice = order.getOriginalPrice(orderVO);

        return getRankDiscount(totalPrice, customerID);
    }

    private double getRankDiscount(double totalPrice, int customerID){
        int discountRank = customer.getRank(customerID);

        ArrayList<RankPO> rankPOList = null;
        try {
            rankPOList = RemoteHelper.getInstance().getWebPromotionDataService().getRank();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        int size = rankPOList.size();
        double[] discount = new double[size];

        for(int i = 0; i < size; i ++) {
            discount[i] = rankPOList.get(i).getDiscount();
        }

        if(discountRank == 0) {
            return totalPrice;
        } else {
            return totalPrice * discount[discountRank-1];
        }
    }
}
