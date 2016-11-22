package group2.grade15.njuse.data.customerdata;


import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.dataservice.CustomerDataService;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.utility.MemberType;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.*;

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

    /**
     * @param customerId
     * @return CustomerPO
     * @throws RemoteException
     * 获取customer信息
     */
    public CustomerPO get(int customerId) throws RemoteException {
        if(customerConnection==null){
            customerConnection=mySql.init();
        }

        int id=0;
        String name=null,password=null,contact=null,identity=null;
        float credit=0;
        MemberType type=null;
        try{
            Statement getInfo=customerConnection.createStatement();
            /*if(getInfo==null){
                System.out.print("wrong");
            }*/
            ResultSet r=getInfo.executeQuery(
                    "select * from Customer where customerID = "+customerId);
            if(r.next()) {
                id = customerId;
                password = r.getString(2);
                name = r.getString(3);
                identity = r.getString(4);
                contact = r.getString(5);
                credit = r.getFloat(6);
                type = MemberType.values()[r.getInt(7)];
            }
            getInfo.close();
            customerConnection.close();
            customerConnection=null;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

        CustomerPO customerInfo=new CustomerPO(id,name,password,contact,identity,credit,type);
        return customerInfo;
    }

    /**
     * @param po
     * @return ResultMessage
     * @throws RemoteException
     * 仅name，password，contact，identity，type可进行添加，identity和type一经添加不可修改
     */
    public CustomerPO add(CustomerPO po) throws RemoteException {
        if(customerConnection==null){
            customerConnection=mySql.init();
        }

        int id=0;
        String name=po.getName(),password=po.getPassword(),contact=po.getContact(),identity=po.getIdentityNum();
        MemberType type=po.getType();
        float credit=0;

        try{
            Statement makeID=customerConnection.createStatement();
            ResultSet newID=makeID.executeQuery("select max(customerid) from customer");
            if(newID.next()){
                id=newID.getInt(1);
            }
            else{
                throw new SQLException();
            }
            makeID.close();

            PreparedStatement addOne=customerConnection.prepareStatement("insert into customer values(?,?,?,?,?,?,?)");
            addOne.setInt(1,id);
            addOne.setString(2,password);
            addOne.setString(3,name);
            addOne.setString(4,identity);
            addOne.setString(5,contact);
            addOne.setInt(7,type.ordinal());
            addOne.executeUpdate();//尚未get新的po

        }catch(SQLException e){
            e.printStackTrace();
        }

        CustomerPO newCustomer=new CustomerPO(id,name,password,contact,identity,credit,type);
        return null;
    }

    public ResultMessage modify(CustomerPO po) throws RemoteException {
        return null;
    }
}
