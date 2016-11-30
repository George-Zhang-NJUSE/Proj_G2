package group2.grade15.njuse.vo;

import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.po.RoomPO;
import group2.grade15.njuse.utility.OrderState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class OrderVO implements Serializable{
	private int orderID;
	private int customerID;
	private int hotelID;
	private int amount;
	private Date checkInTime;
	private Date checkOutTime;
	private Date finalExecuteTime;
	private ArrayList<RoomVO> selectRoom;
	private int numOfCustomer;
	private boolean haveChild;
	private OrderState state;
	
	public OrderVO(OrderPO po){
		orderID = po.getOrderID();
		customerID = po.getCustomerID();
		hotelID = po.getHotelID();
		amount = po.getAmount();
		checkInTime = po.getCheckInTime();
		checkOutTime = po.getCheckOutTime();
		finalExecuteTime = po.getFinalExecuteTime();
		numOfCustomer = po.getNumOfCustomer();
		haveChild = po.isHaveChild();
		state = po.getState();

		selectRoom = new ArrayList();
		ArrayList<RoomPO> roomPOList = po.getSelectRoom();
		for(RoomPO roomPO : roomPOList){
			RoomVO room = new RoomVO(roomPO);
			selectRoom.add(room);
		}
	}

	public OrderVO(int orderID, int customerID, int hotelID, int amount, Date checkInTime, Date checkOutTime, Date finalExecuteTime, ArrayList<RoomVO> selectRoom, int numOfCustomer, boolean haveChild, OrderState state){
        this.orderID = orderID;
        this.customerID = customerID;
        this.hotelID = hotelID;
        this.amount = amount;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.finalExecuteTime = finalExecuteTime;
        this.numOfCustomer = numOfCustomer;
        this.haveChild = haveChild;
        this.state = state;
        this.selectRoom = selectRoom;
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

	public ArrayList<RoomVO> getSelectRoom() {
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

	public OrderPO toPO(){
		ArrayList<RoomPO> selectPOList = new ArrayList();
		for(RoomVO vo : selectRoom){
			selectPOList.add(vo.toPO());
		}
		return new OrderPO(orderID, customerID, hotelID, amount, checkInTime, checkOutTime, finalExecuteTime, selectPOList, numOfCustomer, haveChild, state);
	}
}
