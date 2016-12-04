package group2.grade15.njuse.data.hotelpromotiondata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.HotelPromotionDataService;
import group2.grade15.njuse.po.HotelPromotionPO;
import group2.grade15.njuse.utility.HotelPromotionType;
import group2.grade15.njuse.utility.PromotionState;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
public class HotelPromotionDatabaseImpl implements HotelPromotionDataService {
    DatabaseMySql mySql=null;
    Connection hotelPromotionDatabase=null;

    public HotelPromotionDatabaseImpl(DatabaseInfo info){
        mySql=new DatabaseMySql(info);
        hotelPromotionDatabase=mySql.init();
    }

    /**
     * @param hotelId
     * @return ArrayList<HotelPromotionPO>
     * @throws RemoteException
     * 与时间相关的促销会自动启动,自动关闭
     */
    @Override
    public ArrayList<HotelPromotionPO> getList(int hotelId) throws RemoteException {
        if(hotelPromotionDatabase==null){
            hotelPromotionDatabase=mySql.init();
        }

        try{
            Statement getInfo=hotelPromotionDatabase.createStatement();
            ResultSet resultSet=getInfo.executeQuery("select * from hotelpromotion where hotelid = "+hotelId);

            ArrayList<HotelPromotionPO> list=new ArrayList<HotelPromotionPO>();
            while(resultSet.next()){
                java.util.Date now=new java.util.Date();
                Date current=new Date(now.getTime());

                int promotionID=resultSet.getInt(1);
                HotelPromotionType promotionType=HotelPromotionType.values()[resultSet.getInt(2)];
                Date start=resultSet.getDate(3);
                Date end=resultSet.getDate(4);
                int vipID=resultSet.getInt(5);
                double discount=resultSet.getDouble(6);
                String name=resultSet.getString(7);
                PromotionState state=PromotionState.values()[resultSet.getInt(8)];

                if(start.after(current)&&state.equals(PromotionState.unlaunched)){
                    state=PromotionState.start;
                    PreparedStatement updateState=hotelPromotionDatabase.prepareStatement("update hotelpromotion set " +
                            "state = ? where hotelid = ?");
                    updateState.setInt(1,state.ordinal());
                    updateState.setInt(2,hotelId);

                    updateState.executeUpdate();

                    updateState.close();
                }

                HotelPromotionPO hotelPromotionPO=new HotelPromotionPO(promotionID,promotionType,start,end,vipID,discount,name,state);
                list.add(hotelPromotionPO);
            }

            return list;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultMessage modify(HotelPromotionPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage remove(HotelPromotionPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage add(HotelPromotionPO po) throws RemoteException {
        return null;
    }
}
