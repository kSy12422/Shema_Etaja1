package com.nikitakozhemyako.shema_etaja1.repository;

import com.nikitakozhemyako.shema_etaja1.domain.Room;
import org.springframework.stereotype.Repository;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomRepository {



    public List<Room> findAll() throws ParserConfigurationException, IOException, SAXException {
        ArrayList<Room> rooms = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        Document document = builder.parse(new File("data.xml"));
        NodeList roomsElement = document.getDocumentElement().getElementsByTagName("room");
        // Перебор всех элементов employee
        for (int i = 0; i < roomsElement.getLength(); i++) {
            Node employee = roomsElement.item(i);
            // Получение атрибутов каждого элемента
            NamedNodeMap attributes = employee.getAttributes();
            // Добавление сотрудника. Атрибут - тоже Node, потому нам нужно получить значение атрибута с помощью метода getNodeValue()
            rooms.add(new Room(
                    Integer.parseInt(attributes.getNamedItem("numberFloor").getNodeValue()),
                    Integer.parseInt(attributes.getNamedItem("numberRoom").getNodeValue()),
                    attributes.getNamedItem("predestination").getNodeValue(),
                    Double.parseDouble(attributes.getNamedItem("square").getNodeValue()),
                    Integer.parseInt(attributes.getNamedItem("countDoor").getNodeValue()),
                    Integer.parseInt(attributes.getNamedItem("countWindow").getNodeValue()),
                    Boolean.parseBoolean(attributes.getNamedItem("conditioner").getNodeValue())

            ));
        }
        return rooms;
    }
    public void roomWhriteAll(List<Room> list) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("rooms");
        doc.appendChild(rootElement);



        for (Room r: list
             ) {
            Element room = doc.createElement("room");
            // add staff to root
            rootElement.appendChild(room);
            room.setAttribute("numberFloor", String.valueOf(r.getNumberFloor());
            room.setAttribute("numberRoom", String.valueOf(r.getNumberRoom());
            room.setAttribute("predestination", String.valueOf(r.getPredestination());
            room.setAttribute("square", String.valueOf(r.getSquare());
            room.setAttribute("countDoor", String.valueOf(r.getCountDoor());
            room.setAttribute("countWindow", String.valueOf(r.getCountWindow());
            room.setAttribute("conditioner", String.valueOf(r.getCountDoor());
        }
        // add xml attribute


        //...create XML elements, and others...

        // write dom document to a file
        try (FileOutputStream output =
                     new FileOutputStream("data.xml")) {
            writeXml(doc, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeXml(Document doc, OutputStream output) throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}
