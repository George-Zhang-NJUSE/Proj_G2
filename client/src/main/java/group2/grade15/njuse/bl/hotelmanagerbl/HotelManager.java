package group2.grade15.njuse.bl.hotelmanagerbl;

import group2.grade15.njuse.bl.hotelpromotionbl.HotelPromotionController;
import group2.grade15.njuse.bl.hotelpromotionbl.HotelPromotionControllerBL;
import group2.grade15.njuse.po.HotelManagerPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.*;

import java.rmi.RemoteException;

/**
 * HoteManager的职责是处理单个酒店的业务逻辑
 * 具体的方法的定义可查看对应接口里的方法注释
 * @author Guo
 */
public class HotelManager implements HotelManagerBL{

    private HotelPromotionControllerBL hotelPromotionController;

    public HotelManager(){
        hotelPromotionController = new HotelPromotionController();
    }

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
        return hotelPromotionController.changeState(promotionVO);
    }

    @Override
    public ResultMessage stopHotelPromotion(HotelPromotionVO promotionVO) {
        return hotelPromotionController.changeState(promotionVO);
    }

    @Override
    public ResultMessage deleteHotelPromotion(HotelPromotionVO promotionVO) {
        return hotelPromotionController.changeState(promotionVO);
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
