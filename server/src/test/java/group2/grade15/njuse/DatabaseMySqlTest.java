package group2.grade15.njuse;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * Created by dell on 2016/11/20.
 */
public class DatabaseMySqlTest {
    DatabaseMySql mySqlTest;

    @Before
    public void setUp() throws Exception {
        DatabaseInfo info=new DatabaseInfo("jdbc:postgresql://localhost/FirstDatabase","postgres","1997wyh");
        mySqlTest=new DatabaseMySql(info);
    }

    @Test
    public void initTest(){
        Connection c=mySqlTest.init();
        assertNotEquals(null,c);
    }


}