package group2.grade15.njuse;

import group2.grade15.njuse.data.commentdata.CommentDatabaseImpl;
import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;

import static org.junit.Assert.*;

/**
 * Created by dell on 2016/11/27.
 */
public class CommentDatabaseTest {
    CommentDatabaseImpl commentDatabase=null;

    @Before
    public void setUp() throws Exception {
        DatabaseInfo info=new DatabaseInfo("jdbc:postgresql://localhost/FirstDatabase","postgres","1997wyh");
        commentDatabase=new CommentDatabaseImpl(info);
    }

    @Test
    public void getHotelCommentTest() throws RemoteException{
        assertEquals(1,commentDatabase.getHotelComments(1).get(0).getCommentID());
    }

}