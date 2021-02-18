import java.io.*;
import java.net.Socket;

public class SendFile {

    private static final File file = new File("send/data.csv");
    private static DataOutputStream dataOutputStream = null;

    public static void start() {

        try (Socket socket = new Socket("10.0.6.4", 5000)) {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            sendFile();
        } catch (IOException e) {
            System.out.println("Unable to connect to server");
        }
    }

    private static void sendFile() throws IOException {
        int bytes;

        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            // send file size
            dataOutputStream.writeLong(file.length());

            // break file into chunks
            byte[] buffer = new byte[4 * 1024];
            while ((bytes = fileInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, bytes);
                dataOutputStream.flush();
            }
            System.out.println("File succesfully send");
            fileInputStream.close();
            file.delete();
        } else {
            System.out.println("No file found");
        }
    }
}
