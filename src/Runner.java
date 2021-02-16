import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Runner {

	public static void start() {
		File file = new File("data/data.xml");
		new HashMapCountries();
		String line;
		StringBuilder sb = new StringBuilder();

		if (file.exists()) {
			System.out.println("Start");
			long startTime = System.currentTimeMillis();

			try {
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);

				sb.append("""
						<?xml version="1.0"?>\r
						<WEATHERDATA>\r
						""");

				while ((line = bufferedReader.readLine()) != null) {
					if (!line.contains("</WEATHERDATA>") && !line.contains("<WEATHERDATA>") && !line.contains("<?xml version=\"1.0\"?>")) {
						sb.append(line).append("\r\n");
					}

				}
				sb.append("</WEATHERDATA>");
				new XMLParser(sb.toString());
				sb = new StringBuilder();

				bufferedReader.close();
				long endTime = System.currentTimeMillis();
				System.out.println("That took " + (endTime - startTime) + " miliseconds");
			} catch (FileNotFoundException ex) {
				System.out.println("Unable to open file '" + file);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
