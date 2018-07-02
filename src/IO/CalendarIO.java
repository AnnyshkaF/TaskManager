package IO;

import Model.Calendar.Calendar;
import Model.Calendar.LoadedMonth;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class CalendarIO {
    public void loadCalendarFromFile(String fileCalendar, Calendar calendar) {
        try {
            File fXmlFile = new File(fileCalendar);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("month");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                org.w3c.dom.Node nNode = nList.item(temp);

                String[] data = nNode.getFirstChild().getTextContent().split(" ");
                calendar.getLoadedMonthsArray().add(new LoadedMonth(
                        Integer.parseInt(data[0]),      //Month (1-12)
                        Integer.parseInt(data[1]),      //Year
                        Integer.parseInt(data[2]),      //FirstDay (0 - 6)
                        Integer.parseInt(data[3]),      //DaysInMonth (1-31)
                        Integer.parseInt(data[4])));    //WeeksInMonth  (from zero) 4-5

                //shows dates base
                if (Integer.parseInt(data[0]) < 0 || Integer.parseInt(data[0]) > 13) {
                    throw new RuntimeException("Wrong Month in DateBase");
                }
                if (Integer.parseInt(data[2]) < 0 || Integer.parseInt(data[2]) > 6) {
                    throw new RuntimeException("Wrong Day in DateBase");
                }
                if (Integer.parseInt(data[3]) < 0 || Integer.parseInt(data[3]) > 31) {
                    throw new RuntimeException("Wrong DaysInMonth in DateBase");
                }
                if (Integer.parseInt(data[4]) < 0 || Integer.parseInt(data[4]) > 6) {
                    throw new RuntimeException("Wrong WeeksInMonth in DateBase");
                }
            }
        }catch (Exception e){
            throw new RuntimeException("ErrorCalendarLoading");
        }
    }
}
