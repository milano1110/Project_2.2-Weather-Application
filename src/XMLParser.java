import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class XMLParser {

    private int stn;
    private byte frshht;
    private short wnddir;
    private long timestamp;
    private String date, time;
    private float temp, dewp, stp, slp, visib, wdsp, prcp, sndp, cldc;

    public void parse(String xml) throws ParserConfigurationException, IOException, SAXException {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("MEASUREMENT");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    setStn(Integer.parseInt(eElement.getElementsByTagName("STN").item(0).getTextContent()));
                    setFrshht(Byte.parseByte(eElement.getElementsByTagName("FRSHHT").item(0).getTextContent()));
                    setStn(Integer.parseInt(eElement.getElementsByTagName("STN").item(0).getTextContent()));
                    setStn(Integer.parseInt(eElement.getElementsByTagName("STN").item(0).getTextContent()));

                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    // getters
    public int getStn() {
        return stn;
    }

    public byte getFrshht() {
        return frshht;
    }

    public short getWnddir() {
        return wnddir;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public float getTemp() {
        return temp;
    }

    public float getDewp() {
        return dewp;
    }

    public float getStp() {
        return stp;
    }

    public float getSlp() {
        return slp;
    }

    public float getVisib() {
        return visib;
    }

    public float getWdsp() {
        return wdsp;
    }

    public float getPrcp() {
        return prcp;
    }

    public float getSndp() {
        return sndp;
    }

    public float getCldc() {
        return cldc;
    }

    // setters
    public void setStn(int stn) {
        this.stn = stn;
    }

    public void setFrshht(byte frshht) {
        this.frshht = frshht;
    }

    public void setWnddir(short wnddir) {
        this.wnddir = wnddir;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public void setDewp(float dewp) {
        this.dewp = dewp;
    }

    public void setStp(float stp) {
        this.stp = stp;
    }

    public void setSlp(float slp) {
        this.slp = slp;
    }

    public void setVisib(float visib) {
        this.visib = visib;
    }

    public void setWdsp(float wdsp) {
        this.wdsp = wdsp;
    }

    public void setPrcp(float prcp) {
        this.prcp = prcp;
    }

    public void setSndp(float sndp) {
        this.sndp = sndp;
    }

    public void setCldc(float cldc) {
        this.cldc = cldc;
    }
}
