package SoftA2Movie;

import java.util.HashMap;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
import java.util.ArrayList;



public class Booking {

    private int bookingId;
    public HashMap<String, Integer> seats;
    public HashMap<String, Integer> ages;
    public Customer customer;
    public Showing show;
    public ArrayList<Card> paymentMethod;
    public boolean transaction;
    private double price;
    private LocalDateTime timeOfBooking;

    public Booking(int bookingId, HashMap<String, Integer> seats, HashMap<String, Integer> ages, LocalDateTime timeOfBooking, Customer customer, Showing show, ArrayList<Card> paymentMethod) {
        this.bookingId = bookingId;
        this.seats = seats;
        this.ages  = ages;
        this.timeOfBooking = timeOfBooking;
        this.customer = customer;
        this.show = show;
        this.timeOfBooking = timeOfBooking;
        this.paymentMethod = paymentMethod;
        this.price = 0;
        for (String key : ages.keySet()) {
            if (key.equals("Child")) {
                this.price += this.show.getPrice() * 0.8 * ages.get(key);
            } else if (key.equals("Pensioner")) {
                this.price += this.show.getPrice() * 0.8 * ages.get(key);
            }else if (key.equals("Student")) {
                this.price += this.show.getPrice() * 0.85 * ages.get(key);
            } else if (key.equals("Adult")) {
                this.price += this.show.getPrice() * ages.get(key);
            } 
        }
        this.updateSeatsinShow();
    }

    public int getBookingID(){
        return bookingId;
    }

    public void addPaymentMethod(Card payment) {
        this.paymentMethod.add(payment);
    }

    public Showing getShow() {
        return this.show;
    }

    public LocalDateTime getTimeOfBooking(){
        return timeOfBooking;
    }


    public double getPrice() {
        return this.price;
    }

    public int getNumSeatByAge(String age) {
        for (String key : ages.keySet()) {
			if (key.equals(age)) {
				return ages.get(key);
			}
		}
        return -1;
    }
    public void updateSeatsinShow(){
        // assumes that the creation of booking object is valid -> 
        // number of seats have already been checked
        int totalSeats = this.getTotalSeats();
        this.show.setBookedSeats(show.getBookedSeats()+totalSeats);
        this.show.setAvailableSeats(show.getAvailableSeats() - totalSeats);
        
    }
    public int getTotalSeats(){
        int sum = 0;
        for(int s :this.seats.values()){
            sum += s;
        }
        return sum;
    }
}
