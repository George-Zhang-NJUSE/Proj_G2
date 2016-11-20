package group2.grade15.njuse.data.datafactory;

import group2.grade15.njuse.data.customerdata.CustomerDataBaseImpl;
import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.dataservice.CustomerDataService;

import java.rmi.RemoteException;

/**
 * Created by dell on 2016/11/20.
 */
public class DatabaseFactory implements DataFactory{
    static DatabaseFactory dbFactory=null;
    DatabaseInfo info=new DatabaseInfo("jdbc:postgresql://localhost/FirstDatabase","postgres","1997wyh");
    CustomerDataBaseImpl customerDataBase=null;

    private DatabaseFactory(){}

    public static DatabaseFactory getInstance(){
        if(dbFactory==null){
            dbFactory=new DatabaseFactory();
        }
        return dbFactory;
    }


    public CustomerDataService getCustomerDataService(){
        if(customerDataBase==null) {
            try {
                customerDataBase = new CustomerDataBaseImpl(info);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return customerDataBase;
    }

}
