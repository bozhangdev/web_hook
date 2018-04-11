import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WebHookService extends Remote{
    void setCallBackInterface(ClientUploadFileCallBack person) throws RemoteException;
}