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

    @Test
    public void getCityTest() throws RemoteException{
        assertEquals("南京市",searchDatabase.getCity("00001").get(0).getCityName());
    }

   @Test
    public void getDistrictTest() throws RemoteException{
        assertEquals("栖霞区",searchDatabase.getDistrict("0000100001").get(0).getDistrictName());
   }

   @Test
    public void getCBDTest() throws RemoteException{
        assertEquals("仙林中心",searchDatabase.getCbd("000010000100001").get(0).getCbdName());
   }

}