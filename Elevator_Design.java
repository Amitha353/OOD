class Lobby {
	int buildingId;
	int floorId;
	String lobbyName;
	List<Elevator> elevators;
}

class Elevator {
	int elevatorId;
	Floor currentFloor;
	PriorityQueue<Floor> destinations;
	ElevatorStatus elevatorStatus; //ACTIVE, IDLE, MAINTAINENCE, LOCKED
	float capacity;
	Direction direction;// UP, DOWN
	List<Button> button;
	final static int MIN_FLOOR_ID = 0;
	final static int MAX_FLOOR_ID = 20;
}

public interface IElevatorOperation {
	public void moveUp();
	public void moveDown();
	public boolean addDestination(Request request);
	public void processFloor();
}

class ElevatorOperation implements IElevatorOperation {
	Elevator elevator;
	
	public ElevatorOperation(Elevator elevator) {
		this.elevator = elevator;
	}
	
	public void moveUp() {
		if(elevator.currentFloor < elevator.MAX_FLOOR_ID) {
			elevator.currentFloor++;
		}
	}
	
	public void moveDown() {
		if(elevator.currentFloor > elevator.MIN_FLOOR_ID) {
			elevator.currentFloor--;
		}
	}
	
	public boolean addDestination(Request request) {
		Floor targetFloor = request.floor;
		if(destination.isEmpty()) {
			if(currentFloor.floorId < target.floorId) {
				direction = Direction.UP;
				destinations = new PriorityQueue<>();
			} else {
				direction = Direction.DOWN;
				destinations = new PriorityQueue<>((a, b) -> b.floorId - a.floorId);
			}
			elevatorStatus = ElevatorStatus.ACTIVE;
			destinations.add(targetFloor);
			return true;
		} else if(direction == Direction.UP) {
			if(target.floorId > currentFloor && target.floorId < MAX_FLOOR_ID && direction == request.direction) {
				destinations.add(targetFloor);
				return true;
			}
		} else if(direction == Direction.DOWN) {
			if(target.floorId < currentFloor && target.floorId > MIN_FLOOR_ID
			&& direction == request.direction) {
				destinations.add(targetFloor);
				return true;
			}
		}
		return false;
	}
	
	public void processFloor() {
		if(elevator.destinations.isEmpty()) {
			return;
		}
		
		while(!elevator.destinations.isEmpty() && elevator.destinations.peek() == elevator.currentFloor) {
			System.out.println(elevator.destinations.pollFirst());
		}
	elevator.elevatorStatus = elevator.destinations.isEmpty() ? ElevatorStatus.IDLE : elevator.elevatorStatus;
	}
}

class Request {
	Floor floor;
	Direction direction;
}

class Floor {
	int floorId;
	FloorStatus floorStatus; // NORMAL, MAINTAINENCE, ADMIN, HR-ONLY
}

public enum ElevatorStatus {
	ACTIVE, IDLE, MAINTAINENCE, LOCKED;
}

public enum Direction {
	UP, DOWN;
}

public interface Button {
	int buttonId;
	public void press(int buttonId);
}

class RegularButton implements Button {
	int buttonId;
	
	public void press(int buttonId){} //move to that floor
}

class DoorControl implements Button {
	int buttonId;
	
	public void press(int buttonId){}
}

class Emergency implements Button {
	int buttonId;
	
	public void press(int buttonId) {} //alerts and rings the alarm
}

abstact class Entity {
	int entityId;
	int capacity;

	public boolean entityLiftEligibility(int entityId) {}//check if the entity on lift is safe
}

class User extends Entity {
	String name;
	Address address;
}

class MaintenanceWorker extends User {
	ElevatorKey key;
	
	public boolean lockElevator(ElevatorKey key); //Maintenance;
	public boolean unlockElevator(ElevatorKey key);
}

class ElevatorKey {
	String keyId;
	int ElevatorId;
	boolean isMaster;
}