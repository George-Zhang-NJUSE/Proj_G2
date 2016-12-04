package group2.grade15.njuse;

import group2.grade15.njuse.data.commentdata.CommentDatabaseImpl;
import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.po.CommentPO;
import group2.grade15.njuse.utility.ResultMessage;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.assertEquals;

/**
 * Created by dell on 2016/11/27.
 */
public class CommentDatabaseTest {
    CommentDatabaseImpl commentDatabase = null;

    @Before
    public void setUp() throws Exception {
        DatabaseInfo info = new DatabaseInfo("jdbc:postgresql://localhost/FirstDatabase", "postgres", "1997wyh");
        commentDatabase = new CommentDatabaseImpl(info);
    }

    @Test
    public void getHotelCommentTest() throws RemoteException {
        assertEquals(1, commentDatabase.getHotelComments(1).get(0).getCommentID());
    }

    @Test
    public void getCustomerCommentTest() throws RemoteException {
        assertEquals(1, commentDatabase.getCustomerComments(1).get(0).getHotelID());
    }

    @Test
    public void addTest() throws RemoteException {
        CommentPO commentPO = new CommentPO(1, 1, null, "Bad!", 0, 50.5, 2);
        assertEquals(ResultMessage.SUCCESS, commentDatabase.add(commentPO));
    }

    @Test
    public void modifyTest() throws RemoteException {
        CommentPO commentPO = new CommentPO(1, 1, null, "Just so so", 2, 50.5, 2);
        assertEquals(ResultMessage.SUCCESS, commentDatabase.modify(commentPO));
    }
}