import java.io.*;
import java.net.Socket;

public class SendFile {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void start() {
        while (true) {
            try (Socket socket = new
                    Socket("localhost", 5000)) {
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                sendFile("./data/data.xml");

                dataInputStream.close();
                dataInputStream.close();
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void sendFile(String path) throws Exception{
        int bytes;
        if (path != null) {
            File file = new File(path);
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
        }
    }
}
