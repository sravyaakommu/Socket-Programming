import java.io.*;
import java.net.*;
import java.util.Date;

public class SimpleDateServer {
    private static final int PORT = 6031;

    public static void main(String[] args) {
        BufferedReader console = new BufferedReader(
                new InputStreamReader(System.in)   
        );

        try {
            ServerSocket sock = new ServerSocket(PORT);
            System.out.println("DateServer listening on port " + PORT);

            while (true) {
                Socket client = sock.accept();
                System.out.println("Client connected: " + client.getInetAddress());

                try {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(client.getInputStream())
                    );
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);

                    String dateStr = new Date().toString();
                    out.println("Server time: " + dateStr);
                    System.out.println("Sent date to client: " + dateStr);

                    while (true) {
                        String clientMsg = in.readLine();
                        if (clientMsg == null) {
                            System.out.println("Client disconnected.");
                            break;
                        }
                        System.out.println("Client: " + clientMsg);

                        if ("bye".equalsIgnoreCase(clientMsg.trim())) {
                            System.out.println("Client ended the chat.");
                            break;
                        }

                        System.out.print("Server (type reply): ");
                        String reply = console.readLine();
                        if (reply == null) reply = "";

                        out.println(reply);  

                        if ("bye".equalsIgnoreCase(reply.trim())) {
                            System.out.println("You ended the chat.");
                            break;
                        }
                    }

                    client.close();
                    System.out.println("Connection with this client closed.\n");
                } catch (IOException e) {
                    System.err.println("Error talking to client: " + e);
                }
            }
        } catch (IOException ioe) {
            System.err.println("Server error: " + ioe);
        }
    }
}