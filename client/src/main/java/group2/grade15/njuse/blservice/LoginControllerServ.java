package group2.grade15.njuse.blservice;

import group2.grade15.njuse.utility.IDType;
import group2.grade15.njuse.utility.ResultMessage;

/**
 * 登录的业务逻辑层接口
 * 不断的客户端登录逻辑都实现该接口
 * 界面层采用策略模式实现不同的登录逻辑
 */

public interface LoginControllerServ {
	public ResultMessage login(int id,String password);
}
