import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class main {
    public static void main(String[] args) {//客户端程序入口
        try {
            WebHookService personService = (WebHookService)Naming.lookup("rmi://127.0.0.1:32479/PersonService");
            personService.setCallBackInterface(new ClientUploadFileCallBackImpl());
            System.out.println("Client is running...\n");

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}