package group2.grade15.njuse.runner;

import group2.grade15.njuse.rmi.RemoteHelper;

/**
 * Created by Guo on 2016/11/26.
 */
public class ServerRunner {

    public ServerRunner() {
        new RemoteHelper();
    }

    public static void main(String[] args) {
        new ServerRunner();
    }
}
