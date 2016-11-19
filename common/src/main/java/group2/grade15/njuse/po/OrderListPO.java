package group2.grade15.njuse.po;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderListPO implements Serializable{
	private ArrayList<OrderPO> orderList;
	public OrderListPO(ArrayList<OrderPO> orderList){
		orderList=orderList;
	}
	public ArrayList<OrderPO> getOrderList() {
		return orderList;
	}
}
