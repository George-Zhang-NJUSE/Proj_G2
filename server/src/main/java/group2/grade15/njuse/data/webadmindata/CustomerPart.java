package group2.grade15.njuse.data.webadmindata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.utility.MemberType;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by dell on 2016/11/25.
 */
public class CustomerPart implements CustomerPartService{
    private DatabaseMySql mySql=null;
    private Connection customerPartDatabase=null;

    public CustomerPart(DatabaseInfo info){
        mySql=new DatabaseMySql(info);
        customerPartDatabase=mySql.init();
    }

    @Override
    public ArrayList<CustomerPO> getCustomerInfo() throws RemoteException {
        if(customerPartDatabase==null){
            customerPartDatabase=mySql.init();
        }

        try{
            Statement getInfo=customerPartDatabase.createStatement();
            ResultSet resultSet=getInfo.executeQuery("select * from customer");

            ArrayList<CustomerPO> list=new ArrayList<CustomerPO>();
            while(resultSet.next()){
                int id=resultSet.getInt(1);
                String password=resultSet.getString(2);
                String name=resultSet.getString(3);
                String tel=resultSet.getString(4);
                double credit=resultSet.getDouble(5);
                MemberType type=MemberType.values()[resultSet.getInt(6)];
                Date birthday=resultSet.getDate(7);

                CustomerPO customerPO=new CustomerPO(id,name,password,tel,birthday,credit,type);
                list.add(customerPO);
            }

            getInfo.close();
            customerPartDatabase.close();
            customerPartDatabase=null;

            return list;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param customerPO
     * @return ResultMessage
     * @throws RemoteException
     * 除id,credit之外其它均可修改
     */
    @Override
    public ResultMessage modifyCustomerInfo(CustomerPO customerPO) throws RemoteException {
        if(customerPartDatabase==null){
            customerPartDatabase=mySql.init();
        }

        try{
            PreparedStatement modify=customerPartDatabase.prepareStatement("update customer set " +
                    "password = ?,name = ?,telnum = ?,membertype = ?,birthday = ?" +
                    " where customerid = "+customerPO.getId());
            modify.setString(1,customerPO.getPassword());
            modify.setString(2,customerPO.getName());
            modify.setString(3,customerPO.getContact());
            modify.setInt(4,customerPO.getType().ordinal());
            modify.setDate(5,customerPO.getBirthday());
            modify.executeUpdate();

            modify.close();
            customerPartDatabase.close();
            customerPartDatabase=null;

            return ResultMessage.SUCCESS;
        }catch (SQLException e){
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }
}
