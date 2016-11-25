package group2.grade15.njuse.bl.customerbl;


import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CustomerVO;

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
        return new CustomerVO(id, name, password, contact, credit);
    }

    public ResultMessage modifyInfo(CustomerVO vo) {
        return ResultMessage.SUCCESS;
    }

    public int getId() {
        return id;
    }

}
