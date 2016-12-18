package group2.grade15.njuse;

import group2.grade15.njuse.bl.webadminbl.WebAdminController;
import group2.grade15.njuse.blservice.WebAdminServ;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WebAdminServTest {
    WebAdminServ webAdmin;

    HotelVO hotelVO;
    HotelListVO hotelListVO;
    CustomerListVO customerListVO;
    HotelManagerListVO hotelManagerListVO;
    WebMarketerListVO webMarketerListVO;
    HotelManagerVO hotelManagerVO;
    WebMarketerVO webMarketerVO;

    @Before
    public void setUp(){
        webAdmin = new WebAdminController();
        hotelVO = null;
        hotelListVO = null;
        customerListVO = null;
        hotelManagerListVO = null;
        webMarketerListVO = null;
        hotelManagerVO = null;
        webMarketerVO = null;
    }

    @Test
    public void testCreateHotel() {
        assertEquals(ResultMessage.SUCCESS, webAdmin.createHotel(hotelVO));
    }

    @Test
    public void testGetHotelList(){
        assertEquals(hotelListVO, webAdmin.getHotelList());
    }

    @Test
    public void testModifyHotel() {
        assertEquals(hotelListVO, webAdmin.modifyHotel(hotelVO));
    }

    @Test
    public void testDeleteHotel(){
        assertEquals(ResultMessage.SUCCESS, webAdmin.deleteHotel(hotelVO));
    }

    @Test
    public void testGetCustomerList() {
        assertEquals(customerListVO, webAdmin.getCustomerList());
    }

    @Test
    public void getHotelManagerList() {
        assertEquals(hotelManagerListVO, webAdmin.getHotelManagerList());
    }

    @Test
    public void getWebMarketerList() {
        assertEquals(webMarketerListVO, webAdmin.getWebMarketerList());
    }

    @Test
    public void createHotelManager() {
        assertEquals(ResultMessage.SUCCESS, webAdmin.createHotelManager(hotelManagerVO));
    }

    @Test
    public void modifyWebMarketer() {
        assertEquals(ResultMessage.SUCCESS, webAdmin.modifyWebMarketer(webMarketerVO));
    }

    @Test
    public void createWebMarketer(WebMarketerVO webMarketer) {
        assertEquals(ResultMessage.SUCCESS, webAdmin.createWebMarketer(webMarketer));
    }

    @Test
    public void deleteWebMarketer(){
        assertEquals(ResultMessage.SUCCESS, webAdmin.deleteHotel(hotelVO));
    }
}
