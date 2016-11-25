package group2.grade15.njuse;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.webadmindata.WebAdminDatabaseImpl;
import group2.grade15.njuse.po.WebAdminPO;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

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

}