package group2.grade15.njuse.dataservice.webadmindataservice;

import group2.grade15.njuse.po.*;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by George on 2016/10/16.
 */
public interface WebAdminDataService extends Remote {
    public WebAdminPO getWebAdmin(String webAdminId) throws RemoteException;

    public ArrayList<CustomerPO> getCustomerInfo() throws RemoteException;

    public ResultMessage modifyCustomerInfo(CustomerPO customerPO) throws RemoteException;

    public ArrayList<HotelManagerPO> getHotelManagerInfo() throws RemoteException;

    public ResultMessage modifyHotelManagerInfo(HotelManagerPO hotelManagerPO) throws RemoteException;

    public HotelManagerPO addHotelManagerInfo(HotelManagerPO hotelManagerPO) throws RemoteException;

    public HotelPO addHotel(HotelPO hotelPO) throws RemoteException;

    public ArrayList<HotelPO> getHotelInfo() throws RemoteException;

    public ResultMessage modifyHotelInfo(HotelPO hotelPO) throws RemoteException;

    public ResultMessage deleteHotelInfo(int hotelId) throws RemoteException;//这里要把工作人员一起删掉

    public ResultMessage addWebMarketerInfo(WebMarketerPO webMarketerPO) throws RemoteException;

    public ArrayList<WebMarketerPO> getWebMarketerInfo() throws RemoteException;

    public ResultMessage modifyWebMarketerInfo(WebMarketerPO webMarketerPO) throws RemoteException;

    public ResultMessage deleteWebMarketer(String webMarketerID) throws RemoteException;
}
