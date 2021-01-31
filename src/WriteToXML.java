import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class WriteToXML {

    String filepath = "data/data.xml";

    public WriteToXML(String xml) {
        try {
            FileWriter fileWriter = new FileWriter(filepath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(xml);
            printWriter.flush();
            printWriter.close();

        } catch (Exception e) {
            System.out.println(xml + " niet verstuurd!");
        }
    }
}
