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

    public LevelWebPromotion(){
        order = new Order();
    }

    @Override
    public double countPrice(OrderVO orderVO, WebPromotionVO webPromotionVO) {

        int customerID = orderVO.getCustomerID();
        double totalPrice = order.getOriginalPrice(orderVO);

        return getRankDiscount(totalPrice, customerID);
    }

    private double getRankDiscount(double totalPrice, int customerID){
        //根据ID获取客户的信用值，用于后面的信用等级判定
        CustomerBL customer = new Customer();
        CustomerVO customerVO = customer.getInfo(customerID);
        double coustomerCredit = customerVO.getCredit();

        ArrayList<RankPO> rankPOList = null;
        try {
            rankPOList = RemoteHelper.getInstance().getWebPromotionDataService().getRank();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        //进行客户信用等级的判断
        int size = rankPOList.size();
        double[] discount = new double[size];
        int discountRank = -1;

        for(int i = 0; i < size; i ++) {
            if(coustomerCredit > rankPOList.get(i).getStandard()){
                coustomerCredit ++;
            }
            discount[i] = rankPOList.get(i).getDiscount();
        }

        if(discountRank == -1) {
            return totalPrice;
        } else {
            return totalPrice * discount[discountRank];
        }
    }
}
