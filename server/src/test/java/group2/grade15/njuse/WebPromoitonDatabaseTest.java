package group2.grade15.njuse;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.webpromotiondata.WebPromotionDatabaseImpl;
import group2.grade15.njuse.po.RankPO;
import group2.grade15.njuse.po.WebPromotionPO;
import group2.grade15.njuse.utility.PromotionState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.WebPromotionType;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.sql.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by dell on 2016/12/4.
 */
public class WebPromoitonDatabaseTest {
    WebPromotionDatabaseImpl webPromotionDatabase;

    @Before
    public void setUp() throws Exception {
        DatabaseInfo info = new DatabaseInfo("jdbc:postgresql://localhost/FirstDatabase", "postgres", "1997wyh");
        webPromotionDatabase = new WebPromotionDatabaseImpl(info);
    }

    @Test
    public void getListTest() throws RemoteException {
        assertEquals("回馈顾客", webPromotionDatabase.getList().get(0).getName());
    }

    @Test
    public void modifyTest() throws RemoteException {
        Date start = Date.valueOf("2016-12-05");
        Date end = Date.valueOf("2016-12-06");
        WebPromotionPO webPromotionPO = new WebPromotionPO(1, null, start, end, null, 0,
                0.2, "回馈顾客", PromotionState.stop);
        assertEquals(ResultMessage.SUCCESS, webPromotionDatabase.modify(webPromotionPO));
    }

    @Test
    public void removeTest() throws RemoteException {
        assertEquals(ResultMessage.SUCCESS, webPromotionDatabase.remove(2));
    }

    @Test
    public void addTest() throws RemoteException {
        WebPromotionPO webPromotionPO = new WebPromotionPO(0, WebPromotionType.AreaWeb, null, null,
                "00001000010000100001", 6, 0.2, "回馈顾客", PromotionState.unlaunched);
        assertEquals(ResultMessage.SUCCESS, webPromotionDatabase.add(webPromotionPO));
    }

    @Test
    public void getRankTest() throws RemoteException {
        assertEquals(1, webPromotionDatabase.getRank().get(0).getLevel());
    }

    @Test
    public void modifyRankTest() throws RemoteException {
        RankPO rankPO = new RankPO(1, 200, 0.01, PromotionState.start);
        assertEquals(ResultMessage.SUCCESS, webPromotionDatabase.modifyRank(rankPO));
    }

}