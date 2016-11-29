package group2.grade15.njuse.data.webadmindata;

import group2.grade15.njuse.po.HotelPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by dell on 2016/11/25.
 */
public interface HotelPartService extends Remote{
    public HotelPO addHotel(HotelPO hotelPO) throws RemoteException;
    public ArrayList<HotelPO> getHotelInfo() throws RemoteException;
    public ResultMessage modifyHotelInfo(HotelPO hotelPO) throws RemoteException;
    public ResultMessage deleteHotelInfo(int hotelId) throws RemoteException;//这里要把工作人员一起删掉
}
