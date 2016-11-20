package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.IDType;
import group2.grade15.njuse.vo.OrderListVO;

import java.util.Date;

/**
 * Created by George on 2016/11/13.
 */
public interface OrderListServ {

    public OrderListVO getAllOrderList(int id, IDType type);

    public OrderListVO getExecutedOrderList(int id, IDType type);

    public OrderListVO getUnexecutedOrderList(int id, IDType type);

    public OrderListVO getNewOrderList(Date date);

    public OrderListVO getRevokedOrderList(int id, IDType type);

    public OrderListVO getAbnormalOrderList(int id, IDType type);
}
