package group2.grade15.njuse.data.orderdata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.OrderDataService;
import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.utility.RoomType;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by George on 2016/11/13.
 */
public class OrderDatabaseImpl implements OrderDataService {
    private DatabaseMySql mySql=null;
    private Connection orderDatabase=null;

    public OrderDatabaseImpl(DatabaseInfo info) throws RemoteException{
        mySql=new DatabaseMySql(info);
        orderDatabase=mySql.init();
    }

    /**
     * @return
     * @throws RemoteException
     * 适用于网站营销人员
     */
    @Override
    public ArrayList<OrderPO> getUnexecutedList() throws RemoteException {
        if(orderDatabase==null){
            orderDatabase=mySql.init();
        }

        try{
            Statement unexecuted=orderDatabase.createStatement();
            ResultSet resultSet=unexecuted.executeQuery("select * from order where state = 1");

            ArrayList<OrderPO> list=readResult(resultSet);

            unexecuted.close();
            orderDatabase.close();
            orderDatabase=null;

            return list;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<OrderPO> getListByCustomer(int customerID) throws RemoteException {
        if(orderDatabase==null){
            orderDatabase=mySql.init();
        }

        try{
            Statement customer=orderDatabase.createStatement();
            ResultSet resultSet=customer.executeQuery("select * from order where customerid = "+customerID);

            ArrayList<OrderPO> list=readResult(resultSet);

            customer.close();
            orderDatabase.close();
            orderDatabase=null;

            return list;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<OrderPO> getListByHotel(int hotelID) throws RemoteException {
        if(orderDatabase==null){
            orderDatabase=mySql.init();
        }

        try{
            Statement hotel=orderDatabase.createStatement();
            ResultSet resultSet=hotel.executeQuery("select * from order where hotelid = "+hotelID);

            ArrayList<OrderPO> list=readResult(resultSet);

            hotel.close();
            orderDatabase.close();
            orderDatabase=null;

            return list;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param resultSet
     * @return ArrayList<OrderPO>
     * 设置异常时由下列方法完成，不需要bl调用
     */
    private ArrayList<OrderPO> readResult(ResultSet resultSet){
        ArrayList<OrderPO> list=new ArrayList<OrderPO>();
        try {
            while (resultSet.next()) {
                int orderNum = resultSet.getInt(1);
                int customerID = resultSet.getInt(2);
                int hotelID = resultSet.getInt(3);
                double amount = resultSet.getDouble(4);
                Date checkIn = resultSet.getDate(5);
                Date checkOut = resultSet.getDate(6);
                Date execute = resultSet.getDate(7);
                int roomSum = resultSet.getInt(8);
                int peopleSum = resultSet.getInt(9);
                boolean hasChild = resultSet.getBoolean(10);
                RoomType type = RoomType.values()[resultSet.getInt(11)];
                OrderState state = OrderState.values()[resultSet.getInt(12)];//需要即时确定并更新

                java.util.Date current=new java.util.Date();
                Date now=new Date(current.getTime());
                if(state==OrderState.unexecuted&&execute.before(now)){
                    state=OrderState.abnormal;

                    PreparedStatement modifyState=orderDatabase.prepareStatement("update order set state = ? where ordernum = ?");
                    modifyState.setInt(1,state.ordinal());
                    modifyState.setInt(2,orderNum);
                    modifyState.executeUpdate();
                }

                OrderPO orderPO = new OrderPO(orderNum, customerID, hotelID, amount, checkIn, checkOut, execute, roomSum, type,
                        peopleSum, hasChild, state);
                list.add(orderPO);
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        return list;
    }

    @Override
    public OrderPO getOrder(int orderID) throws RemoteException {

        return null;
    }

    @Override
    public ResultMessage add(OrderPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modify(int orderID, OrderState state) throws RemoteException {
        return null;
    }
}
