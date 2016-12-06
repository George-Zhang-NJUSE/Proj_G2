package group2.grade15.njuse;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.hotelpromotiondata.HotelPromotionDatabaseImpl;
import group2.grade15.njuse.po.HotelPromotionPO;
import group2.grade15.njuse.utility.HotelPromotionType;
import group2.grade15.njuse.utility.PromotionState;
import group2.grade15.njuse.utility.ResultMessage;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by dell on 2016/12/4.
 */
public class HotelPromotionDatabaseTest {
    HotelPromotionDatabaseImpl hotelPromotionDatabase = null;

    @Before
    public void setUp() throws Exception {
        DatabaseInfo info = new DatabaseInfo("jdbc:postgresql://localhost/FirstDatabase", "postgres", "1997wyh");
        hotelPromotionDatabase = new HotelPromotionDatabaseImpl(info);
    }

    @Test
    public void getList() throws Exception {
        assertEquals(0.4, hotelPromotionDatabase.getList(1).get(0).getDiscount(), 0.001);
    }

    @Test
    public void modify() throws Exception {
        Date start = Date.valueOf("2016-12-05");
        Date end = Date.valueOf("2016-12-07");
        HotelPromotionPO hotelPromotionPO = new HotelPromotionPO(1, null, start, end, 0, 0.4,
                "回馈熟客", PromotionState.unlaunched, 0);
        assertEquals(ResultMessage.SUCCESS, hotelPromotionDatabase.modify(hotelPromotionPO));
    }

    @Test
    public void remove() throws Exception {
        assertEquals(ResultMessage.SUCCESS, hotelPromotionDatabase.remove(2));
    }

    @Test
    public void add() throws Exception {
        HotelPromotionPO hotelPromotionPO = new HotelPromotionPO(0, HotelPromotionType.BirthdayHotel,
                null, null, 0, 0.1, "生日特惠", PromotionState.unlaunched, 1);
        assertEquals(ResultMessage.SUCCESS, hotelPromotionDatabase.add(hotelPromotionPO));
    }

}