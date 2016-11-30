package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.CreditPO;
import group2.grade15.njuse.utility.ChangeReason;

import java.io.Serializable;
import java.util.Date;

public class CreditVO implements Serializable{
    private final int customerID;
    private final int orderID;
    private final int creditID;
    private final double creditLeft;
    private final double creditChange;
    private final java.sql.Date time;
    private final ChangeReason reason;
	
	public CreditVO(CreditPO po){
        customerID = po.getCustomerID();
		orderID = po.getOrderID();
        creditID = po.getCreditID();
		creditLeft = po.getCreditLeft();
		creditChange = po.getCreditChange();
		time = po.getTime();
		reason = po.getReason();
	}

	public int getOrderID() {
		return orderID;
	}

	public double getCreditLeft() {
		return creditLeft;
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

	public CreditPO toPO(){
		return new CreditPO(customerID, orderID, creditID, creditLeft, creditChange, time, reason);
	}
}
