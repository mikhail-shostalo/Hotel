package ua.nure.shostalo.SummaryTask4.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EntitiesTest {

    ClassRoom classRoom;
    Room room;
    Answer answer;
    Reserve reserve;
    Request request;
    User user;

    @Before
    public void init() {
	classRoom = new ClassRoom(1, "roomClass", 1, 1.0, "description");
	room = new Room(1, 1, "status", classRoom);
	answer = new Answer(1, room, "arrivalDate", "depDate", 1.0);
	request = new Request(classRoom, "login", 1, "arrivalDate",
		"dateOfDeparture");
	request = new Request(1, classRoom, "login", 1, "arrivalDate",
		"dateOfDeparture");
	reserve = new Reserve(room, "dateReserve", "arrivalDate", "depDate",
		"statusReserve", 1.0);
	reserve = new Reserve(1, room, "dateReserve", "arrivalDate", "depDate",
		"statusReserve", 1.0);
	user = new User("name", "lastName", "login", "password", "email",
		"mobilePhone", "role");
	Language.valueOf(Language.EN.toString());
	ReserveStatus.valueOf(ReserveStatus.BOOKED.toString());
	StatusRoom.valueOf(StatusRoom.FREE.toString());
    }

    @Test
    public void setTest() {
	classRoom.setId(1);
	classRoom.setDescription("description");
	classRoom.setNumberOfPlace(1);
	classRoom.setPrice(1.0);
	classRoom.setRoomClass("roomClass");
	room.setId(1);
	room.setClassRoom(classRoom);
	room.setRoomNumber(1);
	room.setStatus("status");
	answer.setId(1);
	answer.setPrice(1.9);
	answer.setRoom(room);
	answer.setArrivalDate("arrivalDate");
	answer.setDateOfDeparture("dateOfDeparture");
	request.setId(1);
	request.setClassRoom(classRoom);
	request.setArrivalDate("arrivalDate");
	request.setDateOfDeparture("dateOfDeparture");
	request.setLogin("login");
	request.setNumberOfPlace(1);
	reserve.setId(1);
	reserve.setArrivalDate("arrivalDate");
	reserve.setDateOfDepartures("dateOfDepartures");
	reserve.setDateReserve("dateReserve");
	reserve.setPrice(1.0);
	reserve.setRoom(room);
	reserve.setStatusReserve("statusReserve");
	user.setEmail("email");
	user.setLastName("lastName");
	user.setLogin("login");
	user.setMobilePhone("mobilePhone");
	user.setName("name");
	user.setPassword("password");
	user.setRole("role");
	Language.fromValue("ru");
	Language.fromValue("en_US");
	ReserveStatus.fromValue("booked");
	StatusRoom.fromValue("reserved");
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumReserveException() {

	ReserveStatus.fromValue("nbooked");
    }

    @Test(expected = IllegalArgumentException.class)
    public void enumStatusException() {
	StatusRoom.fromValue("nreserved");
    }

    @Test
    public void dateManagementTest() {
	DateManagement dateManagement = new DateManagement();
	Integer actual = 2;
	Integer expected = DateManagement.getDays("2017-01-28", "2017-01-30");
	assertEquals(expected, actual);
	Double expected1 = DateManagement.getPrice("2017-01-28", "2017-01-30",
		1.0);
	Double actual1 = 2.0;
	Integer expected2 = DateManagement.getDays("2017-01-28", "2017-01-28");
	Integer actual2 = 1;
	assertEquals(expected2, actual2);
	assertEquals(expected, actual);

    }

    @Test
    public void getTest() {
	classRoom.getId();
	classRoom.getDescription();
	classRoom.getNumberOfPlace();
	classRoom.getPrice();
	classRoom.getRoomClass();
	room.getId();
	room.getClassRoom();
	room.getRoomNumber();
	room.getStatus();
	answer.getId();
	answer.getPrice();
	answer.getRoom();
	answer.getArrivalDate();
	answer.getDateOfDeparture();
	request.getId();
	request.getClassRoom();
	request.getArrivalDate();
	request.getDateOfDeparture();
	request.getLogin();
	request.getNumberOfPlace();
	reserve.getId();
	reserve.getArrivalDate();
	reserve.getDateOfDepartures();
	reserve.getDateReserve();
	reserve.getPrice();
	reserve.getRoom();
	reserve.getStatusReserve();
	user.getEmail();
	user.getLastName();
	user.getLogin();
	user.getMobilePhone();
	user.getName();
	user.getPassword();
	user.getRole();
	StatusRoom.getValue(StatusRoom.FREE);
	ReserveStatus.getValue(ReserveStatus.BOOKED);
    }

}
