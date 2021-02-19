import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiveFile {
    private static DataInputStream dataInputStream = null;

    /**
     * The main method for the ReceiveFile server
     * @param args
     */
    public static void main(String[] args) {
        // tries to open port 5000
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Listening to port: 5000");

            // infinite loop for receiving client requests
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println(clientSocket + " connected.");

                dataInputStream = new DataInputStream(clientSocket.getInputStream());

                receiveFile();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file received");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Receives a file from the client
     * @throws IOException throws when there is no file found
     */
    private static void receiveFile() throws IOException {
        int bytes;
        FileOutputStream fileOutputStream = new FileOutputStream("receive/data.csv");

        long size = dataInputStream.readLong();     // read file size
        byte[] buffer = new byte[4*1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer,0,bytes);
            size -= bytes;      // read upto file size
        }
        fileOutputStream.close();
    }
}
