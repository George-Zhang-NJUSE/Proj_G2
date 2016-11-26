package group2.grade15.njuse.data.hotelpromotiondata;

import group2.grade15.njuse.dataservice.HotelPromotionDataService;
import group2.grade15.njuse.po.PromotionPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
public class HotelPromotionData implements HotelPromotionDataService {

    public ArrayList<PromotionPO> getList(int hotelId) throws RemoteException {
        return null;
    }

    public ResultMessage modify(PromotionPO po) throws RemoteException {
        return null;
    }

    public ResultMessage remove(PromotionPO po) throws RemoteException {
        return null;
    }

    public ResultMessage add(PromotionPO po) throws RemoteException {
        return null;
    }
}
