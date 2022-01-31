package SoftA2Movie;

import java.util.Date;
import java.time.LocalDateTime; 


public class Showing {
    
    private final int totalSeats = 30;

    private int showID;
    public Movie movie;
    private LocalDateTime session;
    private Date date; // timestamp ??
    private String roomType;
    // private Double price;
    private String location;
    private int bookedSeats;
    private int availableSeats;
    private String movieName;
    public int frontSeats;
    public int middleSeats;
    public int backSeats;
    public LocalDateTime endTime;

    
    public Showing(int showID, Movie movie, LocalDateTime session, Date date, String roomType, String location, int availableSeats, int bookedSeats) {        
        this.showID = showID;
        this.movie = movie;
        // this.movieName = movie.getTitle();
        this.session = session;
        this.date = date;
        this.roomType = roomType;
        this.location = location;
        this.bookedSeats = bookedSeats;
        this.availableSeats = availableSeats;
        this.frontSeats = 10;
        this.middleSeats = 10;
        this.backSeats = 10;
        this.endTime = this.session.plusMinutes(this.movie.getDuration());
    }

    public int getShowID() {
        return this.showID;
    }
    

    public Movie getMovie() {
        return this.movie;
    }

    public String getMovieName() {
        return this.movie.getTitle();
    }

    public int getTotalSeats(){
        return totalSeats;
    }

    public LocalDateTime getSession(){
        return session;
    }
    public void setSession(LocalDateTime s){
        this.session = s;
    }

    public Date getDate(){
        return date;
    }
    public void setDate(Date d){
        this.date = d;
    }

    public String getRoomType(){
        return roomType;
    }
    public void setRoomType(String r){
        // make sure its valid
        this.roomType = r;
    }

    public String getLocation(){
        
        return location;
    }
    public void setLocation(String l){
        // cahnge cinema object too
        this.location = l;
    }

    public int getBookedSeats(){
        return bookedSeats;
    }
    public boolean setBookedSeats(int s){
        // cahnge cinema object too
        
        if(s <= this.totalSeats){
            this.bookedSeats = s;
            availableSeats = totalSeats-bookedSeats;
            return true;
        } else{
            return false;
        }
    }

    public int getAvailableSeats(){

        return availableSeats;
    }
    public boolean setAvailableSeats(int s){
        // cahnge cinema object too
        if(s <= this.availableSeats){
             this.availableSeats = s;
             bookedSeats = totalSeats - availableSeats;
             return true;
        }else{
            return false;
        }
       
    }

    public Double getPrice() {
        Double price = 0.00;
        if (this.roomType.equals("Bronze")) {
            price = this.movie.getPrice();
        } else if (this.roomType.equals("Silver")) {
            price = this.movie.getPrice() * 1.2;
        } else {
            price = this.movie.getPrice() * 1.5;
        }   
        return price;
    }
    public boolean enoughAvailableSeats(int seats){
        return seats <= this.getAvailableSeats();
    }
}
