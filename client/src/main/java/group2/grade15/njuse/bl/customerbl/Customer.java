package group2.grade15.njuse.bl.customerbl;


import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CustomerVO;

import java.rmi.RemoteException;

/**
 * Created by George on 2016/11/6.
 */
public class Customer {

    private int id;
    private String name;
    private String password;
    private String contact;
    private int credit;

    public Customer(CustomerPO po) {
        id = po.getId();
        name = po.getName();
        password = po.getPassword();
        contact = po.getContact();
    }

    public CustomerVO getInfo() {
        CustomerPO po = null;

        try {
            po = RemoteHelper.getInstance().getCustomerDataService().getCustomer(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(po != null) {
            return new CustomerVO(po);
        } else {
            return null;
        }
    }

    public ResultMessage modifyInfo(CustomerVO vo) {
        return ResultMessage.SUCCESS;
    }

    public int getId() {
        return id;
    }

}
