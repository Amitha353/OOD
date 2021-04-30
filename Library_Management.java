/*
* Class for the bases system
* One by one start defining complex objects defined in the base system.
* Actors
* Usecases
*/

class Library {
	String name;
	Address location;
	List<BookItem> books;
}

class Address {
	String streetnumber;
	String city;
	String state;
	String country;
	int zipcode;
}

class Book {
	String uniqueIdNumber;
	String title;
	List<Author> authors;
	BookType bookType;
}

class BookItem extends Book{
	String barcode;
	Date publicationDate;
	Rack rackLocation;
	BookStatus bookStatus;
	BookFormat bookformat;
	Date issueDate;
}

public enum BookType {
	SCI_FI, ROMATIC, FANTASY, DRAMA;
}

public enum BookFormat {
	HARDCOVER, PAPERBACK, NEWSPAPER, JOURNAL;
}

public enum BookStatus {
	ISSUED, AVAILABLE, RESERVED, LOST;
}

class Rack {
	int number;
	String locationId;
}

class Person {
	String firstName;
	String lastName;
}

class Author extends Person {
	List<Book> booksPusblished;
}

class SystemUser extends Person {
	String email;
	String phoneNumber;
	Account account;
}

class Member extends SystemUser {
	int totalBookCheckedOut;
	Search searchObj;
	BookIssueService issueService;
}

class Librarian extends SystemUser {
	Search searchObj;
	BookIssueService issueService;
	
	public void addBookItem(BookItem bookItem);
	public BookItem deleteBookItem(String barcode);
	public BookItem editBookItem(BookItem bookItem);
}

class Account {
	String userName;
	String password;
	int accountId;
}

class Search { //open-close principle
	public static Predicate<Book> filterBookByTitle(String title) {
		return p -> p.title == title;
	}
	
	public static Predicate<Book> filterBookByAuthor(String author) {
		return p -> p.stream().filter(p.author == author).findfirst().get();
	}
	
	public static Predicate<Book> filterBookByType(BookType type) {
		return p -> p.type == type;
	}
	
	public List<Book> filterBooks(List<Book> books, Predicate<Book> predicate) {
		return books.streams().filter(predicate).collect(Collectors.<Book>toList());
	}
}

class BookIssueService {
	FineService fineService;
	
	public BookReservationDetail getReservationDetail(BookItem book);
	public void updateReservationDetail(BookReservationDetail bookReservationDetail);
	public BookReservationDetail reserveBook(BookItem book, System user);
	public BookIssueDetail issueBook(BookItem book, SystemUser user);
	public BookIssueDetail renewBook(BookItem book, SystemUser user);
	public void returnBook(BookItem book, SystemUser user);
}

class Fine {
	Date fineDate;
	BookItem book;
	SystemUser user;
	Double fineValue;
}
