package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;

/**
 * 登录业务逻辑的层间接口
 * 供界面层的登录界面调用
 * 不同的客户端登录逻辑都实现该接口
 * 职责是处理酒店相关的逻辑功能
 * @author Guo
 */
public interface LoginControllerServ {
    /**
     * 用户登录
     * @param id int型，界面层传递来的用户ID
     * @param password String型，界面层传来的用户密码
     * @return 根据返回ResultMessage不同分为四种情况：
     *         ResultMessage.SUCCESS ：登录成功
     *         ResultMessage.FAILED ：密码错误
     *         ResultMessage.NON_EXISTENT ：账号不存在
     *         ResultMessage.CONNECTION_EXCEPTION ：网络异常
     */
    public ResultMessage login(int id, String password);
}
