package group2.grade15.njuse;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.webmarketerdata.WebMarketerDatabaseImpl;
import group2.grade15.njuse.po.WebMarketerPO;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

/**
 * Created by dell on 2016/11/25.
 */
public class WebMarketerDatabaseTest {
    WebMarketerDatabaseImpl webMarketerDatabase=null;

    @Before
    public void setUp() throws Exception {
        DatabaseInfo info=new DatabaseInfo("jdbc:postgresql://localhost/FirstDatabase","postgres","1997wyh");
        webMarketerDatabase=new WebMarketerDatabaseImpl(info);
    }

    @Test
    public void getTest() throws RemoteException{
        WebMarketerPO webMarketerPO=webMarketerDatabase.get("wm00000000");
        assertEquals("12345",webMarketerPO.getPassword());
    }


}