package group2.grade15.njuse;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.hoteldata.HotelDatabaseImpl;
import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.utility.ResultMessage;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

/**
 * Created by dell on 2016/12/1.
 */
public class HotelDatabaseTest {
    HotelDatabaseImpl hotelDatabase;

    @Before
    public void setUp() throws Exception {
        DatabaseInfo info=new DatabaseInfo("jdbc:postgresql://localhost/FirstDatabase","postgres","1997wyh");
        hotelDatabase=new HotelDatabaseImpl(info);
    }

    @Test
    public void getHotelTest() throws RemoteException{
        assertEquals(90,hotelDatabase.getHotel(1).getScore(),0.001);
    }

    @Test
    public void modifyTest() throws RemoteException{
        HotelPO hotelPO=new HotelPO(1,null,"00001000010000100001","江苏省南京市玄武区",
                "6123456",null,null,null,5,0.00,null);
        assertEquals(ResultMessage.SUCCESS,hotelDatabase.modify(hotelPO));
    }
}