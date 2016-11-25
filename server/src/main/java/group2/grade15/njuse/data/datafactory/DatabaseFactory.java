package group2.grade15.njuse.data.datafactory;

import group2.grade15.njuse.data.customerdata.CustomerDataBaseImpl;
import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.hotelmanagerdata.HotelManagerDatabaseImpl;
import group2.grade15.njuse.data.webmarketerdata.WebMarketerDatabaseImpl;
import group2.grade15.njuse.dataservice.cusotmerdataservice.CustomerDataService;
import group2.grade15.njuse.dataservice.datafactory.DataFactory;

import java.rmi.RemoteException;

/**
 * Created by dell on 2016/11/20.
 */
public class DatabaseFactory implements DataFactory {
    static DatabaseFactory dbFactory=null;
    DatabaseInfo info=new DatabaseInfo("jdbc:postgresql://localhost:5432/FirstDatabase","postgres","1997wyh");

    CustomerDataBaseImpl customerDataBase=null;
    HotelManagerDatabaseImpl hotelManagerDatabase=null;
    WebMarketerDatabaseImpl webMarketerDatabase=null;

    private DatabaseFactory(){}

    public static DatabaseFactory getInstance(){
        if(dbFactory==null){
            dbFactory=new DatabaseFactory();
        }
        return dbFactory;
    }


    public CustomerDataService getCustomerDataService() throws RemoteException{
        if(customerDataBase==null) {
            try {
                customerDataBase = new CustomerDataBaseImpl(info);
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return customerDataBase;
    }

    public HotelManagerDatabaseImpl getHotelManagerDataService() throws RemoteException{
        if(hotelManagerDatabase==null){
            try{
                hotelManagerDatabase=new HotelManagerDatabaseImpl(info);
            }catch (RemoteException e){
                e.printStackTrace();
                return null;
            }
        }
        return hotelManagerDatabase;
    }

    public WebMarketerDatabaseImpl getWebMarketerDataService() throws RemoteException{
        if(webMarketerDatabase==null){
            try{
                webMarketerDatabase=new WebMarketerDatabaseImpl(info);
            }catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }
        return webMarketerDatabase;
    }

}
