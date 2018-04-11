import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ClientUploadFileCallBackImpl extends UnicastRemoteObject implements ClientUploadFileCallBack {

    private static final long serialVersionUID = 5471018647833463871L;

    public ClientUploadFileCallBackImpl() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }


    @Override
    public boolean upload(String fileName, byte[] fileContent) throws RemoteException {
        File file = new File(fileName);
        try {
            if (!file.exists())
                file.createNewFile();
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
            os.write(fileContent);
            System.out.println("New file downloaded from server: " + fileName);
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found.\n");
            return false;
        } catch (IOException e) {
            System.out.println("IO error.\n");
            return false;
        }
    }
}