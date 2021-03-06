package group2.grade15.njuse.bl.loginbl;

import group2.grade15.njuse.blservice.LoginControllerServ;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by Guo on 2016/11/26.
 * 客户的登录端
 * 根据返回的ResultMessage不同分为四种情况：
 * ResultMessage.SUCCESS ：登录成功
 * ResultMessage.FAILED ：密码错误
 * ResultMessage.NON_EXISTENT ：账号不存在
 * ResultMessage.CONNECTION_EXCEPTION ：网络异常
 */
public class CustomerLoginImpl implements LoginControllerServ {

    CustomerPO customerPO;
    ResultMessage result;

    @Override
    public ResultMessage login(String id, String password) {
        try {
            customerPO = RemoteHelper.getInstance().getCustomerDataService().getCustomer(Integer.parseInt(id));
        } catch (RemoteException e) {
            result = ResultMessage.CONNECTION_EXCEPTION;
            e.printStackTrace();
        }

        if (customerPO != null) {
            if (customerPO.getPassword().equals(password)) {
                //登录成功
                result = ResultMessage.SUCCESS;
            } else {
                //密码错误
                result = ResultMessage.FAILED;
            }
        } else {
            //账号不存在
            result = ResultMessage.NON_EXISTENT;
        }

        return result;
    }

}
