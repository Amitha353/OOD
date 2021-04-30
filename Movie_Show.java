public class MovieShowService {
	List<CinemaHall> cinema;
	public List<Movie> getMovies(Date date, String city);
	public List<CinemaHall> getCinemaHalls(String city);
}

public class CinemaHall {
	int cinemaHallId;
	String cinemaHallName;
	
	Address address;
	List<Auditorium> auditoriumList;
	
	Map<Date, Movie> getMovies(List<Date> dateList);
	Map<Date, Show> getShows(List<Date> dateList);
}

class Address {
	int zipcode;
	String street;
	String city;
	String state;
	String country;
}

class Auditorium {
	int auditoriumId;
	String auditoriumName;
	int totalSeats;
	List<Show> shows;
}

class Show {
	int showId;
	Movie movie;
	Date startTime;
	Date endTime;
	
	CinemaHall cinemaPlayedAt;
	List<Seat> seats;
}

class Seat {
	int seatId;
	SeatType seatType;
	SeatStatus seatStatus;
	Double price;
}

public enum seatType {
	DELUX, VIP, ECONOMY, OTHER;
}

public enum SeatStatus {
	BOOKED, AVAILABLE, RESERVED, NOT_AVAILABLE;
}

public class Movie {
	String movieName;
	int movieId;
	int durationInMins;
	String language;
	Genre genre;
	Date releaseDate; Map<String, List<Show>> cityShowMap;
}

public enum Genre {
	SCI_FI, DRAMA, ROM_COM, FANTASY;
}

class Person {
	int userId;
	Search searchObj;
}

public class SystemMember extends User {
	Account account;
	String name;
	String email;
	Address address;
}

class Member extends SystemMember {
	public Booking makeBooking(Booking booking);
	public List<Booking> getBooking();
}

class Admin extends SystemMember {
	public boolean addMovie(Movie movie);
	public boolean addShow(Show show);
}

public class Account {
	String username;
	String password;
}

class Search { //open-close principle
	public static Predicate<Movie> filterMovieByName(String name) {
		return p -> p.name == name;
	}
	
	public static Predicate<Movie> filterMovieByGenre(Genre genre) {
		return p -> p.genre == genre;
	}
	
	public static Predicate<Movie> filterMovieByLanguage(String language) {
		return p -> p.language == language;
	}
	
	public static Predicate<Movie> filterMovieByLanguage(Date releaseDate) {
		return p -> p.releaseDate == releaseDate;
	}
	
	public List<Movie> filterMovies(List<Movie> movies, Predicate<Movie> predicate) {
		return movies.stream().filter(predicate).collect(Collectors.<Movie>toList());
	}
	
	//filterMovies(movies, filterMovieByGenre("SCI_FI"))
}

class Booking {
	String bookingId;
	Date bookingDate;
	Member member;
	Show show;
	Auditorium audi;
	BookingStatus bookingStatus;
	double totalAmount;
	List<Seat> seats;
	Payment paymentObj;
	
	public boolean makePayment(Payment payment);
}

public enum BookingStatus {
	REQUESTED, PRENDING, CONFIRMED, CANCELLED;
}

public class Payment {
	double amount;
	Date paymentDate;
	int transactionId;
	PaymentStatus paymentStatus;
}

public enum PaymentStatus {
	UNPAID, PENDING, COMPLETED, DECLINED, CANCELLED, REFUNDED;
}
