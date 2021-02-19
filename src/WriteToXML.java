import java.io.*;

public class WriteToXML {

    // get the filepath where to put the data
    String filepath = "data/data.xml";

    /**
     * Writes the file to an XML file
     * @param xml XML string
     */
    public WriteToXML(String xml) {
        try {
            FileWriter fileWriter = new FileWriter(filepath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(xml);
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
