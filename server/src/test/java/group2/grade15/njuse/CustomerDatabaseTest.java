package group2.grade15.njuse;

import group2.grade15.njuse.data.customerdata.CustomerDataBaseImpl;
import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.utility.MemberType;
import group2.grade15.njuse.utility.ResultMessage;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.sql.Date;

import static org.junit.Assert.*;

/**
 * Created by dell on 2016/11/20.
 */
public class CustomerDatabaseTest {
    CustomerDataBaseImpl customerDataBase;

    @Before
    public void setUp() throws Exception {
        DatabaseInfo info=new DatabaseInfo("jdbc:postgresql://localhost/FirstDatabase","postgres","1997wyh");
        customerDataBase=new CustomerDataBaseImpl(info);
    }

    @Test
    public void getTest() throws RemoteException{
        CustomerPO temp=customerDataBase.getCustomer(000000000);
        if(temp==null) {
            System.err.print("wrong");
        }
        assertEquals("12345", temp.getPassword());
    }

    @Test
    public void addTest() throws RemoteException{
        Date birthday=Date.valueOf("1993-01-21");
        CustomerPO input=new CustomerPO(0,"WuYuhan","123abc","17714363061",birthday,0.00, MemberType.normal);
        CustomerPO temp=customerDataBase.add(input);
        if(temp==null) {
            System.err.print("wrong");
        }
        assertEquals(100.00,temp.getCredit(),0.001);
    }

    @Test
    public void modifyTest() throws RemoteException{
        Date birthday=Date.valueOf("1993-01-21");
        CustomerPO input=new CustomerPO(0,"Iris","123abc","17714363061",birthday,100.00, MemberType.normal);
        assertEquals(ResultMessage.SUCCESS,customerDataBase.modify(input));
    }

}