class ParkingLot {
	String parkingLotName;
	Address address;
	List<ParkingFloor> parkingFloors;
	List<Entrance> entrances;
	List<Exit> exits;
	
	public boolean isParkingSpaceAvailableForVehicle(Vehicle vehicle);
	public boolean updateParkingAttendant(ParkingAttendant parkingAttendant, int gateId);
}

class ParkingFloor {
	int levelId;
	boolean isFull;
	List<ParkingSpace> parkingSpaces;
	ParkingDisplayBoard parkingDisplayBoard;
}

class Gate {
	int gateId;
	ParkingAttendant parkingAttendant;
}

class Entranced extends Gate {
	public ParkingTicket getParkingTicket(Vehicle vehicle);
}

class Exit extends Gate {
	public ParkingTicket payForParking(ParkingTicket parkingTicket, PaymentTypepaymentType);
}

class Address {
	Sting Country;
	String state;
	String city;
	String street;
	String pincode;
}

class ParkingSpace {
	int spaceId;
	boolean isFree;
	double costPerHour;
	Vehicle vehicle;
	ParkingSpaceType parkingSpaceType;
}

class ParkingDisplayBoard {
	Map<ParkingSpaceType, Integer> freeSpotsAvailableMap;
	public void updateFreeSpotsAvailable(ParkingSpaceType parkingSpaceType, int spaces);
}

class Account {
	String name;
	String email;
	String password;
	String empId;
	Address address;
}

class Addmin extends Account {
	public boolean addParkingFloor(ParkingLot parkingLor, ParkingFloor floor);
	public boolean addParkingSpace(ParkingFloor floor, ParkingSpace parkingSpace);
	public boolean addPArkingDisplayBoard(ParkingFloor floor, ParkingDisplayBoard parkingDisplayBoard);
}

class ParkingAttendent extends Account {
	Payment paymentService;
	public boolean processVehicleEntry(Vehicle vehicle);
	public PaymentInfo processPayment(ParkingTicket parkingTicket, PaymentType paymentType);
}

class Vehicle {
	String licenceNumber;
	VehicleType vehicleType;
	ParkingTicket parkingTicket;
	PaymentInfo paymentInfo;
}

class ParkingTicket {
	int ticketId;
	int levelId;
	int spaceId;
	Date vehicleEntryDateTime;
	Date vehicleExitDateTime;
	ParkingSpaceType parkingSpaceType;
	doubleTotalCost;
	ParkingTicketStatus parkingTicketStatus;
	
	public void updateTotalCost();
	public void updateVehicleExitTime(Date vehicleExitDateTime);
}

public enum PaymentType {
	CASH, CREDIT_CARD, DEBIT_CARD, UPI;
}

public enum ParkingSpaceType {
	BIKE_PARKING, CAR_PARKING, TRUCK_PARKING;
}

class PaymentInfo {
	double amount;
	Date paymentDate;
	int transactionId;
	ParkingTicket parkingTicket;
	PaymentStatus paymentStatus;
}

public enum VehicleType {
	BIKE, CAR, TRUCK;
}

public enum ParkingTicketStatus {
	PAID, ACTIVE;
}

enum PaymentStatus {
	UNPAID, PENDING, COMPLETED, DECLINED, CANCELLED, REFUNDED;
}

