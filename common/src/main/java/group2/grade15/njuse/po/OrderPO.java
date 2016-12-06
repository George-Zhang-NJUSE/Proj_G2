package group2.grade15.njuse.po;

import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.RoomType;

import java.io.Serializable;
import java.sql.Date;


public class OrderPO implements Serializable {
    private int orderID;
    private int customerID;
    private int hotelID;
    private double amount;
    private Date checkInTime;
    private Date checkOutTime;
    private Date finalExecuteTime;
    private int roomSum;
    private RoomType type;
    private int numOfCustomer;
    private boolean haveChild;
    private OrderState state;

    public OrderPO(int orderID, int customerID, int hotelID,double amount, Date checkInTime, Date checkOutTime,
                   Date finalExecuteTime, int roomSum, RoomType type, int numOfCustomer, boolean haveChild, OrderState state) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.hotelID = hotelID;
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

    public Date getCheckInTime() {
        return checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public Date getFinalExecuteTime() {
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
}
