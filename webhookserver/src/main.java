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
            System.out.println("Press 1 to upload a new file to clients:");
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Please input 1");
                continue;
            }
            if (choice == 1){
                System.out.println("Please input the relative path of the file:");
                Scanner scanner1 = new Scanner(System.in);
                String file = scanner1.nextLine();
                if (((WebHookServiceImpl) webHookService).upload(file)){
                    System.out.println("This file has been sent to clients.\n");
                }
            }else {
                System.out.println("Please input 1");
            }
        }
    }
}