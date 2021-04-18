package Client;

import Common.Common;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;

public class Client {

    public static void main(String[] args)
    {
        try
        {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            Common remoteCommonStub = (Common) registry.lookup("remoteServer");

            ArrayList<Double> squares = remoteCommonStub.squares(getArrayList());
            System.out.println("Squares:");
            squares.forEach(element -> System.out.print("  " + element));
            ArrayList<Double> roots = remoteCommonStub.roots(getArrayList());
            System.out.println("\nRoots:");
            roots.forEach(element -> System.out.print("  " + element));
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e);
        }
    }

    static ArrayList<Double> getArrayList() {
        return new ArrayList<Double>(Arrays.asList(21.0, 7.0, 63.0, 42.0, 35.0, 56.0, 14.0, 1.0));
    }
}