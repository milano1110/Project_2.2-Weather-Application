import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class WriteToCSV {

	// get the filepath where to put the data
	String filepath = "send/data.csv";

	/**
	 * Writes an XML string to a CSV file
	 * @param xml XML string
	 */
	public WriteToCSV(String xml) {
		try {
			FileWriter fw = new FileWriter(filepath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println(xml);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
