package bl.customerbl;


import po.CustomerPO;
import utility.ResultMessage;
import vo.CustomerVO;

/**
 * Created by George on 2016/11/6.
 */
public class Customer {

    private int id;
    private String name;
    private String password;
    private String contact;
    private String identityNum;
    private int credit;

    public Customer(CustomerPO po) {
        id = po.getId();
        name = po.getName();
        password = po.getPassword();
        contact = po.getContact();
        identityNum = po.getIdentityNum();

    }


    public CustomerVO getInfo() {
        return new CustomerVO(id, name, password, contact, identityNum, credit);
    }

    public ResultMessage modifyInfo(CustomerVO vo) {
        return ResultMessage.SUCCESS;
    }

    public int getId() {
        return id;
    }

}
