package group2.grade15.njuse.vo;

import group2.grade15.njuse.utility.ChangeReason;

import java.io.Serializable;
import java.util.Date;

public class CreditVO implements Serializable{
	private final int orderID;
	private final int creditLeft;
	private final int creditChange;
	private final Date time;
	private final ChangeReason reason;
	
	public CreditVO(int id,int creditLeft,int n,Date time,ChangeReason reason){
		this.orderID=id;
		this.creditLeft=creditLeft;
		this.creditChange=n;
		this.time=time;
		this.reason=reason;
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
