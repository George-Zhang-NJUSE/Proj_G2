package group2.grade15.njuse.data.webadmindata;

import group2.grade15.njuse.data.databaseimpl.DatabaseInfo;
import group2.grade15.njuse.data.databaseimpl.DatabaseMySql;
import group2.grade15.njuse.data.encrypt.Encrypt;
import group2.grade15.njuse.dataservice.webadmindataservice.WebAdminDataService;
import group2.grade15.njuse.po.*;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WebAdminDatabaseImpl implements WebAdminDataService, CustomerPartService, HotelManagerPartService, HotelPartService, WebMarketerPartService {
    private DatabaseInfo info = null;
    private DatabaseMySql mySql = null;
    private Connection webAdminConnection = null;
    private Encrypt encrypt;

    private CustomerPart customerPart = null;
    private HotelManagerPart hotelManagerPart = null;
    private WebMarketerPart webMarketerPart = null;
    private HotelPart hotelPart = null;

    public WebAdminDatabaseImpl(DatabaseInfo info) throws RemoteException {
        this.info = info;
        mySql = new DatabaseMySql(info);
        webAdminConnection = mySql.init();
        encrypt=new Encrypt();
    }

    public WebAdminPO getWebAdmin(String webAdminId) throws RemoteException {
        if (webAdminConnection == null) {
            webAdminConnection = mySql.init();
        }

        try {
            PreparedStatement getInfo = webAdminConnection.prepareStatement("select * from webadmin where " +
                    "employeeid=?");
            getInfo.setString(1, encrypt.encrypt(webAdminId));
            ResultSet resultSet = getInfo.executeQuery();

            resultSet.next();
            String id = webAdminId;
            String password = encrypt.decrypt(resultSet.getString(1));

            getInfo.close();
            webAdminConnection.close();
            webAdminConnection = null;

            WebAdminPO webAdminPO = new WebAdminPO(password, id);
            return webAdminPO;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<CustomerPO> getCustomerInfo() throws RemoteException {
        if (customerPart == null) {
            customerPart = new CustomerPart(info);
        }

        return customerPart.getCustomerInfo();
    }

    @Override
    public ResultMessage modifyCustomerInfo(CustomerPO customerPO) throws RemoteException {
        if (customerPart == null) {
            customerPart = new CustomerPart(info);
        }

        return customerPart.modifyCustomerInfo(customerPO);
    }

    @Override
    public ArrayList<HotelManagerPO> getHotelManagerInfo() throws RemoteException {
        if (hotelManagerPart == null) {
            hotelManagerPart = new HotelManagerPart(info);
        }
        return hotelManagerPart.getHotelManagerInfo();
    }

    @Override
    public ResultMessage modifyHotelManagerInfo(HotelManagerPO hotelManagerPO) throws RemoteException {
        if (hotelManagerPart == null) {
            hotelManagerPart = new HotelManagerPart(info);
        }
        return hotelManagerPart.modifyHotelManagerInfo(hotelManagerPO);
    }

    @Override
    public HotelManagerPO addHotelManagerInfo(HotelManagerPO hotelManagerPO) throws RemoteException {
        if (hotelManagerPart == null) {
            hotelManagerPart = new HotelManagerPart(info);
        }
        return hotelManagerPart.addHotelManagerInfo(hotelManagerPO);
    }

    @Override
    public ResultMessage addWebMarketerInfo(WebMarketerPO webMarketerPO) throws RemoteException {
        if (webMarketerPart == null) {
            webMarketerPart = new WebMarketerPart(info);
        }
        return webMarketerPart.addWebMarketerInfo(webMarketerPO);
    }

    @Override
    public ArrayList<WebMarketerPO> getWebMarketerInfo() throws RemoteException {
        if (webMarketerPart == null) {
            webMarketerPart = new WebMarketerPart(info);
        }

        return webMarketerPart.getWebMarketerInfo();
    }

    @Override
    public ResultMessage modifyWebMarketerInfo(WebMarketerPO webMarketerPO) throws RemoteException {
        if (webMarketerPart == null) {
            webMarketerPart = new WebMarketerPart(info);
        }

        return webMarketerPart.modifyWebMarketerInfo(webMarketerPO);
    }

    @Override
    public ResultMessage deleteWebMarketer(String webMarketerID) throws RemoteException {
        if (webMarketerPart == null) {
            webMarketerPart = new WebMarketerPart(info);
        }

        return webMarketerPart.deleteWebMarketer(webMarketerID);
    }

    @Override
    public HotelPO addHotel(HotelPO hotelPO) throws RemoteException {
        if (hotelPart == null) {
            hotelPart = new HotelPart(info);
        }

        return hotelPart.addHotel(hotelPO);
    }

    @Override
    public ArrayList<HotelPO> getHotelInfo() throws RemoteException {
        if (hotelPart == null) {
            hotelPart = new HotelPart(info);
        }

        return hotelPart.getHotelInfo();
    }

    @Override
    public ResultMessage modifyHotelInfo(HotelPO hotelPO) throws RemoteException {
        if (hotelPart == null) {
            hotelPart = new HotelPart(info);
        }

        return hotelPart.modifyHotelInfo(hotelPO);
    }

    @Override
    public ResultMessage deleteHotelInfo(int hotelID) throws RemoteException {
        if (hotelPart == null) {
            hotelPart = new HotelPart(info);
        }

        return hotelPart.deleteHotelInfo(hotelID);
    }
}
