package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.utility.IDType;
import group2.grade15.njuse.vo.OrderListVO;

/**
 * Created by George on 2016/11/6.
 */
public interface OrderListBL {

    public OrderListVO getAllOrderList(int id);


    public OrderListVO getAbnormalOrderList(int id);

}
