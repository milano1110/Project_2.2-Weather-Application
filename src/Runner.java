import java.io.*;

public class Runner {

	/**
	 * The main method for the Runner class
	 * @param args
	 */
	public static void main(String[] args) {

		// finds the file in the specified path
		File file = new File("data/data.xml");
		// initialize the HashMap
		new HashMapCountries();
		// makes a new StringBuilder
		StringBuilder xml = new StringBuilder();

		// infinite loop for processing the file
		while (true) {
			if (file.exists()) {
				// starts a timer
				System.out.println("Start");
				long startTime = System.currentTimeMillis();

				try {
					// reads the XML file
					FileReader fileReader = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(fileReader);

					// adds the XML tags to the StringBuilder
					xml.append("<?xml version=\"1.0\"?>\r\n" + "<WEATHERDATA>\r\n");

					String line;
					// while the BufferedReader has a next line
					while ((line = bufferedReader.readLine()) != null) {
						if (!line.contains("</WEATHERDATA>") && !line.contains("<WEATHERDATA>") && !line.contains("<?xml version=\"1.0\"?>")) {
							// add the BufferedReader lines to the StringBuilder
							xml.append(line).append("\r\n");
						}
					}

					// adds the closing XML tag to the StringBuilder
					xml.append("</WEATHERDATA>");
					// parses the StringBuilder as a string
					new XMLParser(xml.toString());
					// initialize a new StringBuilder
					xml = new StringBuilder();

					bufferedReader.close();
					// deletes the original XML file
					file.delete();
					// ends the timer and display a message how long it took
					long endTime = System.currentTimeMillis();
					System.out.println("That took " + (endTime - startTime) + " miliseconds");

					// sends the file to the receiving server
					SendFile.start();

					// wait for 5 seconds and then do it again
					Thread.sleep(5000);
				} catch (FileNotFoundException ex) {
					System.out.println("Unable to open file '" + file);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				// if there is no file, print message, and wait 5 sec
				System.out.println("No file found");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}