package group2.grade15.njuse.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by Guo on 2016/11/26.
 */
public class RemoteHelper {

    public RemoteHelper() {
        initServer();
    }

    public void initServer() {
        DataRemoteObject dataRemoteObject;
        try {
            dataRemoteObject = new DataRemoteObject();
            LocateRegistry.createRegistry(1098);
            Naming.bind("rmi://192.168.1.104:1098/DataRemoteObject", dataRemoteObject);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

    }
}
