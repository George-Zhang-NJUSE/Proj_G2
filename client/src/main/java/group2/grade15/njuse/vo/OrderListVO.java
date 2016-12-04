package group2.grade15.njuse.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderListVO implements Serializable {
    private ArrayList<OrderVO> orderList;

    public OrderListVO(ArrayList<OrderVO> orderList) {
        this.orderList = orderList;
    }

    public ArrayList<OrderVO> getOrderList() {
        return orderList;
    }
}
