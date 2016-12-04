package group2.grade15.njuse.data.webpromotiondata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.WebPromotionDataService;
import group2.grade15.njuse.po.RankPO;
import group2.grade15.njuse.po.WebPromotionPO;
import group2.grade15.njuse.utility.PromotionState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.WebPromotionType;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

;

public class WebPromotionDatabaseImpl implements WebPromotionDataService {
    private DatabaseMySql mySql = null;
    private Connection webPromotionDatabase = null;

    public WebPromotionDatabaseImpl(DatabaseInfo info) throws RemoteException {
        mySql = new DatabaseMySql(info);
        webPromotionDatabase = mySql.init();
    }

    @Override
    public ArrayList<WebPromotionPO> getList() throws RemoteException {
        if (webPromotionDatabase == null) {
            webPromotionDatabase = mySql.init();
        }

        try {
            Statement getInfo = webPromotionDatabase.createStatement();
            ResultSet resultSet = getInfo.executeQuery("select * from webpromotion");

            ArrayList<WebPromotionPO> list = new ArrayList<WebPromotionPO>();
            while (resultSet.next()) {
                java.util.Date now = new java.util.Date();
                Date current = new Date(now.getTime());

                int promotionID = resultSet.getInt(1);
                java.sql.Date start = resultSet.getDate(2);
                Date end = resultSet.getDate(3);
                String address = resultSet.getString(4);//未被翻译过的
                double discount = resultSet.getDouble(5);
                String name = resultSet.getString(6);
                int level = resultSet.getInt(7);
                PromotionState state = PromotionState.values()[resultSet.getInt(8)];
                WebPromotionType promotionType = WebPromotionType.values()[resultSet.getInt(9)];

                if (start.before(current) && state.equals(PromotionState.unlaunched)) {
                    state = PromotionState.start;
                    PreparedStatement updateState = webPromotionDatabase.prepareStatement("update webpromotion set " +
                            "promotionstate = ? where promotionid = ?");
                    updateState.setInt(1, state.ordinal());
                    updateState.setInt(2, promotionID);

                    updateState.executeUpdate();

                    updateState.close();
                } else if (end.before(current) && state.equals(PromotionState.start)) {
                    state = PromotionState.stop;
                    PreparedStatement updateState = webPromotionDatabase.prepareStatement("update webpromotion set " +
                            "promotionstate = ? where promotionid = ?");
                    updateState.setInt(1, state.ordinal());
                    updateState.setInt(2, promotionID);

                    updateState.executeUpdate();

                    updateState.close();
                }

                WebPromotionPO webMarketerPO = new WebPromotionPO(promotionID, promotionType, start, end, address, level, discount,
                        name, state);
                list.add(webMarketerPO);
            }

            getInfo.close();
            webPromotionDatabase.close();
            webPromotionDatabase = null;

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param po
     * @return ResultMessage
     * @throws RemoteException 一旦确定了促销类型不可更改
     */
    @Override
    public ResultMessage modify(WebPromotionPO po) throws RemoteException {
        if (webPromotionDatabase == null) {
            webPromotionDatabase = mySql.init();
        }

        try {
            PreparedStatement modify = webPromotionDatabase.prepareStatement("update webpromotion set " +
                    "starttime = ?,overtime = ?,address = ?,discount = ?,promotionname = ?,viplevel = ?,promotionstate = ?");
            modify.setDate(1, po.getStart());
            modify.setDate(2, po.getEnd());
            modify.setString(3, po.getAddress());
            modify.setDouble(4, po.getDiscount());
            modify.setString(5, po.getName());
            modify.setInt(6, po.getLevel());
            modify.setInt(7, po.getState().ordinal());

            modify.executeUpdate();

            modify.close();
            webPromotionDatabase.close();
            webPromotionDatabase = null;

            return ResultMessage.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ResultMessage remove(int promotionID) throws RemoteException {
        if (webPromotionDatabase == null) {
            webPromotionDatabase = mySql.init();
        }

        try {
            Statement remove = webPromotionDatabase.createStatement();
            remove.executeUpdate("delete from webpromotion where promotionid = " + promotionID);

            remove.close();
            webPromotionDatabase.close();
            webPromotionDatabase = null;
            return ResultMessage.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ResultMessage add(WebPromotionPO po) throws RemoteException {
        if (webPromotionDatabase == null) {
            webPromotionDatabase = mySql.init();
        }

        try {
            Statement makeID = webPromotionDatabase.createStatement();
            ResultSet resultSet = makeID.executeQuery("select max(promotionid) from webpromotion");
            int promotionID;
            if (resultSet.next()) {
                promotionID = resultSet.getInt(1) + 1;
            } else {
                throw new SQLException();
            }
            makeID.close();

            PreparedStatement add = webPromotionDatabase.prepareStatement("insert into webpromotion values(?,?,?,?,?,?,?,?,?)");
            add.setInt(1, promotionID);
            add.setDate(2, po.getStart());
            add.setDate(3, po.getEnd());
            add.setString(4, po.getAddress());
            add.setDouble(5, po.getDiscount());
            add.setString(6, po.getName());
            add.setInt(7, po.getLevel());
            add.setInt(8, po.getState().ordinal());
            add.setInt(9, po.getType().ordinal());

            add.executeUpdate();

            add.close();
            webPromotionDatabase.close();
            webPromotionDatabase = null;

            return ResultMessage.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ArrayList<RankPO> getRank() throws RemoteException {
        if (webPromotionDatabase == null) {
            webPromotionDatabase = mySql.init();
        }

        try {
            Statement getInfo = webPromotionDatabase.createStatement();
            ResultSet resultSet = getInfo.executeQuery("select * from rankpromotion");

            ArrayList<RankPO> list = new ArrayList<RankPO>();
            while (resultSet.next()) {
                int level = resultSet.getInt(1);
                double discount = resultSet.getDouble(2);
                int standard = resultSet.getInt(3);
                PromotionState state = PromotionState.values()[resultSet.getInt(4)];

                RankPO rankPO = new RankPO(level, standard, discount, state);
                list.add(rankPO);
            }

            getInfo.close();
            webPromotionDatabase.close();
            webPromotionDatabase = null;

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param rankPO
     * @return ResultMessage
     * @throws RemoteException 只能更改某一个等级的相关信息
     */
    @Override
    public ResultMessage modifyRank(RankPO rankPO) throws RemoteException {
        if (webPromotionDatabase == null) {
            webPromotionDatabase = mySql.init();
        }

        try {
            PreparedStatement modify = webPromotionDatabase.prepareStatement("update rankpromotion set " +
                    "discount = ?,standard = ?,state = ? where level = ?");
            modify.setDouble(1, rankPO.getDiscount());
            modify.setInt(2, rankPO.getStandard());
            modify.setInt(3, rankPO.getState().ordinal());
            modify.setInt(4, rankPO.getLevel());

            modify.executeUpdate();

            modify.close();
            webPromotionDatabase.close();
            webPromotionDatabase = null;

            return ResultMessage.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }
}
