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

    static StringBuilder sb = new StringBuilder("STN,DATE,TIME,COUNTRY,TEMP,DEWP,STP,SLP,VISIB,WDSP,PRCP,SNDP,FRSHTT,CLDC,WNDDIR\r\n");

    public XMLParser(String xml) {
        new HashMapCountries();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("MEASUREMENT");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (HashMapCountries.getSTN(Integer.parseInt(eElement.getElementsByTagName("STN").item(0).getTextContent()))) {
                        if (Float.parseFloat(eElement.getElementsByTagName("TEMP").item(0).getTextContent()) < 0) {
                            sendData(eElement.getElementsByTagName("STN").item(0).getTextContent(),
                                    eElement.getElementsByTagName("DATE").item(0).getTextContent(),
                                    eElement.getElementsByTagName("TIME").item(0).getTextContent(),
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
            new WriteToCSV(sb.toString());
            sb = new StringBuilder("STN,DATE,TIME,COUNTRY,TEMP,DEWP,STP,SLP,VISIB,WDSP,PRCP,SNDP,FRSHTT,CLDC,WNDDIR\r\n");

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public static void sendData(String stn, String date, String time, String country, String temp, String dewp, String stp, String slp,
                                String visib, String wdsp, String prcp, String sndp, String frshtt, String cldc, String wnddir) {
        sb.append(stn + "," + date + "," + time + "," + country + "," + temp + "," + dewp + "," + stp + "," + slp + "," + visib + ","
                + wdsp + "," + prcp + "," + sndp + "," + frshtt + "," + cldc + "," + wnddir + "\r\n");
    }
}
