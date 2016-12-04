package group2.grade15.njuse;

import group2.grade15.njuse.data.creditdata.CreditDatabaseImpl;
import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.po.CreditPO;
import group2.grade15.njuse.utility.ChangeReason;
import group2.grade15.njuse.utility.ResultMessage;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;

/**
 * Created by dell on 2016/11/29.
 */
public class CreditDatabaseTest {
    CreditDatabaseImpl creditDatabase = null;

    @Before
    public void setUp() throws Exception {
        DatabaseInfo info = new DatabaseInfo("jdbc:postgresql://localhost/FirstDatabase", "postgres", "1997wyh");
        creditDatabase = new CreditDatabaseImpl(info);
    }

    @Test
    public void getHistoryTest() throws RemoteException {
        assertEquals(1, creditDatabase.getHistory(1).get(0).getCreditID());
    }

    @Test
    public void addTest() throws RemoteException {
        CreditPO creditPO = new CreditPO(1, 1, 1, 0, 300.00, null, ChangeReason.orderExecute);
        assertEquals(ResultMessage.SUCCESS, creditDatabase.add(creditPO));
    }

}