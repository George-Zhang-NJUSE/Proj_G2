package group2.grade15.njuse.po;

import group2.grade15.njuse.utility.ChangeReason;

import java.io.Serializable;
import java.sql.Date;


public class CreditPO implements Serializable {
    private final int customerID;
    private final int orderID;
    private final int creditID;
    private final double creditLeft;
    private final double creditChange;
    private final Date time;
    private final ChangeReason reason;


    public CreditPO(int customer, int id, int creditID, double creditLeft, double n, Date time, ChangeReason reason) {
        this.customerID = customer;
        this.orderID = id;
        this.creditID = creditID;
        this.creditLeft = creditLeft;
        this.creditChange = n;
        this.time = time;
        this.reason = reason;
    }

    public double getCreditChange() {
        return creditChange;
    }

    public Date getTime() {
        return time;
    }

    public ChangeReason getReason() {
        return reason;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public double getCreditLeft() {
        return creditLeft;
    }

    public int getCreditID() {
        return creditID;
    }
}
