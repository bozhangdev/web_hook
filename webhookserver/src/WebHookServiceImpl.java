
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class WebHookServiceImpl extends UnicastRemoteObject implements WebHookService{

    private static final long serialVersionUID = 621297838434573682L;
    private ArrayList<ClientUploadFileCallBack> callBacks = new ArrayList<>();

    public WebHookServiceImpl() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }

    public boolean upload(String filename){
        for (ClientUploadFileCallBack personService : callBacks) {
            try {
                boolean isUploaded = personService.upload(filename, fileToByte(filename));
            } catch (RemoteException e) {
                System.out.println("Client is disconnected\n");
            }
        }
        return true;
    }

    private byte[] fileToByte(String filename){
        byte[] b = null;
        try {
            File file = new File(filename);
            b = new byte[(int) file.length()];
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
            is.read(b);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("No such file");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public void setCallBackInterface(ClientUploadFileCallBack person) throws RemoteException {
        callBacks.add(person);
    }
}