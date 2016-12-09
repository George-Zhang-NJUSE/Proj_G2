package group2.grade15.njuse.bl.orderbl;

import group2.grade15.njuse.po.OrderPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.vo.OrderVO;

import java.rmi.RemoteException;

public interface OrderBL {
    public OrderVO getInfo(int orderID);

}
