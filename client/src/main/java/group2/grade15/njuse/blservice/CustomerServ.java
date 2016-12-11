package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;
import group2.grade15.njuse.vo.CreditListVO;
import group2.grade15.njuse.vo.CustomerVO;

/**
 * 客户业务逻辑的层间接口
 * 供界面层层调用
 * 职责是处理酒店评论相关的逻辑功能
 * @author Guo
 */
public interface CustomerServ {

    /**
     * 添加新的客户账号
     * @param newCustomerVO CustomerVO型，界面层传递来的存有注册信息的数据对象
     * @return 返回新的CustomerVO对象，若返回VO为null则注册信息已存在，否则注册成功
     */
    public CustomerVO addCustomer(CustomerVO newCustomerVO);

    /**
     * 根据ID获取包含客户详细的VO
     * @param customerID int型，界面层传递来的用户ID
     * @return 成功返回ID对应的CustomerVO，失败则返回null
     */
    public CustomerVO getInfo(int customerID);

    /**
     * 负责修改客户信息
     * @param customerVO CustomerVO型，VO包含修改后的客户信息，由界面层传入逻辑层处理
     * @return 成功返回ResultMessage.SUCCESS
     *         失败返回ResultMessage.FAILED
     *         网络问题则返回ResultMessage.CONNECTION_EXCEPTION
     */
    public ResultMessage modifyInfo(CustomerVO customerVO);

    /**
     * 负责获取用户所有的信用变化记录
     * @param customerID int型，由界面层传入逻辑层的客户ID
     * @return 成功返回该客户的CreditListVO
     *         失败返回null
     */
    public CreditListVO getCreditHistory(int customerID);

}
