package group2.grade15.njuse.runner;

import group2.grade15.njuse.rmi.RemoteHelper;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Guo on 2016/11/26.
 */

public class ClientRunner {

    private RemoteHelper remoteHelper;

    public ClientRunner() {
        linkToServer();
    }

    //进行RMI连接
    private void linkToServer() {
        try {
            remoteHelper = RemoteHelper.getInstance();
            remoteHelper.setRemote(Naming.lookup("rmi://192.168.1.104:1098/DataRemoteObject"));
            System.out.println("linked successfully!");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
