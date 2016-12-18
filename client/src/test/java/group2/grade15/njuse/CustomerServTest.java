package group2.grade15.njuse;

import group2.grade15.njuse.bl.customerbl.CustomerController;
import group2.grade15.njuse.blservice.CustomerServ;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CreditListVO;
import group2.grade15.njuse.vo.CustomerVO;
import org.junit.Before;
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class CustomerServTest {
    CustomerServ customer;

    CustomerVO customerVO;
    CreditListVO creditListVO;

    @Before
    public void setUp(){
        customer = new CustomerController();
        customerVO = null;
        creditListVO = null;
    }

    @Test
    public void addCustomer(CustomerVO newCustomerVO){
        assertEquals(customerVO, customer.addCustomer(newCustomerVO));
    }

    @Test
    public void getInfo(){
        assertEquals(customerVO, customer.getInfo(1));
    }

    @Test
    public void modifyInfo(CustomerVO customerVO){
        assertEquals(ResultMessage.SUCCESS, customer.modifyInfo(customerVO));
    }

    @Test
    public void getCreditHistory(int customerID){
        assertEquals(creditListVO, customer.getCreditHistory(1));
    }
}
