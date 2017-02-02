package ua.nure.shostalo.SummaryTask4.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import ua.nure.shostalo.SummaryTask4.entity.ClassRoom;
import ua.nure.shostalo.SummaryTask4.entity.Room;

public class SortTests {

    private Sorter sorter = new Sorter();

    @Test
    public void enumTest() {
	SortEnum.valueOf(SortEnum.CLASS.toString());
	SortEnum.fromValue("price");
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionEnumTest() {
	SortEnum.fromValue("noPrice");
    }

    @Test
    public void sorterTest() {
	List<Room> rooms = new ArrayList<>();
	rooms.add(new Room(1, 1, "",
		new ClassRoom(1, "roomClass", 1, 1.0, "description")));
	rooms.add(new Room(2, 2, "text",
		new ClassRoom(1, "roomClass1", 2, 2.0, "description1")));
	Collections.sort(rooms, Sorter.SORT_ROOM_BY_CLASS);
	Collections.sort(rooms, Sorter.SORT_ROOM_BY_PLACES);
	Collections.sort(rooms, Sorter.SORT_ROOM_BY_PRICE);
	Collections.sort(rooms, Sorter.SORT_ROOM_BY_STATUS);
    }
}
