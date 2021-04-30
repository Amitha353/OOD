class Product {
	String name;
	int productId;
	float price;
}

class Inventory {
	Map<Product, Integer> productCountMap;
	Map<Integer, Product> aisleProductMap;
	Stack<Integer> availableAisle;
	
	public void addProduct(Product p) {
		if(productCountMap.containsKey(p)) {
			productCountMap.put(p, productCountMap.getOrDefault(p, 0)+1);
		} else {
			if(!availableAisle.isEmpty()) {
				int aisleNumber = stack.pop();
			} else {
				throw new Exception("No aisle to add New product");
			}
			aisleProductMap.put(aisleNumber, p);
			aisleProductMap.put(p, 1);
		}
	} 
}

public interface IState {
	Product dispense();
	void insertCoin();
	void pressButton();
}

public class NoCoinInsertedState implements State {
	VendingMachine vendingMachine;
	
	public NoCoinInsertedState(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}
	
	public void insertCoin(){
		machine.setAmount();
		machine.setState(machine.getCoinInsertedState());

	}

	public void pressButton(){
		throw new MachineException("No coin inserted");
	}

	public Product dispense(){
		throw new MachineException("No coin inserted");
	}
}

public class CoinInsertedState implements State {
	VendingMachine vendingMachine;
	
	public CoinInsertedState(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}
	
	public void insertCoin(){
		throw new MachineException("Coint already inserted");

	}

	public void pressButton(int aisleNumber){
		if(vendingMachine.checkIfProductAvilable(aisleNumber))
			vendingMachine.setState(vendingMachine.getDispenseState());
		else
			throw new MachineException("Product not avilable.")
	}

	public Product dispense(){
		throw new MachineException("Button yet to press");
	}
}

public class DispensingState implements State {
	VendingMachine vendingMachine;
	
	public DispensingState(VendingMachine vendingMachine) {
		this.vendingMachine = vendingMachine;
	}
	
	public void insertCoin(){
		throw new MachineException("Please wait while product is dispensing");

	}

	public void pressButton(int aisleNumber){
		throw new MachineException("Please wait while product is dispensing");
	}

	public Product dispense(){
		vendingMachine.getProductAt(aisleNumber);
		vendingMachine.setState(vendingMachine.getNoCoinInsertedState());
	}
}

public class VendingMachine{
	NoCoinInsertedState noCoinInsertedState	= new NoCoinInsertedState(this);
	CoinInsertedState coinInsertedState = new CoinInsertedState(this);
	DispensingState dispensingState = new DispensingState(this);
	
	State machineState = null;
	int loadedAmount = 0;
	Inventory inventory;
	
	public VendingMachine(){
		machineState = noCoinInsertedState;
		inventory.loadProduct(List<Product> products);
	}

	public void insertCoin(int amount){
		machineState.insertCoin();
	}

	public void pressButton(){
		machineState.pressButton();
		machineState.dispense();
	}
}