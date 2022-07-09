package com.nikitakozhemyako.shema_etaja1.repository;

import com.nikitakozhemyako.shema_etaja1.domain.Room;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomRepositoryTest {

    @Test
    void roomFindAll() throws ParserConfigurationException, TransformerException, IOException, SAXException {
        RoomRepository roomRepository = new RoomRepository();
        List<Room> all = roomRepository.findAll();
        roomRepository.roomFindAll();
        all.forEach(System.out::println);

    }
}