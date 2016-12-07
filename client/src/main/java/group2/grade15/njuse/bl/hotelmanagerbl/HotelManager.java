package group2.grade15.njuse.bl.hotelmanagerbl;

import group2.grade15.njuse.po.HotelManagerPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;

/**
 * Created by Guo on 2016/11/13.
 */
public class HotelManager implements HotelManagerBL{

    @Override
    public HotelManagerVO getInfo(int hotelManagerId) {
        HotelManagerPO hotelManager = null;
        try {
            hotelManager = RemoteHelper.getInstance().getHotelManagerDataService().getHotelManager(hotelManagerId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(hotelManager != null) {
            return new HotelManagerVO(hotelManager);
        } else {
            return null;
        }
    }

    @Override
    public ResultMessage modifyInfo(HotelManagerVO hotelManager) {
        ResultMessage result;
        try {
            result = RemoteHelper.getInstance().getHotelManagerDataService().modify(hotelManager.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.CONNECTION_EXCEPTION;
        }
        return result;
    }

    @Override
    public ResultMessage activateHotelPromotion(HotelPromotionVO promotionVO) {
        return null;
    }

    @Override
    public ResultMessage stopHotelPromotion(HotelPromotionVO promotionVO) {
        return null;
    }

    @Override
    public ResultMessage deleteHotelPromotion(HotelPromotionVO promotionVO) {
        return null;
    }

    @Override
    public ResultMessage addCompany(int customerID) {
        return null;
    }

    @Override
    public ResultMessage deleteCompany(int customerID) {
        return null;
    }

}
