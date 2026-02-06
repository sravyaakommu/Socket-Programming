import java.io.*;
import java.net.*;

public class SimpleDateClient {
    private static final String SERVER_HOST = "11.21.23.130"; 
    private static final int SERVER_PORT = 6031;

    public static void main(String[] args) {
        try {
            Socket sock = new Socket(SERVER_HOST, SERVER_PORT);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(sock.getInputStream())
            );
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            BufferedReader console = new BufferedReader(
                    new InputStreamReader(System.in)   
            );

            String line = in.readLine();
            System.out.println("Server date/time: " + line);

            while (true) {
                System.out.print("Client (type message): ");
                String msgToServer = console.readLine();
                if (msgToServer == null) msgToServer = "";

                out.println(msgToServer);

                if ("bye".equalsIgnoreCase(msgToServer.trim())) {
                    System.out.println("You ended the chat.");
                    break;
                }

                String serverReply = in.readLine();
                if (serverReply == null) {
                    System.out.println("Server disconnected.");
                    break;
                }
                System.out.println("Server: " + serverReply);

                if ("bye".equalsIgnoreCase(serverReply.trim())) {
                    System.out.println("Server ended the chat.");
                    break;
                }
            }

            sock.close();
        } catch (IOException ioe) {
            System.err.println("Client error: " + ioe);
        }
    }
}