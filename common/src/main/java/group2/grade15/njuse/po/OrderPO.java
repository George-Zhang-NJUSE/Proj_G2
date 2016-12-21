package group2.grade15.njuse.po;

import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.RoomType;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


public class OrderPO implements Serializable {
    private int orderID;
    private int customerID;
    private int hotelID;
    private int promotionID = 0;
    private double amount;
    private Timestamp createTime;
    private Timestamp checkInTime;
    private Timestamp checkOutTime;
    private Timestamp finalExecuteTime;
    private int roomSum;
    private RoomType type;
    private int numOfCustomer;
    private boolean haveChild;
    private OrderState state;

    public OrderPO(int orderID, int customerID, int hotelID, int promotionID, double amount, Timestamp createTime, Timestamp checkInTime,
                   Timestamp checkOutTime, Timestamp finalExecuteTime, int roomSum, RoomType type, int numOfCustomer,
                   boolean haveChild, OrderState state) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.hotelID = hotelID;
        this.promotionID = promotionID;
        this.amount = amount;
        this.createTime = createTime;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.finalExecuteTime = finalExecuteTime;
        this.roomSum = roomSum;
        this.type = type;
        this.numOfCustomer = numOfCustomer;
        this.haveChild = haveChild;
        this.state = state;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public double getAmount() {
        return amount;
    }

    public Timestamp getCheckInTime() {
        return checkInTime;
    }

    public Timestamp getCheckOutTime() {
        return checkOutTime;
    }

    public Timestamp getFinalExecuteTime() {
        return finalExecuteTime;
    }

    public int getRoomSum() {
        return roomSum;
    }

    public RoomType getType() {
        return type;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public int getPromotionID() {
        return promotionID;
    }
}
