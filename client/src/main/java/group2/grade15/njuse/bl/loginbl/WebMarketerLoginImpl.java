package group2.grade15.njuse.bl.loginbl;

import group2.grade15.njuse.blservice.LoginControllerServ;
import group2.grade15.njuse.po.WebMarketerPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by Guo on 2016/11/26.
 * 网站营销人员的登录端
 * 根据返回的ResultMessage不同分为四种情况：
 * ResultMessage.SUCCESS ：登录成功
 * ResultMessage.FAILED ：密码错误
 * ResultMessage.NON_EXISTENT ：账号不存在
 * ResultMessage.CONNECTION_EXCEPTION ：网络异常
 */
public class WebMarketerLoginImpl implements LoginControllerServ {

    WebMarketerPO webMarketerPO;
    ResultMessage result;

    @Override
    public ResultMessage login(String id, String password) {
        try {
            webMarketerPO = RemoteHelper.getInstance().getWebMarketerDataService().getWebMarketer(id);
        } catch (RemoteException e) {
            result = ResultMessage.CONNECTION_EXCEPTION;
            e.printStackTrace();
        }

        if (webMarketerPO != null) {
            if (webMarketerPO.getPassword().equals(password)) {
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
