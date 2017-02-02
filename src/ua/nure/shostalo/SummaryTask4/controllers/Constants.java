package ua.nure.shostalo.SummaryTask4.controllers;

/**
 * The class contains the constants.
 * 
 * @author Mikhail Shostalo
 *
 */
public final class Constants {

    /**
     * The private default constructor for constatnts.
     */
    protected Constants() {

	// default constructor
    }

    public static final String PATH_TO_LOG4J_CONFIG = "WEB-INF/log4j.properties";
    public static final String DATA_SOURCE_CONTEXT = "java:comp/env/jdbc/hotel";

    public static final String ATTRIBUTE_ROOM_SERVICE = "ua.nure.shostalo."
	    + "SummaryTask4.services.RoomService";
    public static final String ATTRIBUTE_USER_SERVICE = "ua.nure.shostalo."
	    + "SummaryTask4.services.UserService";
    public static final String ATTRIBUTE_REQUEST_SERVICE = "ua.nure.shostalo."
	    + "SummaryTask4.services.RequestService";
    public static final String ATTRIBUTE_ANSWER_SERVICE = "ua.nure.shostalo."
	    + "SummaryTask4.services.AnswerService";
    public static final String ATTRIBUTE_RESERVE_SERVICE = "ua.nure.shostalo."
	    + "SummaryTask4.services.ReserveService";

    public static final String ATTRIBUTE_ROOMS = "rooms";
    public static final String ATTRIBUTE_ROOM = "room";
    public static final String ATTRIBUTE_ROOM_CLASSES = "classes";
    public static final String ATTRIBUTE_LOCALE = "locale";
    public static final String ATTRIBUTE_PAGE = "page";
    public static final String ATTRIBUTE_BLOCK = "block";
    public static final String ATTRIBUTE_DAYS = "days";
    public static final String ATTRIBUTE_MESSAGE_REGISTRATION = "message";
    public static final String ATTRIBUTE_USER = "user";
    public static final String ATTRIBUTE_LOGIN_MESSAGE = "message";
    public static final String ATTRIBUTE_LOGIN = "login";
    public static final String ATTRIBUTE_REQUEST_LIST = "requests";
    public static final String ATTRIBUTE_ANSWER_LIST = "answers";
    public static final String ATTRIBUTE_REQUEST_COUNT = "requestsCount";
    public static final String ATTRIBUTE_ANSWER_COUNT = "answersCount";
    public static final String ATTRIBUTE_RESERVE_COUNT = "reserveCount";
    public static final String ATTRIBUTE_ARRIVAL = "arrivalDate";
    public static final String ATTRIBUTE_DEPATURE = "depatureDate";
    public static final String ATTRIBUTE_RESERVES = "reserves";

    public static final String PARAMETR_LAST_NAME = "lastName";
    public static final String PARAMETR_NAME = "firstName";
    public static final String PARAMETR_LOGIN = "login";
    public static final String PARAMETR_EMAIL = "inputEmail";
    public static final String PARAMETR_PHONE = "mobilePhone";
    public static final String PARAMETR_PASSWORD = "inputPassword";
    public static final String PARAMETR_USER_ROLE = "client";
    public static final String PARAMETR_STATUS_RESERVE = "booked";
    public static final String PARAMETR_MANAGER_ROLE = "manager";
    public static final String PARAMETR_REQUEST_ARRIVAL = "dateArrive";
    public static final String PARAMETR_REQUEST_DEP = "depDate";
    public static final String PARAMETR_REQUEST_NUMBER_PEOPLE = "numberOfPeople";
    public static final String PARAMETR_REQUEST_CLASS = "roomClass";
    public static final String PARAMETR_REQUEST_ID = "RequestId";
    public static final String PARAMETR_ROOM_ID = "idRoom";
    public static final String PARAMETR_ANSWER_ID = "idAnswer";
    public static final String PARAMETR_RESERVE_ID = "idReserve";
    public static final String PARAMETR_ROOM_PRICE = "priceRoom";
    public static final String PARAMETR_ARRIVAL = "arrival";
    public static final String PARAMETR_DEPATURE = "depature";
    public static final String PARAMETR_SORT = "sort";
    public static final String ENCODING = "UTF-8";
}
