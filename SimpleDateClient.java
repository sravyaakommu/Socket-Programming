import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SimpleDateClient {

    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final int PORT = 5050;       public static void main(String[] args) {
        String host = (args.length > 0) ? args[0] : DEFAULT_HOST;

        System.out.println("Connecting to server " + host + " on port " + PORT + "...");

        try (Socket socket = new Socket(host, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Received from server: " + line);
            }

            System.out.println("Server closed the connection. Client exiting.");

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}