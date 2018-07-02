package IO;

import Model.Calendar.Date;
import Model.Task.Task;
import Model.Task.TaskMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class TaskIO {
    public void loadTasksFromFile(String filename, TaskMap taskMap){
            try{
                File fXmlFile = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("task");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Date tmpDate = new Date(Integer.parseInt(eElement.getElementsByTagName("day").item(0).getTextContent()),
                            Integer.parseInt(eElement.getElementsByTagName("month").item(0).getTextContent()),
                            Integer.parseInt(eElement.getElementsByTagName("year").item(0).getTextContent()));
                    Task tmpTask = new Task(eElement.getElementsByTagName("name").item(0).getTextContent(),
                            eElement.getElementsByTagName("description").item(0).getTextContent(),
                            eElement.getElementsByTagName("durability").item(0).getTextContent(),
                            eElement.getElementsByTagName("complexity").item(0).getTextContent(),
                            Boolean.valueOf(eElement.getElementsByTagName("condition").item(0).getTextContent()));
                    taskMap.addTask(tmpDate, tmpTask);
                }
            }
            }catch (Exception e){
                throw new RuntimeException("ErrorTaskLoading");
            }
    }

    public void saveTasksToFile(String filename, TaskMap taskMap){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("tasktable");
            doc.appendChild(rootElement);

            for (Map.Entry<Date, ArrayList<Task>> entry : taskMap.getTaskMap().entrySet()) {
                for (Task temp : taskMap.getTaskMap().get(entry.getKey())) {

                    Element staff = doc.createElement("task");
                    rootElement.appendChild(staff);

                    Element day = doc.createElement("day");
                    day.appendChild(doc.createTextNode(String.valueOf(entry.getKey().getDay())));
                    staff.appendChild(day);

                    Element month = doc.createElement("month");
                    month.appendChild(doc.createTextNode(String.valueOf(entry.getKey().getMonth())));
                    staff.appendChild(month);

                    Element year = doc.createElement("year");
                    year.appendChild(doc.createTextNode(String.valueOf(entry.getKey().getYear())));
                    staff.appendChild(year);

                    Element name = doc.createElement("name");
                    name.appendChild(doc.createTextNode(temp.getName()));
                    staff.appendChild(name);

                    Element description = doc.createElement("description");
                    description.appendChild(doc.createTextNode(temp.getDescription()));
                    staff.appendChild(description);

                    Element durability = doc.createElement("durability");
                    durability.appendChild(doc.createTextNode(temp.getDurability()));
                    staff.appendChild(durability);

                    Element complexity = doc.createElement("complexity");
                    complexity.appendChild(doc.createTextNode(temp.getComplexity()));
                    staff.appendChild(complexity);

                    Element condition = doc.createElement("condition");
                    condition.appendChild(doc.createTextNode(String.valueOf(temp.getCondition())));
                    staff.appendChild(condition);
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filename));
            transformer.transform(source,result);
        } catch (Exception e) {
            throw new RuntimeException("ErrorTaskSaving");
        }
    }
}
