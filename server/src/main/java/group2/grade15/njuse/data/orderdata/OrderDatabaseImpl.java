package group2.grade15.njuse.data.orderdata;

import group2.grade15.njuse.data.creditdata.CreditDatabaseImpl;
import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.data.encrypt.Encrypt;
import group2.grade15.njuse.dataservice.orderdataservice.OrderDataService;
import group2.grade15.njuse.po.CreditPO;
import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.utility.ChangeReason;
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
    private DatabaseMySql mySql = null;
    private Connection orderDatabase = null;
    private DatabaseInfo info = null;
    private Encrypt encrypt=null;

    public OrderDatabaseImpl(DatabaseInfo info) throws RemoteException {
        mySql = new DatabaseMySql(info);
        orderDatabase = mySql.init();
        this.info = info;
        encrypt=new Encrypt();
    }

    /**
     * @return
     * @throws RemoteException 适用于网站营销人员
     */
    @Override
    public ArrayList<OrderPO> getUnexecutedList() throws RemoteException {
        if (orderDatabase == null) {
            orderDatabase = mySql.init();
        }

        try {
            Statement unexecuted = orderDatabase.createStatement();
            ResultSet resultSet = unexecuted.executeQuery("select * from orderinfo where state = 0");

            ArrayList<OrderPO> list = readResult(resultSet);
            ArrayList<OrderPO> toDelete = new ArrayList<OrderPO>();
            for (OrderPO temp : list) {
                if (temp.getState().equals(OrderState.abnormal)) {
                    toDelete.add(temp);
                }
            }
            for (OrderPO temp : toDelete) {
                list.remove(temp);
            }


            unexecuted.close();
            orderDatabase.close();
            orderDatabase = null;

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<OrderPO> getListByCustomer(int customerID) throws RemoteException {
        if (orderDatabase == null) {
            orderDatabase = mySql.init();
        }

        try {
            Statement customer = orderDatabase.createStatement();
            ResultSet resultSet = customer.executeQuery("select * from orderinfo where customerid = " + encrypt.encrypt(customerID));

            ArrayList<OrderPO> list = readResult(resultSet);

            customer.close();
            orderDatabase.close();
            orderDatabase = null;

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<OrderPO> getListByHotel(int hotelID) throws RemoteException {
        if (orderDatabase == null) {
            orderDatabase = mySql.init();
        }

        try {
            Statement hotel = orderDatabase.createStatement();
            ResultSet resultSet = hotel.executeQuery("select * from orderinfo where hotelid = " + encrypt.encrypt(hotelID));

            ArrayList<OrderPO> list = readResult(resultSet);

            hotel.close();
            orderDatabase.close();
            orderDatabase = null;

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //还未添加加密算法
    @Override
    public int roomToBeAvailable(Date checkIn, Date checkOut, RoomType type, int hotelID) throws RemoteException{
        if(orderDatabase==null){
            orderDatabase=mySql.init();
        }

        try{
            PreparedStatement availableRoom=orderDatabase.prepareStatement("select roomsum from orderinfo where " +
                    "roomtype = ? and hotelID = ? and (state = 0 or state = 1) and checkouttime<?");
            availableRoom.setInt(1,type.ordinal());
            availableRoom.setInt(2,hotelID);
            availableRoom.setDate(3,checkIn);
            ResultSet resultSetA=availableRoom.executeQuery();

            int available=0;
            while (resultSetA.next()){
                available+=resultSetA.getInt(1);
            }

            PreparedStatement unavailableRoom=orderDatabase.prepareStatement("select roomsum from orderinfo where " +
                    "roomtype = ? and hotelID = ? and (state = 0 or state = 1) and checkintime<=? and checkouttime>=?");
            unavailableRoom.setInt(1,type.ordinal());
            unavailableRoom.setInt(2,hotelID);
            unavailableRoom.setDate(3,checkOut);
            unavailableRoom.setDate(4,checkIn);
            ResultSet resultSetU=unavailableRoom.executeQuery();

            int unavailable=0;
            while (resultSetU.next()){
                unavailable+=resultSetU.getInt(1);
            }

            return available-unavailable;
        }catch (SQLException e){
            e.printStackTrace();
            return 100000;
        }
    }

    /**
     * @param resultSet
     * @return ArrayList<OrderPO>
     * 设置异常时由下列方法完成，不需要bl调用
     */
    private ArrayList<OrderPO> readResult(ResultSet resultSet) {
        ArrayList<OrderPO> list = new ArrayList<OrderPO>();
        try {
            while (resultSet.next()) {
                int orderNum = resultSet.getInt(1);
                int customerID = encrypt.decrypt(resultSet.getInt(2));
                int hotelID = encrypt.decrypt(resultSet.getInt(3));
                double amount = resultSet.getDouble(4);
                Date checkIn = resultSet.getDate(5);
                Date checkOut = resultSet.getDate(6);
                Date execute = resultSet.getDate(7);
                int roomSum = resultSet.getInt(8);
                int peopleSum = resultSet.getInt(9);
                boolean hasChild = resultSet.getBoolean(10);
                RoomType type = RoomType.values()[resultSet.getInt(11)];
                OrderState state = OrderState.values()[resultSet.getInt(12)];//需要即时确定并更新

                java.util.Date current = new java.util.Date();
                Date now = new Date(current.getTime());
                if (state == OrderState.unexecuted && execute.before(now)) {
                    state = OrderState.abnormal;

                    PreparedStatement modifyState = orderDatabase.prepareStatement("update orderinfo set state = ? where ordernum = ?");
                    modifyState.setInt(1, state.ordinal());
                    modifyState.setInt(2, orderNum);
                    modifyState.executeUpdate();

                    CreditPO creditPO = new CreditPO(customerID, orderNum, 0, 0.00, -amount, null, ChangeReason.orderAbnormal);
                    CreditDatabaseImpl creditDatabase = new CreditDatabaseImpl(info);
                    if (!creditDatabase.add(creditPO).equals(ResultMessage.SUCCESS)) {
                        throw new Exception();
                    }
                }

                OrderPO orderPO = new OrderPO(orderNum, customerID, hotelID, amount, checkIn, checkOut, execute, roomSum, type,
                        peopleSum, hasChild, state);
                list.add(orderPO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    @Override
    public OrderPO getOrder(int orderID) throws RemoteException {
        if (orderDatabase == null) {
            orderDatabase = mySql.init();
        }

        try {
            PreparedStatement getInfo = orderDatabase.prepareStatement("select * from orderinfo where ordernum = ?");
            getInfo.setInt(1, orderID);
            ResultSet resultSet = getInfo.executeQuery();
            ArrayList<OrderPO> list = readResult(resultSet);

            getInfo.close();
            orderDatabase.close();
            orderDatabase = null;

            return list.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultMessage addOrder(OrderPO po) throws RemoteException {
        if (orderDatabase == null) {
            orderDatabase = mySql.init();
        }

        try {
            PreparedStatement addOrder = orderDatabase.prepareStatement("insert into orderinfo values(?,?,?,?,?,?,?,?,?,?,?,?)");
            Statement makeID = orderDatabase.createStatement();
            ResultSet id = makeID.executeQuery("select max(ordernum) from orderinfo");

            int orderID;
            if (id.next()) {
                orderID = id.getInt(1) + 1;
            } else {
                throw new SQLException();
            }
            makeID.close();

            int hotelID = encrypt.encrypt(po.getHotelID());
            int customerID = encrypt.encrypt(po.getCustomerID());
            double total = po.getAmount();
            Date checkIn = po.getCheckInTime();
            Date checkOut = po.getCheckOutTime();
            Date execute = po.getFinalExecuteTime();
            int room = po.getRoomSum();
            int people = po.getNumOfCustomer();
            boolean hasChild = po.isHaveChild();
            int type = po.getType().ordinal();
            int state = po.getState().ordinal();

            addOrder.setInt(1, orderID);
            addOrder.setInt(2, customerID);
            addOrder.setInt(3, hotelID);
            addOrder.setDouble(4, total);
            addOrder.setDate(5, checkIn);
            addOrder.setDate(6, checkOut);
            addOrder.setDate(7, execute);
            addOrder.setInt(8, room);
            addOrder.setInt(9, people);
            addOrder.setBoolean(10, hasChild);
            addOrder.setInt(11, type);
            addOrder.setInt(12, state);

            addOrder.executeUpdate();

            addOrder.close();
            orderDatabase.close();
            orderDatabase = null;

            return ResultMessage.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    /**
     * @param orderID
     * @param state
     * @return ResultMessage
     * @throws RemoteException 同时进行信用值的变化
     */
    @Override
    public ResultMessage modifyOrder(int orderID, OrderState state) throws RemoteException {
        if (orderDatabase == null) {
            orderDatabase = mySql.init();
        }

        try {
            Statement currentState = orderDatabase.createStatement();
            ResultSet resultSet = currentState.executeQuery("select state,total,customerid,executetime from orderinfo where ordernum = " + orderID);
            OrderState current;
            double sum;
            int customerID;
            Date execute;
            if (resultSet.next()) {
                current = OrderState.values()[resultSet.getInt(1)];
                sum = resultSet.getDouble(2);
                customerID = encrypt.decrypt(resultSet.getInt(3));
                execute = resultSet.getDate(4);
            } else {
                throw new SQLException();
            }
            currentState.close();

            PreparedStatement modifyState = orderDatabase.prepareStatement("update orderinfo set state = ? where ordernum = ?");
            modifyState.setInt(1, state.ordinal());
            modifyState.setInt(2, orderID);

            modifyState.executeUpdate();

            modifyState.close();
            orderDatabase.close();
            orderDatabase = null;

            //撤销异常订单需要单独调用credit
            if (current.equals(OrderState.unexecuted) && state.equals(OrderState.executed)) {
                CreditPO creditPO = new CreditPO(customerID, orderID, 0, 0, sum, null, ChangeReason.orderExecute);
                CreditDatabaseImpl creditDatabase = new CreditDatabaseImpl(info);
                if (creditDatabase.add(creditPO).equals(ResultMessage.FAILED)) {
                    throw new Exception();
                }
            } else if (current.equals(OrderState.unexecuted) && state.equals(OrderState.revoked)) {
                java.util.Date instant = new java.util.Date();
                Date now = new Date(instant.getTime());
                if (execute.getTime() - now.getTime() < 21600000) {
                    CreditPO creditPO = new CreditPO(customerID, orderID, 0, 0, -sum / 2.0, null, ChangeReason.orderCancelled);
                    CreditDatabaseImpl creditDatabase = new CreditDatabaseImpl(info);
                    if (creditDatabase.add(creditPO).equals(ResultMessage.FAILED)) {
                        throw new Exception();
                    }
                }
            }

            return ResultMessage.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }
}
