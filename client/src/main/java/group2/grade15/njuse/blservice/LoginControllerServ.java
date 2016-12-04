package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.ResultMessage;

/**
 * 登录的业务逻辑层接口
 * 不断的客户端登录逻辑都实现该接口
 * 界面层采用策略模式实现不同的登录逻辑
 */

public interface LoginControllerServ {
    /**
     * 供界面层调用的用户登录的方法
     * 根据返回的ResultMessage不同分为四种情况：
     * ResultMessage.SUCCESS ：登录成功
     * ResultMessage.FAILED ：密码错误
     * ResultMessage.NON_EXISTENT ：账号不存在
     * ResultMessage.CONNECTION_EXCEPTION ：网络异常
     */
    public ResultMessage login(int id, String password);
}
