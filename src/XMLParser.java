import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class XMLParser {

    // initialize a StringBuilder with all the relevant types of data
    static StringBuilder sb = new StringBuilder
            ("STN,DATE,TIME,COUNTRY,TEMP,DEWP,STP,SLP,VISIB,WDSP,PRCP,SNDP,FRSHTT,CLDC,WNDDIR\r\n");

    /**
     * Parses an XML string where it filters the data to only include weather stations from Asia
     * and only weather stations where the temp is lower than 0 degrees Celsius
     * @param xml an input xml string
     */
    public XMLParser(String xml) {
        // initialize the HashMap
        new HashMapCountries();

        try {
            // inputs the XML string into a DocumentBuilder and gets the elements by TagName
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("MEASUREMENT");

            // for loop with the list.length
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    // if the STN is found in the HashMap then proceed
                    if (HashMapCountries.getSTN(Integer.parseInt(eElement.getElementsByTagName("STN").item(0).getTextContent()))) {
                        // if the temp is lower than 0 degrees Celsius then proceed
                        if (Float.parseFloat(eElement.getElementsByTagName("TEMP").item(0).getTextContent()) < 0) {
                            // sends the data to be put into a string separated by comma's
                            sendData(eElement.getElementsByTagName("STN").item(0).getTextContent(),
                                    eElement.getElementsByTagName("DATE").item(0).getTextContent(),
                                    eElement.getElementsByTagName("TIME").item(0).getTextContent(),
                                    // gets the relevant country name and adds it
                                    HashMapCountries.getCountry(Integer.parseInt(eElement.getElementsByTagName("STN").item(0).getTextContent())),
                                    eElement.getElementsByTagName("TEMP").item(0).getTextContent(),
                                    eElement.getElementsByTagName("DEWP").item(0).getTextContent(),
                                    eElement.getElementsByTagName("STP").item(0).getTextContent(),
                                    eElement.getElementsByTagName("SLP").item(0).getTextContent(),
                                    eElement.getElementsByTagName("VISIB").item(0).getTextContent(),
                                    eElement.getElementsByTagName("WDSP").item(0).getTextContent(),
                                    eElement.getElementsByTagName("PRCP").item(0).getTextContent(),
                                    eElement.getElementsByTagName("SNDP").item(0).getTextContent(),
                                    eElement.getElementsByTagName("FRSHTT").item(0).getTextContent(),
                                    eElement.getElementsByTagName("CLDC").item(0).getTextContent(),
                                    eElement.getElementsByTagName("WNDDIR").item(0).getTextContent());
                        }
                    }
                }
            }
            // writes to a csv file
            new WriteToCSV(sb.toString());
            // initialize a new StringBuilder with the relevant types of data
            sb = new StringBuilder("STN,DATE,TIME,COUNTRY,TEMP,DEWP,STP,SLP,VISIB,WDSP,PRCP,SNDP,FRSHTT,CLDC,WNDDIR\r\n");

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds all the param into a StringBuilder with comma's in between
     * @param stn station number
     * @param date the date, format yyyy-mm-dd
     * @param time the time, format hh:mm:ss
     * @param country the country
     * @param temp the temperature in degrees Celsius
     * @param dewp the dewpoint in degrees Celsius
     * @param stp the station air pressure in mbar
     * @param slp the sea level air pressure in mbar
     * @param visib the visibility in km
     * @param wdsp windspeed in km/u
     * @param prcp precipitation in cm
     * @param sndp snowfall in cm
     * @param frshtt events per day, in order: Freezing, Rain, Snow, Hail, Thunder, Tornado
     * @param cldc cloud cover in %
     * @param wnddir wind direction in degrees
     */
    public static void sendData(String stn, String date, String time, String country, String temp, String dewp, String stp, String slp,
                                String visib, String wdsp, String prcp, String sndp, String frshtt, String cldc, String wnddir) {
        sb.append(stn + "," + date + "," + time + "," + country + "," + temp + "," + dewp + "," + stp + "," + slp + "," + visib + ","
                + wdsp + "," + prcp + "," + sndp + "," + frshtt + "," + cldc + "," + wnddir + "\r\n");
    }
}
