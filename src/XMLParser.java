import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLParser {

    public static void main(String[] args) {

        try {
            File inputFile = new File("./UnwdmiGenerator/output.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("MEASUREMENT");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element : " + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Station number : "
                            + eElement
                            .getElementsByTagName("STN")
                            .item(0)
                            .getTextContent());
                    System.out.println("Date : "
                            + eElement
                            .getElementsByTagName("DATE")
                            .item(0)
                            .getTextContent());
                    System.out.println("Time : "
                            + eElement
                            .getElementsByTagName("TIME")
                            .item(0)
                            .getTextContent());
                    System.out.println("Temperature : "
                            + eElement
                            .getElementsByTagName("TEMP")
                            .item(0)
                            .getTextContent() + " C");
                    System.out.println("Dewpoint : "
                            + eElement
                            .getElementsByTagName("DEWP")
                            .item(0)
                            .getTextContent() + " C");
                    System.out.println("Air pressure station level: "
                            + eElement
                            .getElementsByTagName("STP")
                            .item(0)
                            .getTextContent() + " mbar");
                    System.out.println("Air pressure sea level : "
                            + eElement
                            .getElementsByTagName("SLP")
                            .item(0)
                            .getTextContent() + " mbar");
                    System.out.println("Visibility : "
                            + eElement
                            .getElementsByTagName("VISIB")
                            .item(0)
                            .getTextContent() + " km");
                    System.out.println("Windspeed : "
                            + eElement
                            .getElementsByTagName("WDSP")
                            .item(0)
                            .getTextContent() + " km");
                    System.out.println("Precipitation : "
                            + eElement
                            .getElementsByTagName("PRCP")
                            .item(0)
                            .getTextContent() + " cm");
                    System.out.println("Snowfall : "
                            + eElement
                            .getElementsByTagName("SNDP")
                            .item(0)
                            .getTextContent() + " cm");
                    System.out.println("Gebeurtenissen op deze dag, cummulatief, binair uitgedrukt. : "
                            + eElement
                            .getElementsByTagName("FRSHTT")
                            .item(0)
                            .getTextContent());
                    System.out.println("Cloud cover : "
                            + eElement
                            .getElementsByTagName("CLDC")
                            .item(0)
                            .getTextContent() + " %");
                    System.out.println("Wind Direction : "
                            + eElement
                            .getElementsByTagName("WNDDIR")
                            .item(0)
                            .getTextContent() + " degrees");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}