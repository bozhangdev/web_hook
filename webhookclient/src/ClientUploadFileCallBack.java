import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientUploadFileCallBack extends Remote{
    boolean upload(String fileName, byte[] fileContent) throws RemoteException;
}