package bl.orderbl;

import po.OrderPO;
import utility.OrderState;
import utility.ResultMessage;
import vo.CustomerVO;
import vo.HotelVO;
import vo.OrderVO;
import vo.RoomVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by George on 2016/11/6.
 */
public class Order {

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

    public Order(OrderPO po) {
        orderID = po.getOrderID();
        customerID = po.getCustomerID();
        hotelID = po.getHotelID();
        amount = po.getAmount();
        checkInTime = po.getCheckInTime();
        checkOutTime = po.getCheckOutTime();
        finalExecuteTime = po.getFinalExecuteTime();
//        selectRoom = po.getSelectRoom();
        numOfCustomer = po.getNumOfCustomer();
        haveChild = po.isHaveChild();
        state = po.getState();
    }

    public OrderVO getInfo(){
        return new OrderVO(orderID, customerID, hotelID, amount, checkInTime, checkOutTime, finalExecuteTime, selectRoom, numOfCustomer, haveChild, state);
    }

    public ResultMessage modifyState(OrderState state){
        return ResultMessage.SUCCESS;
    }

    public OrderPO createPO(){
        return null;
    }
}
