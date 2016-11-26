package group2.grade15.njuse.data.webadmindata;

import group2.grade15.njuse.data.customerdata.CustomerDataBaseImpl;
import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.cusotmerdataservice.CustomerDataService;
import group2.grade15.njuse.dataservice.webadmindataservice.CustomerPartService;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by dell on 2016/11/25.
 */
public class CustomerPart implements CustomerPartService{
    private DatabaseInfo info=null;
    private DatabaseMySql mySql=null;
    private Connection customerPartDatabase=null;

    public CustomerPart(DatabaseInfo info){
        this.info=info;
    }

    @Override
    public CustomerPO getCustomerInfo(int customerID) throws RemoteException {
        CustomerDataBaseImpl customerDataBase=new CustomerDataBaseImpl(info);
        return customerDataBase.get(customerID);
    }

    /**
     * @param customerPO
     * @return ResultMessage
     * @throws RemoteException
     * 除id,credit之外其它均可修改
     */
    @Override
    public ResultMessage modifyCustomerInfo(CustomerPO customerPO) throws RemoteException {
        if(mySql==null){
            mySql=new DatabaseMySql(info);
        }
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
