package group2.grade15.njuse.bl.promotionfactory;

import group2.grade15.njuse.vo.HotelListVO;
import group2.grade15.njuse.vo.HotelPromotionVO;

/**
 * Created by Guo on 2016/12/6.
 */
public interface HotelPromotionBL {
    public double countPrice(double originalPrice, HotelPromotionVO hotelPromotionVO);
}
