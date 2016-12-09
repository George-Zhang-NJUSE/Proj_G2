package group2.grade15.njuse.bl.webpromotionbl;

import group2.grade15.njuse.bl.customerbl.Customer;
import group2.grade15.njuse.bl.customerbl.CustomerBL;
import group2.grade15.njuse.bl.hotelbl.Hotel;
import group2.grade15.njuse.bl.hotelbl.HotelBL;
import group2.grade15.njuse.bl.promotionfactory.WebPromotionBL;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.po.RankPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Guo on 2016/12/6.
 */
public class LevelWebPromotion implements WebPromotionBL {

    @Override
    public double countPrice(OrderVO orderVO, WebPromotionVO webPromotionVO) {
        int roomNum = orderVO.getRoomSum();
        int hotelID = orderVO.getHotelID();
        RoomType roomType = orderVO.getType();

        HotelBL hotel = new Hotel();
        double roomPrice = -1;

        HotelVO hotelVO = hotel.getInfo(hotelID);
        ArrayList<RoomVO> roomList = hotelVO.getRoomList();
        for (RoomVO room : roomList) {
            if (room.getType() == roomType) {
                roomPrice = room.getPrice();
            }
        }

        double totalPrice = roomPrice * roomNum;

        return totalPrice;
    }

    private double gerRankDiscount(double totalPrice, int customerID){
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
