package group2.grade15.njuse.dataservice.areadataservice;

import group2.grade15.njuse.po.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by George on 2016/10/16.
 */
public interface AreaDataService extends Remote {
    public ArrayList<ProvincePO> getProvince() throws RemoteException;
    public ArrayList<CityPO> getCity(String provinceNum) throws RemoteException;
    public ArrayList<DistrictPO> getDistrict(String cityNum) throws RemoteException;
    public ArrayList<CbdPO> getCbd(String districtNum) throws RemoteException;
    public ArrayList<HotelPO> getHotelByAddress(String address) throws RemoteException;
    public ArrayList<HotelPO> getHotelByName(String name) throws RemoteException;
}
