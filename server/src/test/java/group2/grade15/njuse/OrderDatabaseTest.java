package group2.grade15.njuse;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.orderdata.OrderDatabaseImpl;
import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by dell on 2016/12/3.
 */
public class OrderDatabaseTest {
    OrderDatabaseImpl orderDatabase;

    @Before
    public void setUp() throws Exception {
        DatabaseInfo info = new DatabaseInfo("jdbc:postgresql://localhost/FirstDatabase", "postgres", "1997wyh");
        orderDatabase = new OrderDatabaseImpl(info);
    }

    @Test
    public void getUnexecutedListTest() throws Exception {
        assertEquals(0, orderDatabase.getUnexecutedList().size());
    }

    @Test
    public void getListByCustomerTest() throws Exception {
        assertEquals(2, orderDatabase.getListByCustomer(1).size());
    }

    @Test
    public void getListByHotelTest() throws Exception {
        assertEquals(2, orderDatabase.getListByHotel(1).size());
    }

    @Test
    public void getOrderTest() throws Exception {
        assertEquals(1, orderDatabase.getOrder(1).getCustomerID());
    }

    @Test
    public void roomTest() throws Exception{
        Date checkIn=Date.valueOf("2016-11-27");
        Date checkOut=Date.valueOf("2016-11-28");
        assertEquals(-1,orderDatabase.roomToBeAvailable(checkIn,checkOut,RoomType.stadardDoubleBed,1));
    }

    @Test
    public void addOrderTest() throws Exception {
        Date checkIn = Date.valueOf("2016-12-03");
        Date checkOut = Date.valueOf("2016-12-04");
        Date execute = Date.valueOf("2016-12-03");
        OrderPO orderPO = new OrderPO(0, 1, 1, 100, checkIn, checkOut, execute, 1,
                RoomType.stadardDoubleBed, 2, true, OrderState.unexecuted);
        assertEquals(ResultMessage.SUCCESS, orderDatabase.addOrder(orderPO));
    }

    @Test
    public void modifyOrderTest() throws Exception {
        assertEquals(ResultMessage.SUCCESS, orderDatabase.modifyOrder(2, OrderState.revoked));
    }

}