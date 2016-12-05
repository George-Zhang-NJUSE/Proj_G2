package group2.grade15.njuse;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.hotelmanagerdata.HotelManagerDatabaseImpl;
import group2.grade15.njuse.po.HotelManagerPO;
import group2.grade15.njuse.utility.ResultMessage;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;

/**
 * Created by dell on 2016/11/25.
 */
public class HotelManagerDatabaseTest {
    HotelManagerDatabaseImpl hotelManagerDatabase;

    @Before
    public void setUp() throws Exception {
        DatabaseInfo info = new DatabaseInfo("jdbc:postgresql://localhost/FirstDatabase", "postgres", "1997wyh");
        hotelManagerDatabase = new HotelManagerDatabaseImpl(info);
    }

    @Test
    public void getTest() throws RemoteException {
        HotelManagerPO hotelManagerPO = hotelManagerDatabase.getHotelManager(0);
        assertEquals("MissWu", hotelManagerPO.getName());
    }

    @Test
    public void modifyTest() throws RemoteException {
        HotelManagerPO hotelManagerPO = new HotelManagerPO(0, "123", "MissWu", "6883221", 0);
        assertEquals(ResultMessage.SUCCESS, hotelManagerDatabase.modify(hotelManagerPO));
    }

}
