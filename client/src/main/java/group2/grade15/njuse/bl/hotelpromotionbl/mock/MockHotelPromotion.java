package bl.hotelpromotionbl.mock;

import bl.hotelpromotionbl.HotelPromotion;
import utility.PromotionType;
import utility.ResultMessage;
import data.hotelpromotiondata.HotelPromotionData;
import vo.HotelPromotionListVO;
import vo.HotelPromotionVO;


/**
 * Created by ALIENWARE-PC on 2016/11/13.
 */
public class MockHotelPromotion extends HotelPromotion {
    private PromotionType type;

    @Override
    public PromotionType getType() {
        return type;
    }

    @Override
    public HotelPromotionVO createHotelPromotion(HotelPromotionVO promotionInfo) {
        return promotionInfo;
    }

    @Override
    public HotelPromotionListVO getHotelPromotionList() {
        HotelPromotionData hpdata=new HotelPromotionData();

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
}
