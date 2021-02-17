import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Runner {

	public static void start() {
		File file = new File("data/data.xml");
		new HashMapCountries();
		StringBuilder xml = new StringBuilder();

		if (file.exists()) {

			while (true) {
				System.out.println("Start");
				long startTime = System.currentTimeMillis();

				try {
					FileReader fileReader = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(fileReader);


					xml.append("""
							<?xml version="1.0"?>\r
							<WEATHERDATA>\r
							""");



					String line;
					while ((line = bufferedReader.readLine()) != null) {
						if (!line.contains("</WEATHERDATA>") && !line.contains("<WEATHERDATA>") && !line.contains("<?xml version=\"1.0\"?>")) {
							xml.append(line).append("\r\n");
						}

					}
					xml.append("</WEATHERDATA>");
					new XMLParser(xml.toString());
					xml = new StringBuilder();

					bufferedReader.close();
					long endTime = System.currentTimeMillis();
					System.out.println("That took " + (endTime - startTime) + " miliseconds");
					SendFile.start();
					Thread.sleep(5000);
				} catch (FileNotFoundException ex) {
					System.out.println("Unable to open file '" + file);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
