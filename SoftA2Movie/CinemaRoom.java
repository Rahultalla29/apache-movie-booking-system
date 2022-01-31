package SoftA2Movie;
// import java.util.HashMap;

public class CinemaRoom {

    private Cinema cinema;
    private String roomType;
    private int numSeats;
    private int available;
    private int booked;
    private int front;
    private int middle;
    private int back;
    // public HashMap<String, int> bookedSeats

    public CinemaRoom(Cinema cinema, String roomType) {
        this.cinema = cinema;
        this.roomType = roomType;
        this.numSeats = 30;
        this.available = 30;
        this.booked = this.numSeats - this.available;
        this.front = 10;
        this.middle = 10;
        this.back = 10;
        // this.bookedSeats = new HashMap<String, int>();
        // this.bookedSeats.put("front", 10);
        // this.bookedSeats.put("middle", 10);
        // this.bookedSeats.put("back", 10);
    }

    // ######################
    // ### GETTER METHODS ###
    // ######################

    // GET CINEMA
    public Cinema getCinema() {
        return this.cinema;
    }

    // GET CINEMA ROOM TYPE
    public String getRoomType() {
        return this.roomType;
    }

    // GET TOTAL SEATS 
    public int getTotalSeats() {
        return this.numSeats;
    }
    
    // GET TOTAL AVAILABLE SEATS NO.
    public int getAvailableSeats() {
        return this.available;
    }

    // GET TOTAL BOOKED SEATS NO.
    public int getBookedSeats() {
        return this.booked;
    }

    // GET FRONT AVAILABLE SEATS NO.
    public int getFrontSeats() {
        return this.front;
    }

    // GET MIDDLE AVAILABLE SEATS NO.
    public int getMiddleSeats() {
        return this.middle;
    }

    // GET BACK AVAILABLE SEATS NO.
    public int getBackSeats() {
        return this.back;
    }

    // ######################
    // ### SETTER METHODS ###
    // ######################

    // REDUCE AVAILABLE SEATS BY 1
    /*public void setAvailableSeats(String section) {
        this.available -= 1;
        if (section.equals("front")) {
            this.front -= 1;
        } else if (section.equals("middle")) {
            this.middle -= 1;
        } else if (section.equals("back")) {
            this.back -= 1;
        } else {
            System.out.println("Incorrect Section Has Been Passed In");
        }
        
    }*/

    // // REDUCE AVAILABLE SEATS BY 1
    // public void setBookedSeats(String section) {
    //     int seats = this.bookedSeats.get(section);
    //     if (seats > 0) {
    //         seats -= 1;
    //     } else {
    //         System.out.println("Error: Attempted To Book When No Seats Available");
    //     } this.bookedSeats.put(section, seats);
    // }


}
