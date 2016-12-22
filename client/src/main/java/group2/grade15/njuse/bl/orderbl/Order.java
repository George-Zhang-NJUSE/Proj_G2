package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.bl.customerbl.Customer;
import group2.grade15.njuse.bl.customerbl.CustomerBL;
import group2.grade15.njuse.bl.hotelbl.Hotel;
import group2.grade15.njuse.bl.hotelbl.HotelBL;
import group2.grade15.njuse.bl.hotelpromotionbl.HotelPromotionController;
import group2.grade15.njuse.bl.hotelpromotionbl.HotelPromotionControllerBL;
import group2.grade15.njuse.bl.promotionfactory.HotelPromotionBL;
import group2.grade15.njuse.bl.promotionfactory.HotelPromotionFactory;
import group2.grade15.njuse.bl.promotionfactory.WebPromotionBL;
import group2.grade15.njuse.bl.promotionfactory.WebPromotionFactory;
import group2.grade15.njuse.bl.webpromotionbl.WebPromotionController;
import group2.grade15.njuse.bl.webpromotionbl.WebPromotionControllerBL;
import group2.grade15.njuse.cache.CacheManager;
import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.PromotionState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Order implements OrderBL{

    private CustomerBL customer;
    private WebPromotionControllerBL webPromotionController;
    private HotelPromotionControllerBL hotelPromotionController;

    public Order(){
        customer = new Customer();
        webPromotionController = new WebPromotionController();
        hotelPromotionController = new HotelPromotionController();
    }

    public OrderVO getInfo(int orderID) {
        OrderPO po = null;

        try {
            po = RemoteHelper.getInstance().getOrderDataService().getOrder(orderID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (po != null) {
            return new OrderVO(po);
        } else {
            return null;
        }
    }

    public ResultMessage modifyState(int orderID, OrderState state) {
        try {
            CacheManager.getInstance().clearAll();
            return RemoteHelper.getInstance().getOrderDataService().modifyOrder(orderID, state);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.CONNECTION_EXCEPTION;
        }
    }

    public ResultMessage savePO(OrderVO order) {
        int ID = order.getCustomerID();
        double credit = customer.getInfo(ID).getCredit();

        if(credit > 0) {
            try {
                CacheManager.getInstance().clearAll();
                return RemoteHelper.getInstance().getOrderDataService().addOrder(order.toPO());
            } catch (RemoteException e) {
                e.printStackTrace();
                return ResultMessage.CONNECTION_EXCEPTION;
            }
        } else {
            return ResultMessage.ILLEGAL;
        }
    }

    public OrderVO createPO(OrderVO orderVO){
        double webInfo[];
        double hotelInfo[];
        double minPrice;
        int usedPromotionID;

        //优惠策略的计算
        webInfo = getPriceWithWebPro(orderVO);
        hotelInfo = getPriceWithHotelPro(orderVO);

        if(webInfo[0] <= hotelInfo[0]){
            minPrice = webInfo[0];
            usedPromotionID = (int) webInfo[1];
        } else {
            minPrice = hotelInfo[0];
            usedPromotionID = (int) hotelInfo[1];
        }

        return new OrderVO(orderVO, minPrice, usedPromotionID);
    }

    public double getOriginalPrice(OrderVO orderVO){
        int roomNum = orderVO.getRoomSum();
        int hotelID = orderVO.getHotelID();
        RoomType roomType = orderVO.getType();

        //计算入住天数
        Timestamp checkIn = orderVO.getCheckInTime();
        Timestamp checkOut = orderVO.getCheckOutTime();
        int seconds = (60 * 60 * 24 * 1000);
        long stayDays = 0;
        long sub = (checkOut.getTime() - checkIn.getTime());
        if(sub < seconds)
        {
            stayDays = 1;
        }
        else if(sub % seconds == 0) {
            stayDays = sub / seconds;
        } else {
            stayDays = sub / seconds + 1;
        }

        HotelBL hotel = new Hotel();
        double roomPrice = -1;

        HotelVO hotelVO = hotel.getInfo(hotelID);
        ArrayList<RoomVO> roomList = hotelVO.getRoomList();
        for (RoomVO room : roomList) {
            if (room.getType() == roomType) {
                roomPrice = room.getPrice();
            }
        }

        double originalPrice = roomPrice * roomNum * stayDays;

        return originalPrice;
    }

    @Override
    public ResultMessage updateTime(Timestamp checkIn, Timestamp checkOut, int orderID) {
        ResultMessage result;
        try {
            result = RemoteHelper.getInstance().getOrderDataService().updateTime(checkIn, checkOut, orderID);
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.CONNECTION_EXCEPTION;
        }

        return result;
    }

    private double[] getPriceWithWebPro(OrderVO orderVO){
        double[] info = new double[2]; //0中存储minPrice，1中存储usedPromotion
        double minPrice = getOriginalPrice(orderVO);
        int usedPromotionID = 0;

        //获取优惠策略的列表
        WebPromotionListVO webPromotionListVO = webPromotionController.getWebPromotionList();

        //对优惠策略列表里的策略进行遍历，依次与原价相乘，取最小的优惠值和优惠策略ID
        if(webPromotionListVO != null) {
            ArrayList<WebPromotionVO> webPromotionList = webPromotionListVO.getWebPromotionList();
            for (WebPromotionVO webPromotionVO : webPromotionList) {
                String promotionType = webPromotionVO.getType().toString();
                WebPromotionBL webPromotion = WebPromotionFactory.getWebPromotion(promotionType);

                boolean isMin = webPromotionVO.getState() == PromotionState.start
                        && webPromotion.countPrice(orderVO, webPromotionVO) < minPrice;

                if (isMin) {
                    minPrice = webPromotion.countPrice(orderVO, webPromotionVO);
                    usedPromotionID = webPromotionVO.getPromotionID();
                }
            }
        }

        info[0] = minPrice;
        info[1] = usedPromotionID;
        return info;
    }

    private double[] getPriceWithHotelPro(OrderVO orderVO){
        double[] info = new double[2]; //0中存储minPrice，1中存储usedPromotion
        int hotelID = orderVO.getHotelID();
        double minPrice = getOriginalPrice(orderVO);
        int usedPromotionID = 0;

        //获取优惠策略的列表
        HotelPromotionListVO hotelPromotionListVO = hotelPromotionController.getHotelPromotionList(hotelID);

        //对优惠策略列表里的策略进行遍历，依次与原价相乘，取最小的优惠值和优惠策略ID
        if(hotelPromotionListVO != null) {
            ArrayList<HotelPromotionVO> hotelPromotionList = hotelPromotionListVO.getHotelPromotionList();
            for (HotelPromotionVO hotelPromotionVO : hotelPromotionList) {
                String promotionType = hotelPromotionVO.getType().toString();
                HotelPromotionBL hotelPromotion = HotelPromotionFactory.getHotelPromotion(promotionType);

                boolean isMin = hotelPromotionVO.getState() == PromotionState.start
                        && hotelPromotion.countPrice(orderVO, hotelPromotionVO) < minPrice;

                if (isMin) {
                    minPrice = hotelPromotion.countPrice(orderVO, hotelPromotionVO);
                    usedPromotionID = hotelPromotionVO.getPromotionID();
                }
            }
        }

        info[0] = minPrice;
        info[1] = usedPromotionID;
        return info;
    }
}
