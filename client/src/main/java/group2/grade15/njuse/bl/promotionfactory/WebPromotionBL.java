package group2.grade15.njuse.bl.promotionfactory;

import group2.grade15.njuse.vo.HotelPromotionVO;
import group2.grade15.njuse.vo.WebPromotionVO;

/**
 * Created by Guo on 2016/12/6.
 */
public interface WebPromotionBL {
    public double countPrice(double originalPrice, WebPromotionVO webPromotionVO);
}
