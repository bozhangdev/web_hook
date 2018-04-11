import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;


public class main {
    public static void main(String[] args) {//服务端程序入口
        WebHookService webHookService = null;
        try {
            webHookService = new WebHookServiceImpl();
            LocateRegistry.createRegistry(32479);
            Naming.bind("rmi://127.0.0.1:32479/PersonService", webHookService);
            System.out.println("Service started...");
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press 1 to upload a new file:");
            int choice = scanner.nextInt();
            if (choice == 1){
                System.out.println("Please input the relative path of the file:");
                Scanner scanner1 = new Scanner(System.in);
                String file = scanner1.nextLine();
                ((WebHookServiceImpl) webHookService).upload(file);
            }
        }
    }
}