package Common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Common extends Remote {
    ArrayList<Double> squares(ArrayList<Double> arr) throws RemoteException;

    ArrayList<Double> roots(ArrayList<Double> arr) throws RemoteException;
}