package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.RoomType;

import java.io.Serializable;
import java.sql.Date;

public class OrderVO implements Serializable {
    private int orderID;
    private int customerID;
    private int hotelID;
    private int promotionID;                   //该订单采取的优惠策略，初次创建时从界面层传到逻辑层时请设为0，逻辑层返回时会设置好优惠策略的ID
    private double amount;
    private Date checkInTime;
    private Date checkOutTime;
    private Date finalExecuteTime;
    private int roomSum;
    private RoomType type;
    private int numOfCustomer;
    private boolean haveChild;
    private OrderState state;

    public OrderVO(OrderPO po) {
        orderID = po.getOrderID();
        customerID = po.getCustomerID();
        hotelID = po.getHotelID();
        promotionID = 0;
        amount = po.getAmount();
        checkInTime = po.getCheckInTime();
        checkOutTime = po.getCheckOutTime();
        finalExecuteTime = po.getFinalExecuteTime();
        roomSum = po.getRoomSum();
        type = po.getType();
        numOfCustomer = po.getNumOfCustomer();
        haveChild = po.isHaveChild();
        state = po.getState();
    }

    //专用于在更新订单价格
    public OrderVO(OrderVO vo, double newAmount, int useDpromotionID) {
        orderID = vo.getOrderID();
        customerID = vo.getCustomerID();
        hotelID = vo.getHotelID();
        promotionID = useDpromotionID;
        amount = newAmount;
        checkInTime = vo.getCheckInTime();
        checkOutTime = vo.getCheckOutTime();
        finalExecuteTime = vo.getFinalExecuteTime();
        roomSum = vo.getRoomSum();
        type = vo.getType();
        numOfCustomer = vo.getNumOfCustomer();
        haveChild = vo.isHaveChild();
        state = vo.getState();
    }

    public OrderVO(int orderID, int customerID, int hotelID, int promotionID, int amount, Date checkInTime, Date checkOutTime, Date finalExecuteTime, int roomSum, RoomType type, int numOfCustomer, boolean haveChild, OrderState state) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.hotelID = hotelID;
        this.promotionID = promotionID;
        this.amount = amount;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.finalExecuteTime = finalExecuteTime;
        this.roomSum = roomSum;
        this.type = type;
        this.numOfCustomer = numOfCustomer;
        this.haveChild = haveChild;
        this.state = state;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public int getOrderID() {
        return orderID;
    }

    public double getAmount() {
        return amount;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public Date getFinalExecuteTime() {
        return finalExecuteTime;
    }

    public int getNumOfCustomer() {
        return numOfCustomer;
    }

    public boolean isHaveChild() {
        return haveChild;
    }

    public OrderState getState() {
        return state;
    }

    public int getRoomSum() {
        return roomSum;
    }

    public RoomType getType() {
        return type;
    }

    public OrderPO toPO() {
        return new OrderPO(orderID, customerID, hotelID, amount, checkInTime, checkOutTime, finalExecuteTime, roomSum, type, numOfCustomer, haveChild, state);
    }

    public int getPromotionID() {
        return promotionID;
    }
}
