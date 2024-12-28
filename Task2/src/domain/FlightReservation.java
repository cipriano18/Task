package domain;

import java.time.LocalDate;

public class FlightReservation {

private String reservationId; 
private String passengerName; 
private String origin;    
private String destination; 
private LocalDate flightDate; 
private double price;

public FlightReservation() {
}
public FlightReservation(String reservationId, String passengerName, String origin, String destination,
		LocalDate flightDate, double price) {
	this.reservationId = reservationId;
	this.passengerName = passengerName;
	this.origin = origin;
	this.destination = destination;
	this.flightDate = flightDate;
	this.price = price;
}
public String getReservationId() {
	return reservationId;
}
public void setReservationId(String reservationId) {
	this.reservationId = reservationId;
}
public String getPassengerName() {
	return passengerName;
}
public void setPassengerName(String passengerName) {
	this.passengerName = passengerName;
}
public String getOrigin() {
	return origin;
}
public void setOrigin(String origin) {
	this.origin = origin;
}
public String getDestination() {
	return destination;
}
public void setDestination(String destination) {
	this.destination = destination;
}
public LocalDate getFlightDate() {
	return flightDate;
}
public void setFlightDate(LocalDate flightDate) {
	this.flightDate = flightDate;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
@Override
public String toString() {
	return " ID " + reservationId + " Nombre " + passengerName + " Origen "
			+ origin + " Destino " + destination + " Fecha " + flightDate + " Precio " + price;
}

}
