
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

    public synchronized boolean upload(String filename){
        byte[] b = null;
        try {
            File file = new File(filename);
            b = new byte[(int) file.length()];
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
            is.read(b);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("No such file");
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("IO Error.");
            return false;
        }
        for (ClientUploadFileCallBack personService : callBacks) {
            try {
                personService.upload(filename, b);
            } catch (RemoteException e) {
                System.out.println("An client is disconnected\n");
                callBacks.remove(personService);
            }
        }
        return true;
    }



    @Override
    public synchronized void setCallBackInterface(ClientUploadFileCallBack person) throws RemoteException {
        callBacks.add(person);
    }
}