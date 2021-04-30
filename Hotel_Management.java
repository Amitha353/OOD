class Hotel {
	String name;
	Integer id; //UUID
	Location hotelLocation;
	List<Room> roomList;
}

class Location {
	int zipcode;
	String street;
	String area;
	String city;
	String country;
}

class Room {
	String roomNumber;
	RoomStyle roomStyle;
	RoomStatus roomStatus;
	Double bookingPrice;
	List<RoomKey> roomKeys;
	List<HouseKeepingLog> houseKeepingLogs;
}

public enum RoomStyle {
	STANDARD, DELUX, FAMILY_SUITE;
}

public enum RoomStatus {
	AVAILABLE, RESERVED, NOT_AVAILABLE, OCCUPIED, SERVICE_IN_PROGRESS;
}

class RoomKey {
	String keyId;
	String barCode;
	Date issuedAt;
	Boolean isActive;
	Boolean isMaster;
	
	public void assignRoom(Room room);
}

class HouseKeepingLog {
	String description;
	Date startDate;
	int duration;
	HouseKeeper houseKeeper;
	
	public void addRoom(Room room);
}

class Person {
	String name;
	Account accountDetail;
	String phone;
}

public class Account {
	String username;
	String password;
	
	AccountStatus accountStatus;
}

public enum AccountStatus {
	ACTIVE, CLOSED, BLOCKED;
}

class HouseKeeper extends Person {
	public List<Room> getRoomServiced(Date data);
}

class Guest extends Person {
	Search searchObj;
	Booking bookingObj;
	
	public List<RoomBooking> getAvailableRoomBookings();
}

class Receptionist extends Person {
	Search searchObj;
	Booking bookingObj;
	
	public void checkInGuest(Guest guest, RoomBooking bookingInfo);
	public void checkOutGuest(Guest guest, RoomBooking bookingInfo);
}

class admin extends Person {
	public void addRoom(Room roomDetails);
	public Room deleteRoom(Room roomId);
	public void editRoom(Room roomDetail);
}

class search {
	public List<Room> searchRoom(RoomStyle roomStyle, Data startDate, int duration);
}

class Booking {
	public RoomBooking createBooking(Guest guestInfo);
	public RoomBooking cancelBooking(int bookingId);
}

class RoomBooking {
	String bookingId;
	Date startDate;
	int durationInDays;
	BookingStatus bookingStatus;
	List<Guest> guestList;
	List<Room> roomInfo;
	BaseRoomCharge totalRoomCharges;
}

/**
* A decorator pattern allows a user to add new functionality to an existing object without altering its structure.
* Decorator pattern is used to decorate the price here
*/
interface BaseRoomCharge {
	Double getCost();
}

class RoomCharge implements BaseRoomCharge {
	double cost;
	Double getCost() {
		return cost;
	}
	void setCost(double cost) {
		this.cost = cost;
	}
}

class RoomServiceCharge implements BaseRoomCharge {
	
	double cost;
	BaseRoomCharge baseRoomCharge;
	Double getCost() {
		baseRoomCharge.setCost(baseRoomCharge.getCost() + cost);
		return baseRoomCharge.getCost();
	}
}

class InRoomPurchaseCharges implements BaseRoomCharge {
	
	double cost;
	BaseRoomCharge baseRoomCharge;
	Double getCost() {
		baseRoomCharge.setCost(baseRoomCharge.getCost() + cost);
		return baseRoomCharge.getCost();
	}
}
------------------------------
https://www.decipherzone.com/blog-detail/decorator-design-pattern-java-example

