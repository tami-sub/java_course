import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    private static LinkedList<Connection> connections = new LinkedList<>();     //пользователи

    public static synchronized void serverMessage(String message) {     // Серверное сообщение
        System.out.println(message);
        for (Connection user : connections) {
            user.out.println(message);
        }
    }

    public static void main(String[] args) {
        ServerSocket server = null;

        try {
            server = new ServerSocket(4020);
            while (true) {
                Socket socket = server.accept();    //Получаем сокет
                connections.add(new Connection(socket, connections.size()));
                serverMessage("User №" + (connections.size()-1) + " joined");
                new Thread(connections.getLast()).start(); //запускаем новый поток
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert server != null;
                server.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static synchronized void getMessage(String message, Connection fromUser) {
        System.out.println(String.format("Message: %s from №%s", message, fromUser.id));
        fromUser.out.println("Me:\n" + message);
        for (Connection user : connections) {
            if (user != fromUser) {
                user.out.println(String.format("Another user: №%s \n %s", fromUser.id, message));
            }
        }
    }
}