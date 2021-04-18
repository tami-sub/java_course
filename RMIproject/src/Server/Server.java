package Server;
import Common.Common;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server
{
    public static void main(String[] args)
    {
        try
        {
            CommonImpl commonImplement = new CommonImpl();
            Common remoteCommonStub = (Common) UnicastRemoteObject.exportObject(commonImplement, 0);


            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            String name = "remoteServer";
            registry.rebind(name, remoteCommonStub);

            System.out.println("Object is registered.");
            System.out.println("Now server is waiting for client request...");
        }
        catch(Exception e){
            System.out.println("Error : "+e);
        }
    }
}