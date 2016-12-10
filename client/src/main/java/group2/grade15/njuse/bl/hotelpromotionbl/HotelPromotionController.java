package group2.grade15.njuse.bl.hotelpromotionbl;

import group2.grade15.njuse.blservice.HotelPromotionServ;
import group2.grade15.njuse.po.HotelPromotionPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelPromotionListVO;
import group2.grade15.njuse.vo.HotelPromotionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Guo on 2016/11/13.
 */
public class HotelPromotionController implements HotelPromotionControllerBL, HotelPromotionServ {

    @Override
    public ResultMessage createHotelPromotion(HotelPromotionVO promotionInfo) {
        ResultMessage result;
        try {
            result = RemoteHelper.getInstance().getHotelPromotionDataService().add(promotionInfo.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.CONNECTION_EXCEPTION;
        }

        return result;
    }

    @Override
    public HotelPromotionVO getHotelPromotion(int hotelID, int hotelPromotionID) {
        HotelPromotionListVO hotelPromotionListVO = getHotelPromotionList(hotelID);
        ArrayList<HotelPromotionVO> hotelPromotionList = hotelPromotionListVO.getHotelPromotionList();

        for(HotelPromotionVO hotelPromotionVO : hotelPromotionList){
            if(hotelPromotionVO.getPromotionID() == hotelPromotionID){
                return hotelPromotionVO;
            }
        }

        return null;
    }

    @Override
    public HotelPromotionListVO getHotelPromotionList(int hotelID) {
        ArrayList<HotelPromotionVO> hotelPromotionList = new ArrayList();
        ArrayList<HotelPromotionPO> hotelPromotionPOList = new ArrayList();

        try {
            hotelPromotionPOList = RemoteHelper.getInstance().getHotelPromotionDataService().getList(hotelID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        for(HotelPromotionPO hotelPromotionPO : hotelPromotionPOList){
            hotelPromotionList.add(new HotelPromotionVO(hotelPromotionPO));
        }

        return new HotelPromotionListVO(hotelPromotionList);
    }

    @Override
    public ResultMessage modifyHotelPromotion(HotelPromotionVO promotion) {
        ResultMessage result;
        try {
            result = RemoteHelper.getInstance().getHotelPromotionDataService().modify(promotion.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.CONNECTION_EXCEPTION;
        }

        return result;
    }

    @Override
    public ResultMessage changeState(HotelPromotionVO promotionVO) {
        ResultMessage result;
        try {
            result = RemoteHelper.getInstance().getHotelPromotionDataService().modify(promotionVO.toPO());
        } catch (RemoteException e) {
            e.printStackTrace();
            result = ResultMessage.CONNECTION_EXCEPTION;
        }

        return result;
    }

}
