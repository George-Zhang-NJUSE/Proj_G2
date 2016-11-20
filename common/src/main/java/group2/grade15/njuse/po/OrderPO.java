package group2.grade15.njuse.po;

import group2.grade15.njuse.utility.OrderState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


public class OrderPO implements Serializable{
	private int orderID;
	private int customerID;
	private int hotelID;
	private int amount;
	private Date checkInTime;
	private Date checkOutTime;
	private Date finalExecuteTime;
	private ArrayList<RoomPO> selectRoom;
	private int numOfCustomer;
	private boolean haveChild;
	private OrderState state;
	
	public OrderPO(int orderID,int customer,int hotel,int amount,Date checkInTime,Date checkOutTime,Date finalExecuteTime,ArrayList<RoomPO> selectRoom,int numOfCustomer,boolean haveChild,OrderState state){
		this.orderID=orderID;
		this.customerID=customer;
		this.hotelID=hotel;
		this.amount=amount;
		this.checkInTime=checkInTime;
		this.checkOutTime=checkOutTime;
		this.finalExecuteTime=finalExecuteTime;
		this.selectRoom=selectRoom;
		this.numOfCustomer=numOfCustomer;
		this.haveChild=haveChild;
		this.state=state;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getHotelID() {
		return hotelID;
	}

	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}

	public int getOrderID() {
		return orderID;
	}

	public int getAmount() {
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

	public ArrayList<RoomPO> getSelectRoom() {
		return selectRoom;
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
