import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {
        ExecutorService threadPool =
                Executors.newFixedThreadPool(1000);

        try (ServerSocket server = new ServerSocket(7789)) {

            server.setReuseAddress(true);
            //XMLHandler.Runner.start();

            // running infinite loop for getting client request
            while (true) {

                // socket object to receive incoming client requests
                Socket client = server.accept();

                // Displaying that new client is connected to server
                /*System.out.println("New client connected"
                        + client.getInetAddress()
                        .getHostAddress());*/

                // create a new thread object
                ClientHandler clientSock = new ClientHandler(client);

                // the threadpool will execute incoming threads
                threadPool.execute(new Thread(clientSock));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ClientHandler class
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        // Constructor
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }

        public void run() {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[3370];
            int length;

            try {
                while ((length = clientSocket.getInputStream().read(buffer)) != -1) {
                    result.write(buffer, 0, (length-1));
                    new WriteToXML(result.toString());
                    result = new ByteArrayOutputStream();
                }
            } catch (IOException e) {
                System.out.println("Client disconnected.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}