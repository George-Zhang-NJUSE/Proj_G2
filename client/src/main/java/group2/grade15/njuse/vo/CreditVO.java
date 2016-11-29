package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.CreditPO;
import group2.grade15.njuse.utility.ChangeReason;

import java.io.Serializable;
import java.util.Date;

public class CreditVO implements Serializable{
	private final int orderID;
	private final int creditLeft;
	private final int creditChange;
	private final Date time;
	private final ChangeReason reason;
	
	public CreditVO(CreditPO po){
		orderID = po.getOrderID();
		creditLeft = po.getCreditLeft();
		creditChange = po.getCreditChange();
		time = po.getTime();
		reason = po.getReason();
	}

	public int getOrderID() {
		return orderID;
	}

	public int getCreditLeft() {
		return creditLeft;
	}

	public int getCreditChange() {
		return creditChange;
	}

	public Date getTime() {
		return time;
	}

	public ChangeReason getReason() {
		return reason;
	}

}
