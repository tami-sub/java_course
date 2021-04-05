package sample;

import java.io.*;

public class Saver {
    public static final Saver INSTANCE = new Saver();

    private Saver() {
    }

    public void saveAccount(Client client,Account acc, String filename) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
        objectOutputStream.writeObject(client);
        objectOutputStream.writeObject(acc);
//        objectOutputStream.writeObject(collect);
        objectOutputStream.close();
    }

    public Account reestablishAccount(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
        Client client = (Client) objectInputStream.readObject();
        Account acc = (Account) objectInputStream.readObject();
//        Collector collect = (Collector) objectInputStream.readObject();
        objectInputStream.close();
        return acc;
    }
}