package SoftA2Movie;
import java.util.ArrayList;

public class Cinema {

    private String location;
    private String address;
    private int numRooms;
    private ArrayList<CinemaRoom> rooms;

    public Cinema(String location, String address){ //int numRooms, ArrayList<CinemaRoom> rooms) {
        this.location = location;
        this.address = address;
        this.numRooms = 0;
        this.rooms = new ArrayList<CinemaRoom>();
        this.setRooms();
        //this.rooms = rooms;
    }

    // ######################
    // ### GETTER METHODS ###
    // ######################

    // GET CINEMA LOCATION
    public String getLocation() {
        return this.location;
    }

    // GET CINEMA ADDRESS
    public String getAddress() {
        return this.address;
    }

    // GET CINEMA NO. ROOMS
    public int getNumRooms() {
        return this.getRooms().size();
    }

    // GET CINEMA ROOMS LIST
    public ArrayList<CinemaRoom> getRooms() {
        return this.rooms;
    }

    // ######################
    // ### SETTER METHODS ###
    // ######################

    // SET CINEMA LOCATION
    public void setLocation(String location) {
        this.location = location;
    }

    // SET CINEMA ADDRESS
    public void setAddress(String address) {
        this.address = address;
    }

    // // SET CINEMA NO. ROOMS
    // public void setNumRooms(int numRooms) {
    //     this.numRooms = numRooms;
    // }

    // ADD CINEMA ROOMS TO LIST
    public void addRoom(CinemaRoom room) {
        this.rooms.add(room);
        this.numRooms += 1;

    }

    // REMOVE CINEMA ROOM FROM LIST
    public void removeRoom(CinemaRoom room) {
        this.rooms.remove(room);
    }

    // SET CINEMA ROOMS 
    public void setRooms(){
        // 2 of each
        
        CinemaRoom gold1 = new CinemaRoom(this, "Gold");
        CinemaRoom gold2 = new CinemaRoom(this, "Gold");

        this.rooms.add(gold1);
        this.rooms.add(gold2);
        
        CinemaRoom silver1 = new CinemaRoom(this, "Silver");
        CinemaRoom silver2=  new CinemaRoom(this, "Silver");

        this.rooms.add(silver1);
        this.rooms.add(silver2);
        
        CinemaRoom bronze1 = new CinemaRoom(this, "Bronze");
        CinemaRoom bronze2=  new CinemaRoom(this, "Bronze");

        this.rooms.add(bronze1);
        this.rooms.add(bronze2);
    }
}
