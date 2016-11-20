package group2.grade15.njuse;

import group2.grade15.njuse.data.customerdata.CustomerDataBaseImpl;
import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.po.CustomerPO;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

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
        CustomerPO temp=customerDataBase.get(0);
        if(temp==null) {
            System.err.print("wrong");
        }
        assertEquals(temp.getPassword(),"12345");
    }

}