import java.io.*;
import java.net.Socket;

public class Connection implements Runnable {
    private Socket socket;
    private BufferedReader in;
    protected PrintWriter out;
    protected int id;

    public Connection(Socket socket, int id) throws IOException {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            this.id = id;
    }

    @Override
    public void run() {
        try {
            String input;
            while ((input = in.readLine()) != null) {
                Server.getMessage(input, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}