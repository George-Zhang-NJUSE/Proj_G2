package bl.hotelmanagerbl.mock;

import bl.hotelmanagerbl.HotelManager;
import po.HotelPromotionListPO;
import utility.ResultMessage;
import data.hotelpromotiondata.HotelPromotionData;
import utility.OrderState;
import vo.*;

import java.rmi.RemoteException;

/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
public class MockHotelManager extends HotelManager {
    private int id;
    private String password;
    private String name;
    private String contact;

    @Override
    public ResultMessage modifyInfo(HotelManagerVO hotelManager) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage modifyHotelInfo(HotelVO hotel) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public HotelVO getHotelInfo(int hotelID) {
        return null;
    }

    @Override
    public ResultMessage modifyRoomInfo(RoomVO roomInfo) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public HotelPromotionVO createHotelPromotion(HotelPromotionVO promotionInfo) {
        return promotionInfo;
    }

    @Override
    public HotelPromotionListVO getHotelPromotionList() {
        HotelPromotionData hpdata=new HotelPromotionData();
        try{
            HotelPromotionListPO a=hpdata.getList(id);
            HotelPromotionListVO b=new HotelPromotionListVO(null);

            return null;
        }catch(RemoteException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultMessage modifyHotelPromotion(HotelPromotionVO hotelPromotion) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage activateHotelPromotion(HotelPromotionVO promotionVO) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage stopHotelPromotion(HotelPromotionVO promotionVO) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage deleteHotelPromotion(HotelPromotionVO promotionVO) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage modifyState(int orderID, OrderState s) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage addCompany(int customerID) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage deleteCompany(int customerID) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public HotelManagerVO getInfo(int hotelManagerId) {
        return new HotelManagerVO(id,password,name,contact);
    }
}
