package group2.grade15.njuse.dataservice;

import group2.grade15.njuse.po.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by George on 2016/10/16.
 */
public interface AreaDataService extends Remote {
    public ArrayList<ProvincePO> getProvince();
    public ArrayList<CityPO> getCity(String provinceNum);
    public ArrayList<DistrictPO> getDistrict(String cityNum);
    public ArrayList<CbdPO> getCbd(String districtNum);
    public ArrayList<HotelPO> getHotel(String address);
}
