package bl.orderbl;

import utility.IDType;
import vo.OrderListVO;

/**
 * Created by George on 2016/11/6.
 */
public interface OrderListBL {

    public OrderListVO getAbnormalOrderList(int id, IDType type);

}
