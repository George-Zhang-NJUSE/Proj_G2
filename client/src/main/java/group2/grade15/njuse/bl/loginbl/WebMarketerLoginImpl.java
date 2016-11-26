package group2.grade15.njuse.bl.loginbl;

import group2.grade15.njuse.blservice.LoginControllerServ;
import group2.grade15.njuse.po.CustomerPO;
import group2.grade15.njuse.po.WebMarketerPO;
import group2.grade15.njuse.rmi.RemoteHelper;
import group2.grade15.njuse.utility.ResultMessage;

import java.rmi.RemoteException;

/**
 * Created by Guo on 2016/11/26.
 */
public class WebMarketerLoginImpl implements LoginControllerServ {

    WebMarketerPO po;
    ResultMessage result;

    @Override
    public ResultMessage login(int id, String password) {
        try {
            po = RemoteHelper.getInstance().getWebMarketerDataService().getWebMarketer(String.valueOf(id));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(po != null){
            if(po.getPassword().equals(password)){
                result = ResultMessage.SUCCESS;
            } else {
                result = ResultMessage.FAILED;
            }
        }

        return result;
    }

}
