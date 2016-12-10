package group2.grade15.njuse.data.hotelpromotiondata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.hotelpromotiondataservice.HotelPromotionDataService;
import group2.grade15.njuse.po.HotelPromotionPO;
import group2.grade15.njuse.utility.HotelPromotionType;
import group2.grade15.njuse.utility.PromotionState;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
public class HotelPromotionDatabaseImpl implements HotelPromotionDataService {
    DatabaseMySql mySql = null;
    Connection hotelPromotionDatabase = null;

    public HotelPromotionDatabaseImpl(DatabaseInfo info) throws RemoteException {
        mySql = new DatabaseMySql(info);
        hotelPromotionDatabase = mySql.init();
    }

    /**
     * @param hotelId
     * @return ArrayList<HotelPromotionPO>
     * @throws RemoteException 与时间相关的促销会自动启动,自动关闭
     */
    @Override
    public ArrayList<HotelPromotionPO> getList(int hotelId) throws RemoteException {
        if (hotelPromotionDatabase == null) {
            hotelPromotionDatabase = mySql.init();
        }

        try {
            Statement getInfo = hotelPromotionDatabase.createStatement();
            ResultSet resultSet = getInfo.executeQuery("select * from hotelpromotion where hotelid = " + hotelId);

            ArrayList<HotelPromotionPO> list = new ArrayList<HotelPromotionPO>();
            while (resultSet.next()) {
                java.util.Date now = new java.util.Date();
                Date current = new Date(now.getTime());

                int promotionID = resultSet.getInt(1);
                Date start = resultSet.getDate(2);
                Date end = resultSet.getDate(3);
                int vipID = resultSet.getInt(4);
                double discount = resultSet.getDouble(5);
                String name = resultSet.getString(6);
                PromotionState state = PromotionState.values()[resultSet.getInt(7)];
                HotelPromotionType promotionType = HotelPromotionType.values()[resultSet.getInt(8)];
                int hotelID = resultSet.getInt(9);

                if ((start!=null)&&start.before(current) && state.equals(PromotionState.unlaunched)) {
                    state = PromotionState.start;
                    PreparedStatement updateState = hotelPromotionDatabase.prepareStatement("update hotelpromotion set " +
                            "promotionstate = ? where promotionid = ?");
                    updateState.setInt(1, state.ordinal());
                    updateState.setInt(2, promotionID);

                    updateState.executeUpdate();

                    updateState.close();
                } else if ((end!=null)&&end.before(current) && state.equals(PromotionState.start)) {
                    state = PromotionState.stop;
                    PreparedStatement updateState = hotelPromotionDatabase.prepareStatement("update hotelpromotion set " +
                            "promotionstate = ? where promotionid = ?");
                    updateState.setInt(1, state.ordinal());
                    updateState.setInt(2, promotionID);

                    updateState.executeUpdate();

                    updateState.close();
                }

                HotelPromotionPO hotelPromotionPO = new HotelPromotionPO(promotionID, promotionType, start, end, vipID, discount, name, state, hotelID);
                list.add(hotelPromotionPO);
            }

            getInfo.close();
            hotelPromotionDatabase.close();
            hotelPromotionDatabase = null;

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
    public ResultMessage modify(HotelPromotionPO po) throws RemoteException {
        if (hotelPromotionDatabase == null) {
            hotelPromotionDatabase = mySql.init();
        }

        try {
            PreparedStatement modify = hotelPromotionDatabase.prepareStatement("update hotelpromotion set " +
                    "starttime = ?,overtime = ?,vipid = ?,discount = ?,promotionname = ?,promotionstate = ? " +
                    "where promotionid = ?");

            modify.setDate(1, po.getStart());
            modify.setDate(2, po.getEnd());
            modify.setInt(3, po.getVipID());
            modify.setDouble(4, po.getDiscount());
            modify.setString(5, po.getName());
            modify.setInt(6, po.getState().ordinal());
            modify.setInt(7, po.getPromotionID());

            modify.executeUpdate();

            modify.close();
            hotelPromotionDatabase.close();
            hotelPromotionDatabase = null;

            return ResultMessage.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ResultMessage remove(int promotionID) throws RemoteException {
        if (hotelPromotionDatabase == null) {
            hotelPromotionDatabase = mySql.init();
        }

        try {
            Statement remove = hotelPromotionDatabase.createStatement();
            remove.executeUpdate("delete from hotelpromotion where promotionid = " + promotionID);

            remove.close();
            hotelPromotionDatabase.close();
            hotelPromotionDatabase = null;
            return ResultMessage.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    @Override
    public ResultMessage add(HotelPromotionPO po) throws RemoteException {
        if (hotelPromotionDatabase == null) {
            hotelPromotionDatabase = mySql.init();
        }

        try {
            Statement makeID = hotelPromotionDatabase.createStatement();
            ResultSet resultSet = makeID.executeQuery("select max(promotionid) from hotelpromotion");
            int promotionID;
            if (resultSet.next()) {
                promotionID = resultSet.getInt(1) + 2;
            } else {
                throw new SQLException();
            }
            makeID.close();

            PreparedStatement add = hotelPromotionDatabase.prepareStatement("insert into hotelpromotion values(?,?,?,?,?,?,?,?,?)");
            add.setInt(1, promotionID);
            add.setDate(2, po.getStart());
            add.setDate(3, po.getEnd());
            add.setInt(4, po.getVipID());
            add.setDouble(5, po.getDiscount());
            add.setString(6, po.getName());
            add.setInt(7, po.getState().ordinal());
            add.setInt(8, po.getType().ordinal());
            add.setInt(9, po.getHotelID());

            add.executeUpdate();

            add.close();
            hotelPromotionDatabase.close();
            hotelPromotionDatabase = null;

            return ResultMessage.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }
}
