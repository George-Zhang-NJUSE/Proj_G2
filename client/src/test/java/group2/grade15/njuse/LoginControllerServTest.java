package group2.grade15.njuse;

import group2.grade15.njuse.bl.loginbl.CustomerLoginImpl;
import group2.grade15.njuse.bl.loginbl.HotelManagerLoginImpl;
import group2.grade15.njuse.bl.loginbl.WebAdminLoginImpl;
import group2.grade15.njuse.bl.loginbl.WebMarketerLoginImpl;
import group2.grade15.njuse.blservice.LoginControllerServ;
import group2.grade15.njuse.utility.ResultMessage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginControllerServTest {
    LoginControllerServ customerLoginImpl;
    LoginControllerServ hotelManagerLoginImpl;
    LoginControllerServ webMarketerLoginImpl;
    LoginControllerServ webAdminLoginImpl;

    @Before
    public void setUp(){
        customerLoginImpl = new CustomerLoginImpl();
        hotelManagerLoginImpl = new HotelManagerLoginImpl();
        webMarketerLoginImpl = new WebMarketerLoginImpl();
        webAdminLoginImpl = new WebAdminLoginImpl();
    }

    @Test
    public void testLogin1(){
        assertEquals(ResultMessage.SUCCESS, customerLoginImpl.login("1","a"));
    }

    @Test
    public void testLogin2(){
        assertEquals(ResultMessage.SUCCESS, hotelManagerLoginImpl.login("1","a"));
    }

    @Test
    public void testLogin3(){
        assertEquals(ResultMessage.SUCCESS, webMarketerLoginImpl.login("1","a"));
    }

    @Test
    public void testLogin4(){
        assertEquals(ResultMessage.SUCCESS, webAdminLoginImpl.login("1","a"));
    }
}
