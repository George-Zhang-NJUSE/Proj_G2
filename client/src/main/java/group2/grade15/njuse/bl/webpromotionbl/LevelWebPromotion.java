package group2.grade15.njuse.bl.webpromotionbl;

import group2.grade15.njuse.bl.hotelbl.Hotel;
import group2.grade15.njuse.bl.hotelbl.HotelBL;
import group2.grade15.njuse.bl.promotionfactory.WebPromotionBL;
import group2.grade15.njuse.po.RankPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.HotelVO;
import group2.grade15.njuse.vo.OrderVO;
import group2.grade15.njuse.vo.RoomVO;
import group2.grade15.njuse.vo.WebPromotionVO;

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

    private boolean gerRankDiscount(double totalPrice, int customerRank){
        ArrayList<RankPO> rankPOList = null;
        try {
            rankPOList = RemoteHelper.getInstance().getWebPromotionDataService().getRank();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        int size = rankPOList.size();
        double[] discount = new double[size];
        double[] standard = new double[size];

        for(int i = 0; i < size; i ++) {
            discount[i] = rankPOList.get(i).getDiscount();
            standard[i] = rankPOList.get(i).getStandard();
        }

//        for()
        return true;
    }
}
