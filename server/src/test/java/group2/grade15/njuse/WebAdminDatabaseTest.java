package group2.grade15.njuse;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.webadmindata.WebAdminDatabaseImpl;
import group2.grade15.njuse.po.*;
import group2.grade15.njuse.utility.MemberType;
import group2.grade15.njuse.utility.ResultMessage;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by dell on 2016/11/25.
 */
public class WebAdminDatabaseTest {
    WebAdminDatabaseImpl webAdminDatabase;

    @Before
    public void setUp() throws Exception {
        DatabaseInfo info = new DatabaseInfo("jdbc:postgresql://localhost/FirstDatabase", "postgres", "1997wyh");
        webAdminDatabase = new WebAdminDatabaseImpl(info);
    }

    @Test
    public void getTest() throws RemoteException {
        WebAdminPO webAdminPO = webAdminDatabase.getWebAdmin("wa00000000");
        assertEquals("12345", webAdminPO.getPassword());
    }

    @Test
    public void getCustomerInfoTest() throws RemoteException {
        ArrayList<CustomerPO> customerList = webAdminDatabase.getCustomerInfo();
        assertEquals("Sue", customerList.get(0).getName());
    }

    @Test
    public void modifyCustomerInfoTest() throws RemoteException {
        Date birthday = Date.valueOf("1993-01-22");
        CustomerPO customerPO = new CustomerPO(0, "Sue", "12345", "6883221", birthday,
                0, MemberType.vip,null);
        assertEquals(ResultMessage.SUCCESS, webAdminDatabase.modifyCustomerInfo(customerPO));
    }

    @Test
    public void getHotelManagerInfoTest() throws RemoteException {
        ArrayList<HotelManagerPO> hotelManagerList = webAdminDatabase.getHotelManagerInfo();
        assertEquals("MissWu", hotelManagerList.get(0).getName());
    }

    @Test
    public void modifyHotelManagerInfoTest() throws RemoteException {
        HotelManagerPO hotelManagerPO = new HotelManagerPO(0, "12345", "MissWu", "6883221", 1);
        assertEquals(ResultMessage.SUCCESS, webAdminDatabase.modifyHotelManagerInfo(hotelManagerPO));
    }

    @Test
    public void addWebMarketerInfoTest() throws RemoteException {
        WebMarketerPO webMarketerPO = new WebMarketerPO("123abc", "wm00000001");
        assertEquals(ResultMessage.SUCCESS, webAdminDatabase.addWebMarketerInfo(webMarketerPO));
    }

    @Test
    public void getWebMarketerInfoTest() throws RemoteException {
        assertEquals(2, webAdminDatabase.getWebMarketerInfo().size());
    }

    @Test
    public void modifyWebMarketerInfoTest() throws RemoteException {
        WebMarketerPO webMarketerPO = new WebMarketerPO("1234a", "wm00000001");
        assertEquals(ResultMessage.SUCCESS, webAdminDatabase.modifyWebMarketerInfo(webMarketerPO));
    }

    @Test
    public void deleteWebMarketerInfoTest() throws RemoteException {
        assertEquals(ResultMessage.SUCCESS, webAdminDatabase.deleteWebMarketer("wm00000001"));
    }

    @Test
    public void addHotelinfoTest() throws RemoteException {
        HotelPO hotelPO = new HotelPO(0, "莫泰", "00001000010000100001", "江苏省南京市栖霞区马群",
                "6883221", null,
                "健身馆", null, 2, 0.00, null);
        assertEquals(2, webAdminDatabase.addHotel(hotelPO).getId());
    }

    @Test
    public void getHotelInfoTest() throws RemoteException {
        assertEquals(2, webAdminDatabase.getHotelInfo().size());
    }

    @Test
    public void modifyHotelInfoTest() throws RemoteException {
        HotelPO hotelPO = new HotelPO(2, "如家", "00001000010000100001", "江苏省南京市珠江路",
                "6883221", null,
                null, null, 2, 0.00, null);
        assertEquals(ResultMessage.SUCCESS, webAdminDatabase.modifyHotelInfo(hotelPO));
    }

    @Test
    public void deleteHotelInfoTest() throws RemoteException {
        assertEquals(ResultMessage.SUCCESS, webAdminDatabase.deleteHotelInfo(2));
    }

    @Test
    public void addHotelManagerTest() throws RemoteException {
        HotelManagerPO po = new HotelManagerPO(0, "111", "wang", "110", 1);
        assertEquals(2, webAdminDatabase.addHotelManagerInfo(po).getId());
    }
}