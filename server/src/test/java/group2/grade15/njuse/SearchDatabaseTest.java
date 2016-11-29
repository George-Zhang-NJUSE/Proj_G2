package group2.grade15.njuse;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.searchdata.SearchDatabaseImpl;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

/**
 * Created by dell on 2016/11/29.
 */
public class SearchDatabaseTest {
    SearchDatabaseImpl searchDatabase=null;

    @Before
    public void setUp() throws Exception {
        DatabaseInfo info=new DatabaseInfo("jdbc:postgresql://localhost/FirstDatabase","postgres","1997wyh");
        searchDatabase=new SearchDatabaseImpl(info);
    }

    @Test
    public void getProvinceTest() throws RemoteException{
        assertEquals("江苏省",searchDatabase.getProvince().get(0).getProvinceName());
    }

}