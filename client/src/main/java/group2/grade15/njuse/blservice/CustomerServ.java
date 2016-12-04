package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CustomerVO;

public interface CustomerServ {

    /**
     * 添加单一的的客户账号
     * VO在界面层由用户填写，再传回逻辑层
     */
    public CustomerVO addCustomer(CustomerVO newCustomerVO);

    /**
     * 根据ID获取包含客户详细的VO
     */
    public CustomerVO getInfo(int customerID);

    /**
     * 负责修改客户信息
     * VO包含修改后的客户信息，由界面层传入逻辑层处理
     */
    public ResultMessage modifyInfo(CustomerVO vo);

}
