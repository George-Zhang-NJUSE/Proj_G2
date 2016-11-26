package group2.grade15.njuse;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.webadmindata.WebAdminDatabaseImpl;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.po.WebAdminPO;
import group2.grade15.njuse.utility.MemberType;
import group2.grade15.njuse.utility.ResultMessage;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.sql.Date;

import static org.junit.Assert.*;

/**
 * Created by dell on 2016/11/25.
 */
public class WebAdminDatabaseTest {
    WebAdminDatabaseImpl webAdminDatabase;

    @Before
    public void setUp() throws Exception {
        DatabaseInfo info=new DatabaseInfo("jdbc:postgresql://localhost/FirstDatabase","postgres","1997wyh");
        webAdminDatabase=new WebAdminDatabaseImpl(info);
    }

    @Test
    public void getTest() throws RemoteException{
        WebAdminPO webAdminPO=webAdminDatabase.get("wa00000000");
        assertEquals("12345",webAdminPO.getPassword());
    }

    @Test
    public void getCustomerInfoTest() throws RemoteException{
        CustomerPO customerPO=webAdminDatabase.getCustomerInfo(0);
        assertEquals("Sue",customerPO.getName());
    }

    @Test
    public void modifyCustomerInfoTest() throws RemoteException{
        Date birthday=Date.valueOf("1993-01-22");
        CustomerPO customerPO=new CustomerPO(0,"Sue","12345","6883221",birthday,0, MemberType.vip);
        assertEquals(ResultMessage.SUCCESS,webAdminDatabase.modifyCustomerInfo(customerPO));
    }


}