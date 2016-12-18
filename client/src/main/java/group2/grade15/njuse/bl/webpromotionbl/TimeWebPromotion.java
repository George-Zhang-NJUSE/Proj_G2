package group2.grade15.njuse.bl.webpromotionbl;

import group2.grade15.njuse.bl.orderbl.Order;
import group2.grade15.njuse.bl.orderbl.OrderBL;
import group2.grade15.njuse.bl.promotionfactory.WebPromotionBL;
import group2.grade15.njuse.vo.*;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Guo on 2016/12/6.
 */
public class TimeWebPromotion implements WebPromotionBL {
    public OrderBL order;

    public TimeWebPromotion(){
        order = new Order();
    }

    @Override
    public double countPrice(OrderVO orderVO, WebPromotionVO webPromotionVO) {

        double totalPrice = order.getOriginalPrice(orderVO);

        if(isFit(orderVO, webPromotionVO)) {
            return totalPrice * webPromotionVO.getDiscount();
        } else {
            return totalPrice;
        }
    }

    private boolean isFit(OrderVO orderVO, WebPromotionVO webPromotionVO){
        Timestamp createTime = orderVO.getCreateTime();

        Date startTime = webPromotionVO.getStart();
        Date endTime = webPromotionVO.getEnd();

        return createTime.after(startTime) && createTime.before(endTime);
    }
}
