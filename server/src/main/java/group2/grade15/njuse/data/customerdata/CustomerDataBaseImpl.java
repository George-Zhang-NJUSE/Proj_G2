package group2.grade15.njuse.data.customerdata;


import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.CustomerDataService;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.utility.MemberType;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by George on 2016/11/13.
 */
public class CustomerDataBaseImpl implements CustomerDataService {
    private DatabaseMySql mySql=null;
    private Connection customerConnection=null;

    public CustomerDataBaseImpl(DatabaseInfo info) throws RemoteException{
        mySql=new DatabaseMySql(info);
        customerConnection=mySql.init();
    }

    public CustomerPO get(int customerId) throws RemoteException {
        if(customerConnection==null){
            return null;
        }

        int id=0;
        String name=null,password=null,contact=null,identity=null;
        float credit=0;
        MemberType type=null;
        try{
            Statement getInfo=customerConnection.createStatement();
            if(getInfo==null){
                System.out.print("wrong");
            }
            ResultSet r=getInfo.executeQuery(
                    "select * from Customer where customerID = "+customerId);
            r.next();
            id=customerId;
            password=r.getString(2);
            name=r.getString(3);
            identity=r.getString(4);
            contact=r.getString(5);
            credit=r.getFloat(6);
            type=MemberType.values()[r.getString(7).charAt(0)-'0'];
            getInfo.close();
            customerConnection.close();
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        CustomerPO customerInfo=new CustomerPO(id,name,password,contact,identity,credit,type);
        return customerInfo;
    }

    public ResultMessage add(CustomerPO po) throws RemoteException {
        return null;
    }

    public ResultMessage modify(CustomerPO po) throws RemoteException {
        return null;
    }
}
