package group2.grade15.njuse.bl.hotelmanagerbl;

import group2.grade15.njuse.po.HotelManagerPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.OrderState;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.HotelManagerVO;
import group2.grade15.njuse.vo.HotelPromotionListVO;
import group2.grade15.njuse.vo.HotelPromotionVO;

import java.rmi.RemoteException;

/**
 * Created by Guo on 2016/12/7.
 */
public interface HotelManagerBL {
    public HotelManagerVO getInfo(int hotelManagerId);

    public ResultMessage modifyInfo(HotelManagerVO hotelManager);

    public ResultMessage activateHotelPromotion(HotelPromotionVO promotionVO);

    public ResultMessage stopHotelPromotion(HotelPromotionVO promotionVO);

    public ResultMessage deleteHotelPromotion(HotelPromotionVO promotionVO);

    public ResultMessage addCompany(int customerID);

    public ResultMessage deleteCompany(int customerID);

}
