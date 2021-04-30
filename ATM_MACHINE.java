Class ATM {
	int atmID;
	Address location;
	CashDispenser cashDispenser;
	Screen screen;
	CardReader cardReader;
	Keypad keypad;
	CashDeposit cashDeposit;
	ChequeDeposit chequeDeposit;
	
	BankService bankService;
}

class Address {
	int zipcode;
	String street;
	String city;
	String state;
	String country;
}

class CashDispenser {
	Map<CashType, List<Cash>> cashAvailable; //50,[123, 56, 32 , 56]
	public void dispenseCash(int amount);
}

public enum CashType {
	FIFTY, HUNDRED, FIVEHUNDRED;
}

public Cash {
	CashType cashType;
	String serialNumber;
}

class Screen {
	public void display(String message);
}

class CardReader {
	public CardInfo fetchDetails();
}

class CardInfo {
	CardType cardType;
	Bank bank;
	String cardNumber;
	Date expiryDate;
	int cvv;
	float withdrawLimit;
}

public enum CardType {
	DEBIT, CREDIT;
}

class Keypad {
	public String getInput();
}

class Bank {
	String name;
	Address location;
	List<ATM> atmList;
}

interface BankService {
	public Boolean isValidUser(String pin, CardIngo cardInfo);
	public Customer getCustomerDetails(CardInfo cardInfo);
	public TransactionDetail executeTransaction(Transaction transactionInfo, Customer customer);
}

Class BankA implements BankService {
	public Boolean isValidUser(String pin, CardIngo cardInfo);
	public Customer getCustomerDetails(CardInfo cardInfo);
	public TransactionDetail executeTransaction(Transaction transactionInfo, Customer customer);
}

Class BankB implements BankService {
	public Boolean isValidUser(String pin, CardIngo cardInfo);
	public Customer getCustomerDetails(CardInfo cardInfo);
	public TransactionDetail executeTransaction(Transaction transactionInfo, Customer customer);
}

class BankServiceFactory {
	//It will return BankA / BankB object so that for transacting we use that.
	public BankService getBankServiceObject(CardInfo cardInfo);
}

class Customer {
	String firstName;
	String lastName;
	CardInfo cardInfo;
	Account account;
	
	BankService bankServiceObj;
	CustomerStatus customerStatus;
}

class Account {
	String accountNumber;
	Float availableBalance;
}

public enum CustomerStatus {
	BLOCKED, ACTIVE, CLOSED;
}
// Multi-level
class Transaction {
	Integer transactionId;
	String sourceAccount;
	Date transactionDate;
}

class Deposit extends Transaction {
	float amount;
}

class ChequeDeposit extends Deposit {
	public Cheque getCheque();
}

class CashDeposit extends Deposit {
	public List<Cash> getCash();
}

class Withdraw extends Transaction {
	float amount;
	
	public List<Cash> withdrawAmount(float Amount) {} 
}

class Transfer extends Transaction {
	String destinationAccount;
	float amount;
}

class TransactionDetail {
	TransactionStatus transactionStatus;
	string sourceAccountNumber;
	Date transactionDate;
	TransactionType transactionType;
	
	int transactionId;
}

public enum TransactionStatus {
	PENDING, CANCELLED, SUCCESS, ERROR;
}

public enum TransactionType {
	WITHDRAW, DEPOSIT, TRANSFER;
}