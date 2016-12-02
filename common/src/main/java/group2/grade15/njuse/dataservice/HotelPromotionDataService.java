package group2.grade15.njuse.dataservice;

import group2.grade15.njuse.po.HotelPromotionPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by George on 2016/10/16.
 */
public interface HotelPromotionDataService extends Remote {

    public ArrayList<HotelPromotionPO> getList(int hotelId) throws RemoteException;

    public ResultMessage modify(HotelPromotionPO po) throws RemoteException;

    public ResultMessage remove(HotelPromotionPO po) throws RemoteException;

    public ResultMessage add(HotelPromotionPO po) throws RemoteException;

}
