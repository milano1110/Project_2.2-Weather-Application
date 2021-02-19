import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    /**
     * The main method for the Server class
     * @param args
     */
    public static void main(String[] args) {
        // makes a threadpool with 1000 threads
        ExecutorService threadPool =
                Executors.newFixedThreadPool(1000);

        // tries to open port 7789
        try (ServerSocket server = new ServerSocket(7789)) {

            server.setReuseAddress(true);

            // running infinite loop for getting client request
            while (true) {

                // socket object to receive incoming client requests
                Socket client = server.accept();

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

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        /**
         * Constructor for the ClientHandler class
         * @param socket the socket for the incoming client
         */
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }

        /**
         * Run method for the ClientHandler class
         */
        public void run() {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[3370];
            int length;

            try {
                // while there is an input stream
                while ((length = clientSocket.getInputStream().read(buffer)) != -1) {
                    result.write(buffer, 0, (length-1));
                    // writes the input stream to XML file
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