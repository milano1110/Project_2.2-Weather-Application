import java.io.*;
import java.net.Socket;

public class SendFile {

    private static DataOutputStream dataOutputStream = null;

    public static void start() {

        try (Socket socket = new Socket("localhost", 5000)) {

            while (true) {
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                sendFile();

                Thread.sleep(5000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sendFile() throws IOException {
        int bytes;
        File file = new File("./send/data.csv");

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
            fileInputStream.close();
            file.delete();
        } else {
            System.out.println("No file found");
        }
    }
}
