package shared_interfaces;

import java.rmi.Remote;

public interface HookServerService extends Remote {
    void signUp(String email, String password, ClientCallBack callBack);
    boolean logIn(String email, String password);
    void deleteHook(String email, String password);
    void getNewFiles(String email);
}
