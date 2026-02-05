import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimpleDateServer {

    private static final int PORT = 5050;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        System.out.println("Starting SimpleDateServer on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                System.out.println("Waiting for client connection...");

                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("Client connected from: " + clientSocket.getInetAddress());

                    String dateTime = LocalDateTime.now().format(FORMATTER);

                    out.println("Welcome to SimpleDateServer!");
                    out.println("Current server date and time: " + dateTime);

                    System.out.println("Sent date/time to client and closed connection.");
                } catch (IOException e) {
                    System.err.println("Error while communicating with a client: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("Could not start server on port " + PORT);
            e.printStackTrace();
        }
    }
}