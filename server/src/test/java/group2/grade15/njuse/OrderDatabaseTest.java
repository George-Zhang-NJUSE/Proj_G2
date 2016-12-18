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
import java.sql.Timestamp;

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
        assertEquals(1, orderDatabase.getListByCustomer(3).size());
    }

    @Test
    public void getListByHotelTest() throws Exception {
        assertEquals(2, orderDatabase.getListByHotel(1).size());
    }

    @Test
    public void getOrderTest() throws Exception {
        assertEquals(1, orderDatabase.getOrder(1).getRoomSum());
    }

    @Test
    public void roomTest() throws Exception{
        Timestamp checkIn=Timestamp.valueOf("2016-11-27 00:00:00");
        Timestamp checkOut=Timestamp.valueOf("2016-11-28 00:00:00");
        assertEquals(0,orderDatabase.roomToBeAvailable(checkIn,checkOut,RoomType.stadardDoubleBed,1));
    }

    @Test
    public void addOrderTest() throws Exception {
        Timestamp createTime=Timestamp.valueOf("2016-12-02 01:01:01");
        Timestamp checkIn = Timestamp.valueOf("2016-12-03 01:01:01");
        Timestamp checkOut = Timestamp.valueOf("2016-12-04 01:01:01");
        Timestamp execute = Timestamp.valueOf("2016-12-03 01:01:01");
        OrderPO orderPO = new OrderPO(0, 1, 1, 100, createTime,checkIn, checkOut,
                execute, 1, RoomType.stadardDoubleBed, 2, true, OrderState.unexecuted);
        assertEquals(ResultMessage.SUCCESS, orderDatabase.addOrder(orderPO));
    }

    @Test
    public void modifyOrderTest() throws Exception {
        assertEquals(ResultMessage.SUCCESS, orderDatabase.modifyOrder(2, OrderState.revoked));
    }

    @Test
    public void updateTimeTest() throws Exception{
        Timestamp checkIn = Timestamp.valueOf("2016-12-03 00:00:00");
        Timestamp checkOut = Timestamp.valueOf("2016-12-04 01:01:01");
        assertEquals(ResultMessage.SUCCESS,orderDatabase.updateTime(checkIn,checkOut,1));
    }

}