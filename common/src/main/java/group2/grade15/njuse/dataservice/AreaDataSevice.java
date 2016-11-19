package group2.grade15.njuse.dataservice;

import group2.grade15.njuse.po.CbdPO;
import group2.grade15.njuse.po.HotelListPO;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by George on 2016/10/16.
 */
public interface AreaDataSevice extends Remote {
    public HotelListPO getAreaList(CbdPO conditionInfo) throws RemoteException;

}
