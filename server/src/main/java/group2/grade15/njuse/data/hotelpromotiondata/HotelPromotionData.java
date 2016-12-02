package group2.grade15.njuse.data.hotelpromotiondata;

import group2.grade15.njuse.dataservice.HotelPromotionDataService;
import group2.grade15.njuse.po.HotelPromotionPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
public class HotelPromotionData implements HotelPromotionDataService {

    @Override
    public ArrayList<HotelPromotionPO> getList(int hotelId) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage modify(HotelPromotionPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage remove(HotelPromotionPO po) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage add(HotelPromotionPO po) throws RemoteException {
        return null;
    }
}
