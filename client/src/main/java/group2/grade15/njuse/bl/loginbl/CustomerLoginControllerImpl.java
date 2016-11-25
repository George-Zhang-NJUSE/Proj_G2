package group2.grade15.njuse.bl.loginbl;

import group2.grade15.njuse.blservice.LoginControllerServ;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by 果宝 on 2016/11/25.
 */
public class CustomerLoginControllerImpl implements LoginControllerServ {

    public ResultMessage login(int id, String password) {
        ResultMessage result = null;
        CustomerPO po;

        if(password.equals(password)){
            result = ResultMessage.SUCCESS;
        } else {
            result = ResultMessage.FAILED;
        }

        return result;
    }
}
