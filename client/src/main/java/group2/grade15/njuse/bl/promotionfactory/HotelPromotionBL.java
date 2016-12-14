package group2.grade15.njuse.bl.promotionfactory;

import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelPromotionVO;
import group2.grade15.njuse.vo.OrderVO;

/**
 * Created by Guo on 2016/12/6.
 */
public interface HotelPromotionBL {
    public double countPrice(OrderVO orderVO, HotelPromotionVO hotelPromotionVO);
}
