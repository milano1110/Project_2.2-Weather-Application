import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class WriteToCSV {
	String filepath = "send/data.csv";

	public WriteToCSV(String stn) {
		try {
			FileWriter fw = new FileWriter(filepath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println(stn);
			pw.flush();
			pw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
