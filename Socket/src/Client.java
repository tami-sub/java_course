import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // ридер читающий с консоли
    private static BufferedReader in; // поток чтения из сокета
    private static PrintWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                // адрес - локальный хост, порт, такой же как у сервера
                clientSocket = new Socket("localhost", 4020); // запрашиваем
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                System.out.println("Write your message:");
                while (true) {
                    if (in.ready())
                        System.out.println(in.readLine());

                    if (reader.ready()) {
                        String input = reader.readLine();
                        out.println(input);
                        if (input.contains("stop")) break;
                    }
                }
            } finally {
                out.close();
                in.close();
                reader.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}