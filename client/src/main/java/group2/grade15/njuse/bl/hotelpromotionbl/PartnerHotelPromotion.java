package group2.grade15.njuse.bl.hotelpromotionbl;

import group2.grade15.njuse.bl.hotelbl.Hotel;
import group2.grade15.njuse.bl.hotelbl.HotelBL;
import group2.grade15.njuse.bl.promotionfactory.HotelPromotionBL;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Guo on 2016/12/6.
 */
public class PartnerHotelPromotion implements HotelPromotionBL {

    @Override
    public double countPrice(OrderVO orderVO, HotelPromotionVO hotelPromotionVO) {
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

        if(isFit(orderVO, hotelPromotionVO)) {
            return totalPrice * hotelPromotionVO.getDiscount();
        } else {
            return totalPrice;
        }
    }

    private boolean isFit(OrderVO orderVO, HotelPromotionVO hotelPromotionVO){
        String hotelName = hotelPromotionVO.getName();

        //获取该客户企业合作单位
        int customerID = orderVO.getCustomerID();
        CustomerPO customerPO = null;

        try {
            customerPO = RemoteHelper.getInstance().getCustomerDataService().getCustomer(customerID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        String companyName = null;
        if(customerPO != null) {
            companyName = customerPO.getCompanyName();
        }

        if(companyName == null || !hotelName.equals(companyName)) {
            return false;
        } else {
            return true;
        }
    }
}
