package group2.grade15.njuse;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by dell on 2016/11/20.
 */
public class DatabaseMySqlTest {
    DatabaseMySql mySqlTest;
    Connection c;

    @Before
    public void setUp() throws Exception {
        DatabaseInfo info = new DatabaseInfo("jdbc:postgresql://localhost:5432/FirstDatabase", "postgres", "1997wyh");
        mySqlTest = new DatabaseMySql(info);
    }

    @Test
    public void initTest() {
        c = mySqlTest.init();
        assertNotEquals(null, c);
    }

    @After
    public void afterTest() throws SQLException {
        if (c != null) {
            c.close();
        }
    }

}