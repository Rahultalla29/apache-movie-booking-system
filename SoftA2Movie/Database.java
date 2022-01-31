package SoftA2Movie;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;  
import java.time.LocalDateTime; 
import java.text.DateFormat;  
import java.time.format.DateTimeFormatter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.time.ZoneId;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.time.LocalDate;

public class Database {
    private ArrayList<CreditCard> creditCards;
    private ArrayList<GiftCard> giftCards;
    private ArrayList<Staff> staff;
    private ArrayList<Customer> customers;
    private ArrayList<Movie> movies;
    private ArrayList<Showing> shows;
    private ArrayList<Booking> bookings;

    private String GiftCardCsv;
    private String staffCsv;
    private String customerCsv;
    public String movieCsv;
    private String showCsv;
    private String bookingCsv;
    private String cancelledCsv;
    private String creditCardJson;
    private int bookingID;

    public Database(){
        this.creditCards = new ArrayList<CreditCard>();
        this.giftCards = new ArrayList<GiftCard>();
        this.staff = new ArrayList<Staff>();
        this.customers = new ArrayList<Customer>();
        this.movies = new ArrayList<Movie>();
        this.shows = new ArrayList<Showing>();
        this.bookings = new ArrayList<Booking>();
        this.bookingID = 0;
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// SET CSVS /////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public void setGiftCardCsv(String csv){
        this.GiftCardCsv = csv;
    }
    public void setStaffCsv(String csv){
        this.staffCsv = csv;
    }
    public void setCreditCardCsv(String json){
        this.creditCardJson = json;
    }
    public void setCustomerCsv(String csv){
        this.customerCsv = csv;
    }
    public void setMovieCsv(String csv){
        this.movieCsv = csv;
    }
    public void setShowCsv(String csv){
        this.showCsv = csv;
    }
    public void setBookingCsv(String csv){
        this.bookingCsv = csv;
    }
    public void setCancelledCsv(String csv){
        this.cancelledCsv = csv;
    }

    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// CREDITCARDS //////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    
    public void parseCreditCard() {
		JSONParser parser = new JSONParser();
        try {     
            JSONArray a = (JSONArray) parser.parse(new FileReader("src/main/java/SoftA2Movie/resources/input/CreditCards.json"));

            for (Object obj : a) {
                JSONObject details = (JSONObject) obj;

                String name = (String) details.get("name");
                String number = (String) details.get("number");

                CreditCard card = new CreditCard(name, number);
                this.creditCards.add(card);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    
    public ArrayList<CreditCard> getCreditCards() {
        return this.creditCards;
    }

    public boolean CreditCardValid(String name, String number) {
        for (CreditCard card : this.creditCards) {
            if (card.getName().equals(name)) {
                if (card.getCardNo().equals(number)) {
                    return true;
                }
            }
        }
        return false;
    }

    public CreditCard getCreditCard(String name) {
        for (CreditCard card : this.creditCards) {
            if (card.getName().equals(name)) {
                return card;
            }
        }    
        return null;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// GIFT  CARDS //////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    
    public ArrayList<GiftCard> getGiftCards(){
        return this.giftCards;
    }

    public void parseGiftCards(){
        ArrayList<String> cards = new ArrayList<String>();
        
        try{        
            File file = new File(this.GiftCardCsv);
            Scanner scanner = new Scanner(file);           
            // convert to list of strings 
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                cards.add(line);
            }
            scanner.close();
        } catch(FileNotFoundException e){e.printStackTrace();}

       //split each string into an array
        for(int i =0; i <cards.size(); i++){
            String card = cards.get(i);
            String[] splitCard = card.split(",");
            
            // [1234567812345678GC,true]
            String cardNumber = splitCard[0];
            
            boolean redeemable = Boolean.valueOf(splitCard[1]);
            Double amount = Double.parseDouble(splitCard[2]);
            GiftCard gc = new GiftCard(cardNumber, redeemable, amount);
            this.giftCards.add(gc);           
        }


    }


    public boolean addGiftCard(String cardNumber, boolean redeemable, Double amount){
        if(isValidGiftCard(cardNumber)){
            return false;
        }
        GiftCard gc = new GiftCard(cardNumber,redeemable, amount);
        this.giftCards.add(gc);
        this.updateGiftCardCsv();
        return true;
    }


    public void removeGiftCard(String cardNumber){

        for (int i =0; i <this.giftCards.size(); i++){
            if (this.giftCards.get(i).getCardNo().equals(cardNumber)){
                this.giftCards.remove(i);  
            }
        }
        this.updateGiftCardCsv();
         
    }
    
    public boolean isValidGiftCard(String cardNumber){
        
        for (int i =0; i <this.giftCards.size(); i++){
            if (this.giftCards.get(i).getCardNo().equals(cardNumber) & this.giftCards.get(i).redeemable){
                return true; 
            }
        }
        return false;
    }
    public GiftCard getGiftCard(String name) {
        for (GiftCard card : this.giftCards) {
            if (card.getCardNo().equals(name)) {
                return card;
            }
        }    
        return null;
    }


    public void updateGiftCardCsv(){
        try {
            FileWriter myWriter = new FileWriter(this.GiftCardCsv);
            for(int i = 0; i < this.getGiftCards().size(); i++){
                GiftCard card = this.getGiftCards().get(i);
                String r = Boolean.toString(card.redeemable);
                String a = Double.toString(card.amount);
                String line = card.getCardNo()+","+r+","+a+"\n";
                myWriter.write(line);          
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// STAFF  ///////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////


    public ArrayList<Staff> getStaff(){
        return this.staff;
    }

    public void parseStaff(){
        ArrayList<String> staff = new ArrayList<String>();
        try{

        
            File file = new File(this.staffCsv);
            Scanner scanner = new Scanner(file);
            
            // convert to list of strings 
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                staff.add(line);
            }
            scanner.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

        //split each string into an array
        for(int i =0; i <staff.size(); i++){
            String member = staff.get(i);
            String[] split = member.split(",");
            // [name],[username],[password],[is manager?]
            
            String name = split[0];
            String username = split[1];
            String password = split[2];
                       
            boolean isManager = Boolean.valueOf(split[3]);
            Staff s = new Staff(username,name, password,isManager);
            this.staff.add(s);           
        }


    }
    public boolean checkStaffExists(String username, String password){
        for (int i =0; i <this.staff.size(); i++){
//            System.out.println("uname " + this.staff.get(i).username + " pwd " + this.staff.get(i).password);
            if (this.staff.get(i).username.equals(username) && this.staff.get(i).password.equals(password)){ 
                return true;
            }
        }
        return false;

    }
    
    public Staff getStaff(String username, String password){
        for (int i =0; i <this.staff.size(); i++){
            if (this.staff.get(i).username.equals(username) && this.staff.get(i).password.equals(password)){ 
                return this.staff.get(i);
            }
        }
        return null;

    }


    public boolean addStaff(String name,String username,String password, boolean manager){
        if(checkStaffExists(name, password)){
            return false;
        }
        Staff s = new Staff(name, username, password,manager);
        this.staff.add(s);
        this.updateStaffCsv();
        return true;
    }
    
    public boolean changeStaffPassword(String name,String username,String old_password, String new_password){
        if(checkStaffExists(name, old_password)){
            Staff s = getStaff(name, old_password);
            s.setPassword(new_password);
        }
        return false;
    }
    
    public void fireStaff(String username){
        
        for (int i =0; i <this.staff.size(); i++){
            if (this.staff.get(i).username.equals(username)){
                this.staff.remove(i);  
            }
        }
        
       this.updateStaffCsv();
         
    }
    public void makeManager(String username){
        for (int i =0; i <this.staff.size(); i++){
            if (this.staff.get(i).username.equals(username)){
                this.staff.get(i).setManager(true); 
            }
        }
        this.updateStaffCsv();

    }

    public void updateStaffCsv(){
        try {
            FileWriter myWriter = new FileWriter(this.staffCsv);
            for(int i = 0; i < this.getStaff().size(); i++){
                Staff s = this.getStaff().get(i);
                String line = s.name+","+s.username+","+s.password+","+s.isManager+"\n";
                myWriter.write(line);          
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
  
    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// CUSTOMER  ////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////


    public ArrayList<Customer> getCustomers(){
        return this.customers;
    }

    public void parseCustomers(){
        ArrayList<String> customers = new ArrayList<String>();
        try{

        
            File file = new File(this.customerCsv);
            Scanner scanner = new Scanner(file);
            
            // convert to list of strings 
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                customers.add(line);
            }
            scanner.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

        //split each string into an array
        for(int i =0; i <customers.size(); i++){
            String customer = customers.get(i);
            String[] split= customer.split(",");
            
            // [Name],[unique username],[password -> pin?],[optional - creditcard no],[optional -credit card pin]
            String name = split[0];
            String username = split[1];
            String password = split[2];
            
        
            Customer c = new Customer(username, name, password);

            if(split[3] != null){
                    
                String cardNo = split[3];
                c.saveDetails(cardNo);
            }
            else{
                c.saveDetails(null);
            }
            this.customers.add(c);           
        }


    }

    public boolean addCustomer(String name, String username, String password){
        if (!usernameExists(username)) {
            Customer c = new Customer(username, name, password);
            this.customers.add(c);
            this.updateCustomerCsv();
            return true;
        }
        return false;
    }

    public boolean usernameExists(String username) {
        for (int i =0; i <this.customers.size(); i++){
            if (this.customers.get(i).username.equals(username)){
                return true;
            }
        }
        return false;
    }

    public void removeCustomer(String username){

        for (int i =0; i <this.customers.size(); i++){
            if (this.customers.get(i).username.equals(username)){
                this.customers.remove(i);  
            }
        }
        
        this.updateCustomerCsv();
         
    }
    public void removeStaff(String name){

        for (int i =0; i <this.staff.size(); i++){
            if (this.staff.get(i).name.equals(name)){
                this.staff.remove(i);  
            }
        }
        
        this.updateStaffCsv();
         
    }
	
    public Customer getCustomer(String username, String password){
        
        for (int i = 0; i <this.customers.size(); i++){
            if (this.customers.get(i).username.equals(username) && this.customers.get(i).password.equals(password)) {
                return this.customers.get(i); 
            }

        }
        return null;

    } 

    public boolean customerExist(String username, String password){
        
        for (int i = 0; i <this.customers.size(); i++){
            if (this.customers.get(i).username.equals(username) && this.customers.get(i).password.equals(password)) {
                return true; 
            }

        }
        return false;

    } 
    public boolean customerExist(String username){
        
        for (int i = 0; i <this.customers.size(); i++){
            if (this.customers.get(i).username.equals(username)) {
                return true; 
            }

        }
        return false;

    }

    public void updateCustomerCsv(){
        try {
            FileWriter myWriter = new FileWriter(this.customerCsv);
            for(int i = 0; i < this.getCustomers().size(); i++){
                Customer c = this.getCustomers().get(i);
                String line = c.name +","+ c.username+","+c.password+","+c.creditCardNo+"\n";
                myWriter.write(line);          
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public Customer getCustomer(String name){
        for(int i =0; i < this.customers.size(); i++){
            if(this.customers.get(i).getName().equals(name)){
                return this.customers.get(i);
            }
        }
        return null;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////  MOVIES  /////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    
    public ArrayList<Movie> getMovies(){
        return this.movies;
    }

    public boolean addMovie(String name, String synopsis, String classification, Date release, String director, String cast, int duration, Double price){
        if(movieExists(name)){
            return false;
        }
        Movie m = new Movie(name, synopsis, classification, release, director, cast, duration, price);
        this.movies.add(m);
        this.updateMovieCsv();
        return true;
    }

    public void removeMovie(String movieTitle){

        for (int i = 0; i < this.movies.size(); i++){
            if (this.movies.get(i).getTitle().equals(movieTitle)) {
                this.movies.remove(i);  
            }
        }
        this.updateMovieCsv();
    }
    public ArrayList<String> readFile(String fileType){
        String path;
        if (fileType == null){
            return null;
        }else if (fileType.equals("movies")){
            path = this.movieCsv;
        }
        else if (fileType.equals("bookings")){
            path = this.bookingCsv;
        }else if (fileType.equals("transactions")){
            path = this.cancelledCsv;
        }else {path = "";}
        
        ArrayList<String> movies = new ArrayList<String>();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file); 
            // convert to list of strings 
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                movies.add(line);
            }
            scanner.close();
            return movies;
        } catch(FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
        
    public void parseMovies(){
        ArrayList<String> movies = new ArrayList<String>();
        try {
            File file = new File(this.movieCsv);
            Scanner scanner = new Scanner(file); 
            // convert to list of strings 
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                movies.add(line);
            }
            scanner.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        //split each string into an array
        for(int i = 0; i < movies.size(); i++){
            String movie = movies.get(i);
            String[] split= movie.split(",");
            
            try {
                String title = split[0];
                String synopsis = split[1];
                String classification = split[2];
                Date release = new SimpleDateFormat("dd/MM/yyyy").parse(split[3]);
                String director = split[4];
                String cast = split[5];
                int duration = Integer.parseInt(split[6]);
                double price = Double.parseDouble(split[7]);
            
                Movie m = new Movie(title, synopsis, classification, release, director, cast, duration, price);
                this.movies.add(m);    
            } catch(Exception e) {
                e.printStackTrace();
            }       
        }
    }
    
    public boolean movieExists(String movieTitle){
        
        for (int i = 0; i < this.movies.size(); i++){
            if (this.movies.get(i).getTitle().equals(movieTitle)) {
                return true; 
            }
        }
        return false;
    } 
    public Movie getMovie(String title){
        for(int i =0; i < this.movies.size(); i++){
            if(this.movies.get(i).getTitle().equals(title)){
                return this.movies.get(i);
            }
        }
        return null;
    }

    public void updateMovieCsv(){
        try {
            FileWriter myWriter = new FileWriter(this.movieCsv);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
            for(int i = 0; i < this.getMovies().size(); i++){
                Movie m = this.getMovies().get(i);
                String line = m.getTitle() +","+ m.getSynopsis()+","+m.getClassification()+","+dateFormat.format(m.getReleaseDate())+","+m.getDirector()+",\""+m.getCast()+"\","+Integer.toString(m.getDuration())+","+Double.toString(m.getPrice())+"\n";
                myWriter.write(line);          
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    
    ////////////////////////////////// MOVIE MODIFICATION METHODS ////////////////////////////////////
    
    
    public void modifyMovieTitle(String title, String newTitle){
        if(this.movieExists(title)){
            Movie movie = getMovie(title);
            movie.setTitle(newTitle);
            this.updateMovieCsv();
            
        }
    }
    public void modifyMovieSynposis(String title, String s){
        if(this.movieExists(title)){
            Movie movie = getMovie(title);
            movie.setSynopsis(s);
            this.updateMovieCsv();
            
        }
    }
    public void modifyMovieReleaseDate(String title, Date r){
        if(this.movieExists(title)){
            Movie movie = getMovie(title);
            movie.setReleaseDate(r);
            this.updateMovieCsv();
        }
    }
    public void modifyMovieDirector(String title, String director){
        if(this.movieExists(title)){
            Movie movie = getMovie(title);
            movie.setDirector(director);
            this.updateMovieCsv();
        }
    }

    public void modifyMovieCast(String title, String cast){
        if(this.movieExists(title)){
            Movie movie = getMovie(title);
            movie.setCast(cast);
            this.updateMovieCsv();
            
        }
    }
    public void modifyMovieDuration(String title, int duration){
        if(this.movieExists(title)){
            Movie movie = getMovie(title);
            movie.setDuration(duration);
            this.updateMovieCsv();
            
        }
    }
    public void modifyMoviePrice(String title, double price){
        if(this.movieExists(title)){
            Movie movie = getMovie(title);
            movie.setPrice(price);
            this.updateMovieCsv(); 
            
        }
    }
    public void modifyMovieRating(String title, String classification){
        if(this.movieExists(title)){
            Movie movie = getMovie(title);
            movie.setClassification(classification);
            this.updateMovieCsv(); 
            
        }
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// SHOWS  ///////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    
    public ArrayList<Showing> getShows(){
        return this.shows;
    }

    public String getShowCsv() {
        return this.showCsv;
    }

    // only add for upcoming week (Monday is first day of the week)
    // check time as well
    public boolean addShow(int showID, String movieName, LocalDateTime session, Date date, String screenSize, String location) {
        if(showExists(showID)){
            
            return false;
        }
        
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        Integer year1 = c.get(c.YEAR);
        Integer week1 = c.get(c.WEEK_OF_YEAR);

        Calendar ci = Calendar.getInstance();
        ci.setFirstDayOfWeek(Calendar.MONDAY);
        long millis = date.getTime();
        ci.setTimeInMillis(millis);
        Integer year2 = ci.get(ci.YEAR);
        Integer week2 = ci.get(ci.WEEK_OF_YEAR);
       


        if (year1.equals(year2)) {
            if(week1 == week2 - 1) {
                Movie movie = getMovie(movieName);
                if(showDuringAnotherShow(session, date,screenSize, location)){
                        
                        return false;
                    }
                Showing s = new Showing(showID, movie, session, date, screenSize, location, 30, 0); // default 
                this.shows.add(s);
                this.updateShowCsv();
                return true;   
            }
        }
        
        return false;

    }
    public boolean showDuringAnotherShow(LocalDateTime session, Date date, String screenSize, String location){
        for (Showing show : this.shows) {
                if (
                    show.getDate().equals(date)&&
                    show.getRoomType().equals(screenSize) &&
                    show.getLocation().equals(location) &&
                    session.isBefore(show.endTime)) {
                        return true;
            }
        }
        return false;
    }

    public void removeShow(int showID){
        for (int i = 0; i < this.shows.size(); i++){
            if (this.shows.get(i).getShowID() == showID) {
                this.shows.remove(i);  
            }
        }
        this.updateShowCsv();
    }

    public void parseShows(){
        ArrayList<String> showings = new ArrayList<String>();
        try {
            File file = new File(this.showCsv);
            Scanner scanner = new Scanner(file); 
            // convert to list of strings 
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                showings.add(line);
            }
            scanner.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        //split each string into an array
        for(int i = 0; i < showings.size(); i++){
            String show = showings.get(i);
            String[] split = show.split(",");
            
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                int showID = Integer.parseInt(split[0]);
                String movieName = split[1];
                LocalDateTime session = LocalDateTime.parse(split[3]+" "+split[2], formatter);
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(split[3]);
                String roomType = split[4];
                String location = split[5];
                int aSeats = Integer.parseInt(split[6]);
                int bSeats = Integer.parseInt(split[7]);
            
                Movie movie = getMovie(movieName);
                Showing s = new Showing(showID, movie, session, date, roomType, location, aSeats, bSeats);
                this.shows.add(s);    
            } catch(Exception e) {
                e.printStackTrace();
            }       
        }
    }

    public int getShowID(String movieTitle, String location, Date date, String roomType, LocalDateTime session) {
        for (int i = 0; i < this.shows.size(); i++){
            if (this.shows.get(i).getMovie().getTitle().equals(movieTitle)
                && this.shows.get(i).getSession().equals(session)
                && this.shows.get(i).getDate().equals(date)
                && this.shows.get(i).getRoomType().equals(roomType)
                && this.shows.get(i).getLocation().equals(location)) {
                return this.shows.get(i).getShowID(); 
            }
        }
        return -1;
    }

    public boolean showExists(int showID){
        
        for (int i = 0; i < this.shows.size(); i++){
            if (this.shows.get(i).getShowID() == showID) {
                return true; 
            }
        }
        return false;
    } 

    public void updateShowCsv(){
        try {
            FileWriter myWriter = new FileWriter(this.showCsv);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
            for(int i = 0; i < this.getShows().size(); i++){
                Showing s = this.getShows().get(i);

                int sessionHour = s.getSession().getHour();
                int sessionMinute = s.getSession().getMinute();
                
                String cH = Integer.toString(sessionHour);
                String sm = Integer.toString(sessionMinute);

                if (sessionHour < 10){ 
                    cH = '0'+cH;
                }
                
                if (sessionMinute < 10){ 
                    sm = '0'+sm;
                }


                String line = s.getShowID() +","+ s.getMovieName()+","+cH+":"+sm+","+dateFormat.format(s.getDate())
                                            +","+s.getRoomType()+","+s.getLocation()+","+s.getAvailableSeats()+","+s.getBookedSeats()
                                            +"\n";
                myWriter.write(line);          
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Showing getShow(int showID){   
        for (int i = 0; i < this.shows.size(); i++){
            if (this.shows.get(i).getShowID() == showID) {
                return this.shows.get(i); 
            }
        }
        return null;
    }
    
    
    
    //////////////////////////////////// SHOW MODIFICATION METHODS  ////////////////////////////////////////
    
    /*
    public void modifyShowSession(int showID, String newSession){
        if(this.showExists(showID)){
            Showing show = getShow(showID);
            show.setSession(newSession);
            
            this.updateShowCsv();
            
        }

    }
    public void modifyShowDate(int showID, Date newDate){
        if(this.showExists(showID)){
            Calendar c = Calendar.getInstance();
            c.setFirstDayOfWeek(Calendar.MONDAY);
            Integer year1 = c.get(c.YEAR);
            Integer week1 = c.get(c.WEEK_OF_YEAR);
    
            Calendar ci = Calendar.getInstance();
            ci.setFirstDayOfWeek(Calendar.MONDAY);
            long millis = newDate.getTime();
            ci.setTimeInMillis(millis);
            Integer year2 = ci.get(ci.YEAR);
            Integer week2 = ci.get(ci.WEEK_OF_YEAR);
    
            if (year1 == year2) {
                if(week1 == week2 - 1) {
                    Showing show = getShow(showID);
                    show.setDate(newDate);
                }
            }        
            this.updateShowCsv();
        }
    } // still only for this week


    public void modifyShowRoomType(int showID, String newRoomType){
        if(this.showExists(showID)){
            Showing show = getShow(showID);
            show.setRoomType(newRoomType);    
            this.updateShowCsv();
        }
    }

    public void modifyShowLocation(int showID, String newLocation){
        if(this.showExists(showID)){
            Showing show = getShow(showID);
            show.setLocation(newLocation); // already changed cinema object    
            this.updateShowCsv();
        }
    } // update cinmea list too?

    // we might need these two later on
    public void modifyShowBookedSeats(int showID, int s){
        if(this.showExists(showID)){
            Showing show = getShow(showID);
            show.setBookedSeats(s); // already changed cinema object    
            this.updateShowCsv();
        }
    }

    public void modifyShowAvailableSeats(int showID, int s){
        if(this.showExists(showID)){
            // still be the same max
            Showing show = getShow(showID);
            if (show.enoughAvailableSeats(s)){
                show.setAvailableSeats(s); // already changed cinema object    
                this.updateShowCsv();

            }
        }
    } */


    //////////////////////////////////// FILTER METHODS ////////////////////////////////////////

    
    public ArrayList<Showing> showingsByLocation(ArrayList<Showing> newShows, String location){
        ArrayList<Showing> showByLocation = new ArrayList<Showing>();
        for(int i =0; i < newShows.size(); i++){
            if(newShows.get(i).getLocation().equals(location)){
                showByLocation.add(newShows.get(i));
            }
        }
        return showByLocation;

    }
    public ArrayList<Showing> showingsBySession(ArrayList<Showing> newShows, String session){
        ArrayList<Showing> showBySession = new ArrayList<Showing>();
        if (session.equals("Morning")) {
            for(int i = 0; i < newShows.size(); i++){
                if (newShows.get(i).getSession().getHour() >= 9 && newShows.get(i).getSession().getHour() < 12) {
                    showBySession.add(newShows.get(i));
                }
            }
        }
        else if (session.equals("Afternoon")) {
            for(int i = 0; i < newShows.size(); i++){
                if (newShows.get(i).getSession().getHour() >= 12 && newShows.get(i).getSession().getHour() < 18) {
                    showBySession.add(newShows.get(i));
                }
            }
        }
        else if (session.equals("Night")) {
            for(int i = 0; i < newShows.size(); i++){
                if (newShows.get(i).getSession().getHour() >= 18 && newShows.get(i).getSession().getHour() < 22) {
                    showBySession.add(newShows.get(i));
                }
            }
        }
        return showBySession;
    }

    public ArrayList<Showing> showingsByScreenSize(ArrayList<Showing> newShows, String size){
        ArrayList<Showing> showBySize = new ArrayList<Showing>();
        for(int i =0; i < newShows.size(); i++){
            if(newShows.get(i).getRoomType().equals(size)){
                showBySize.add(newShows.get(i));
            }
        }
        return showBySize;
    }
    
    /*public ArrayList<Showing> showingsByTitle(ArrayList<Showing> newShows, String title){
        ArrayList<Showing> showByTitle = new ArrayList<Showing>();
        for(int i =0; i < newShows.size(); i++){
            if(newShows.get(i).getMovie().getTitle().equals(title)){
                showByTitle.add(newShows.get(i));
            }
        }
        return showByTitle;
    }*/
    public ArrayList<Showing> showingsByTitle(ArrayList<Showing> newShows, String title){
        ArrayList<Showing> showByTitle = new ArrayList<Showing>();
        String[] split = title.split(",");
        for(int i =0; i < newShows.size(); i++){
            for(int j = 0; j < split.length; j++){
                if(newShows.get(i).getMovie().getTitle().equals(split[j])){
                    //System.out.println(split[j]);
                    showByTitle.add(newShows.get(i));
                }
            }

        }
        return showByTitle;
    }

    public ArrayList<Showing> showingsByToday(ArrayList<Showing> newShows){        
        ArrayList<Showing> showToday = new ArrayList<Showing>();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate now = LocalDate.now();  
        Date currentDate = Date.from(now.atStartOfDay(defaultZoneId).toInstant());
        LocalDateTime current = LocalDateTime.now();
        for (Showing show : newShows) {
            if (show.getDate().toString().equals(currentDate.toString())) {
                if (show.getSession().getHour() > current.getHour()) {
                    showToday.add(show);
                } else if (show.getSession().getHour() == current.getHour() && show.getSession().getMinute() > current.getMinute()) {
                    showToday.add(show);  
                }
            }
        }
        return showToday;
    }

    public ArrayList<Showing> showingsTillNextMonday(ArrayList<Showing> newShows){        
        ArrayList<Showing> showByMonday= new ArrayList<Showing>();
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        Integer year1 = c.get(c.YEAR);
        Integer week1 = c.get(c.WEEK_OF_YEAR);
        Calendar ci = Calendar.getInstance();
        ci.setFirstDayOfWeek(Calendar.MONDAY);
        LocalDateTime current = LocalDateTime.now();
        for (Showing show : newShows) {
            long millis = show.getDate().getTime();
            ci.setTimeInMillis(millis);
            Integer year2 = ci.get(ci.YEAR);
            Integer week2 = ci.get(ci.WEEK_OF_YEAR);
            if (year1.equals(year2)) {
                if(week1.equals(week2)) {
                    if (show.getSession().toLocalDate().equals(current.toLocalDate())) {
                        if (show.getSession().getHour() > current.getHour()) {
                            showByMonday.add(show);
                        } else if (show.getSession().getHour() == current.getHour() && show.getSession().getMinute() > current.getMinute()) {
                            showByMonday.add(show);  
                        }
                    } else if (show.getSession().toLocalDate().isAfter(current.toLocalDate())) {
                        showByMonday.add(show); 
                    }
                }
            }
        }
        return showByMonday;
    }

    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// BOOKINGS //////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////


    public ArrayList<Booking> getBookings() { 
        return this.bookings;
    }

    public Booking getBooking(int bookingID){   
        for (int i = 0; i < this.bookings.size(); i++){
            if (this.bookings.get(i).getBookingID() == bookingID) {
                return this.bookings.get(i); 
            }
        }
        return null;
    }

    public boolean bookingExists(int bookingID){
        
        for (int i = 0; i < this.bookings.size(); i++){
            if (this.bookings.get(i).getBookingID() == bookingID) {
                return true; 
            }
        }
        return false;
    } 

    public void addBooking(int bookingID, HashMap<String, Integer> seats, HashMap<String, Integer> ages, LocalDateTime timeOfBooking, Customer customer, Showing show, ArrayList<Card> paymentMethod) {
        Booking booking = new Booking(bookingID, seats, ages, timeOfBooking, customer, show, paymentMethod);
        this.bookings.add(booking);
        this.updateBookingCsv();
    }

    public void removeBooking(int bookingID) {
        if (this.bookingExists(bookingID)) {
            this.bookings.remove(this.getBooking(bookingID));
            this.updateBookingCsv();
        }
    }

    // is there a parse booking needed ?

    // follow booking summary csv -> also delete last weeks ones?
    // fix booking price based on child, adult, pension etc
    // add datetime of booking to csv and details of booking -> how many ppl, their ages, the seat types
    public void updateBookingCsv() {
        try {
            FileWriter myWriter = new FileWriter(this.bookingCsv);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            for(int i = 0; i < this.getBookings().size(); i++){
                Booking b = this.getBookings().get(i);
                String line = b.getShow().getShowID() +","+ b.getShow().getMovieName()+","+b.getShow().getSession().getHour()+":"+b.getShow().getSession().getMinute()+","+b.getShow().getRoomType()
                                            +","+b.getShow().getLocation()+","+b.getPrice()+b.getShow().getBookedSeats()+","+b.getShow().getAvailableSeats()
                                            +",\""+b.getTimeOfBooking().format(formatter)+"\","+b.getNumSeatByAge("Child")+","+b.getNumSeatByAge("Student")
                                            +","+b.getNumSeatByAge("Adult")+","+b.getNumSeatByAge("Pensioner")+"\n";
                myWriter.write(line);          
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    //////////////////////////////////// BOOK  /////////////////////////////////////////////////////////////// 
    
    

    

    // can give customer name instead of customer, showing id instead of show object and cardno instead of card object if its easier
    // in whcih case this method is better
    
    public String book(HashMap<String, Integer> seats, HashMap<String, Integer> ages, LocalDateTime timeOfBooking, String customerName, String GcardNo, String CcardNo, String movieName, String location, Date date, String roomType, LocalDateTime session){
        this.bookingID += 1;
        // custmomer exists becase already loged in and that creates a customer
        Customer customer = this.getCustomer(customerName);

        int showID = getShowID(movieName, location, date, roomType, session);
        Showing show = this.getShow(showID);
        // invalid show
        if(show == null){
            return "This show does not exist, please add valid details";
        }
        // number of seats available
        
        if (show.getAvailableSeats() < (seats.get("front")+seats.get("middle")+seats.get("back"))){
            return "Not Enough Seats for the Seats Booked!";
        }

        if(seats.get("front") > show.frontSeats){
            return "not enough front row seats";
        }
        
        if(seats.get("middle") > show.middleSeats){
            return "not enough middle row seats";
        }

        if(seats.get("back") > show.backSeats){
            return "not enough back row seats";
        }
        
        Double price = 0.00;
        for (String key : ages.keySet()) {
            if (key.equals("Child")) {
                price += show.getPrice() * 0.8 * ages.get(key);
            } else if (key.equals("Student")) {
                price += show.getPrice() * 0.85 * ages.get(key);
            } else if (key.equals("Adult")) {
                price += show.getPrice() * ages.get(key);
            } else {
                price += show.getPrice() + 0.8 * ages.get(key);
            }
        }
        ArrayList<Card> paymentMethod = new ArrayList<Card>();
        // assuming valid card
        if (GcardNo == null && CcardNo != null) {
            if (this.CreditCardValid(customerName, CcardNo)){
                paymentMethod.add(this.getCreditCard(CcardNo));
            } else {
                return "invalid credit card";
            }
        }
        else {
            // if giftcard has sufficient balance
            if (this.isValidGiftCard(GcardNo)) {
                if (this.getGiftCard(GcardNo).checkAmount(price)) {
                    paymentMethod.add(this.getGiftCard(GcardNo));
                    this.getGiftCard(GcardNo).amount -= price; //updates giftcard balance
                    if (this.getGiftCard(GcardNo).amount == 0) { this.removeGiftCard(GcardNo); }
                } else {
                    if (CcardNo == null) {
                        return "Insufficient Gift Card balance. Please enter credit card details for split payment.";
                    } else {
                        if (this.CreditCardValid(customerName, CcardNo)) {
                            paymentMethod.add(this.getCreditCard(CcardNo));
                            paymentMethod.add(this.getGiftCard(GcardNo));
                            this.getGiftCard(GcardNo).amount -= price; //updates giftcard balance
                            // this.removeGiftCard(GcardNo);
                        } else {
                            return "Invalid credit card.";
                        }
                    }
                }
            } else {
                if (CcardNo != null) { 
                    if (CreditCardValid(customerName, CcardNo)) {
                        
                        paymentMethod.add(this.getCreditCard(CcardNo));
                    } else {
                        return "Invalid Gift Card and Invalid Credit Card.";
                    }
                } else {
                    return "Invalid Gift Card. Please enter Credit Card details.";
                }
            }
        }
        
        // assuming we all good now
        show.frontSeats -= seats.get("front");
        show.middleSeats -= seats.get("middle");
        show.backSeats -= seats.get("back");
        this.addBooking(bookingID, seats, ages, timeOfBooking, customer, show, paymentMethod);
        return Integer.toString(bookingID);        
    }

    
    //////////////////////////////////// CANCELLED BOOKING CSV /////////////////////////////////////////////////////

    
    public void updateCancelledBookingsCSV(int showID, String customerName, String username, LocalDateTime time, String reason) {
        try {
            FileWriter myWriter = new FileWriter(this.cancelledCsv, true);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String line = "\n"+Integer.toString(showID)+",\""+customerName
                            +"\","+username+",\""+time.format(formatter)+"\",\""+reason+"\"";
            myWriter.write(line);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    } 


    /////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// OTHER  ///////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////

    
    //////////////////////////////////// JTABLE /////////////////////////////////////////////////////
    
    

    public JTable getJtableSForDisplay(ArrayList<Showing> m) {

        JTable j;
        String[][] data = new String[m.size()][];

        
        for(int i = 0; i < m.size(); i++){

            int sessionHour = m.get(i).getSession().getHour();
            int sessionMinute = m.get(i).getSession().getMinute();
            
            String cH = Integer.toString(sessionHour);
            String sm = Integer.toString(sessionMinute);

            if (sessionHour < 10){ 
                cH = '0'+cH;
            }
            
            if (sessionMinute < 10){ 
                sm = '0'+sm;
            }

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            data[i] = new String[] {
                m.get(i).getMovie().getTitle(),
                m.get(i).getMovie().getSynopsis(),
                m.get(i).getMovie().getClassification(),
                dateFormat.format(m.get(i).getMovie().getReleaseDate()),
                m.get(i).getMovie().getDirector(),
                m.get(i).getMovie().getCast(),
                Integer.toString(m.get(i).getMovie().getDuration()),
                cH+":"+sm,
                m.get(i).getRoomType(),
                m.get(i).getLocation(),
                Integer.toString(m.get(i).getAvailableSeats()),
                dateFormat.format(m.get(i).getDate())
            };

        }
 
        String[] columnNames = {"Title","Synopsis", "Rating","Release","Director","Cast","Length","Session","Screen","Location","Seats","Date"};

        j = new JTable(data, columnNames);

        return j;

    }
    public JTable getJtableForDisplayMovie(ArrayList<Movie> m) {

        JTable j;
        String[][] data = new String[m.size()][];
        
        for(int i = 0; i < m.size(); i++){
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            data[i] = new String[] {
                m.get(i).getTitle(),
                m.get(i).getSynopsis(),
                m.get(i).getClassification(),
                dateFormat.format(m.get(i).getReleaseDate()),
                m.get(i).getDirector(),
                m.get(i).getCast(),
                Integer.toString(m.get(i).getDuration()),
                Double.toString(m.get(i).getPrice())
            };

        }
 
        String[] columnNames = {"Title","Synopsis", "Rating","Release","Director","Cast","Length","Price"};

        j = new JTable(data, columnNames);

        return j;

    }
    
    public JTable getJtableForDisplayGiftCard(ArrayList<GiftCard> m) {

        JTable j;
        String[][] data = new String[m.size()][];
        
        for(int i = 0; i < m.size(); i++){
            data[i] = new String[] {
                m.get(i).getCardNo(),
                Boolean.toString(m.get(i).redeemable),
                Double.toString(m.get(i).amount)
            };

        }
 
        String[] columnNames = {"Card Number","Redeemable", "Amount"};

        j = new JTable(data, columnNames);

        return j;

    }
    public JTable getJtableForDisplayCreditCard(ArrayList<CreditCard> m) {

        JTable j;
        String[][] data = new String[m.size()][];
        
        for(int i = 0; i < m.size(); i++){
            
            data[i] = new String[] {
                m.get(i).getCardNo(),
                m.get(i).getName(),
            };

        }
 
        String[] columnNames = {"Card Number","Name"};

        j = new JTable(data, columnNames);

        return j;

    }
    public JTable getJtableForDisplayCustomers(ArrayList<Customer> m) {

        JTable j;
        String[][] data = new String[m.size()][];
        
        for(int i = 0; i < m.size(); i++){
            
            data[i] = new String[] {
                m.get(i).getUsername(),
                m.get(i).getName(),
            };

        }
 
        String[] columnNames = {"Username","Name"};

        j = new JTable(data, columnNames);

        return j;

    }
    public JTable getJtableForDisplayStaff(ArrayList<Staff> m) {

        JTable j;
        String[][] data = new String[m.size()][];
        
        for(int i = 0; i < m.size(); i++){
            
            data[i] = new String[] {
                m.get(i).getUsername(),
                m.get(i).getName(),
                Boolean.toString(m.get(i).isManager)
            };

        }
 
        String[] columnNames = {"Username","Name", "Manager"};

        j = new JTable(data, columnNames);

        return j;

    }
    
    public JTable getJtableForDisplayBooking(ArrayList<Booking> m) {

        JTable j;
        String[][] data = new String[m.size()][];
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        for(int i = 0; i < m.size(); i++){
            
            data[i] = new String[] {
                
                m.get(i).customer.getName(),
                Integer.toString(m.get(i).getBookingID()),
                Integer.toString(m.get(i).getShow().getShowID()),
                dateFormat.format(m.get(i).getTimeOfBooking()),
                Double.toString(m.get(i).getPrice()),
                Integer.toString(m.get(i).getTotalSeats()),
                m.get(i).paymentMethod.get(0).getCardNo(),
                
            };

        }
 
        String[] columnNames = {"Name", "Booking ID", "Show ID", "TIme of Booking", "Price", "Total Seats Booked", "Card Number Used"};

        j = new JTable(data, columnNames);

        return j;

    }
        /// upcoming shows csv
    String UpcomingShowCsv;
    
    public void setUpcomingShowsCsv(String s){
        this.UpcomingShowCsv = s;
    }

    public void updateUpcomingShowCsv(){
    // check if its already happened and then write it up to the csv

        // filter out older shows 


        ArrayList<Showing> upcomingShows = new ArrayList<Showing>();
        
        LocalDateTime current = LocalDateTime.now();


        for( Showing show : this.getShows()){
            
            if (show.getSession().toLocalDate().equals(current.toLocalDate())) {
                
                if (show.getSession().getHour() > current.getHour()) {
                        upcomingShows.add(show);
                } if (show.getSession().getHour() == current.getHour() && show.getSession().getMinute() > current.getMinute()) {
                        upcomingShows.add(show);  
                }
                
            }
            if(show.getSession().toLocalDate().isAfter(current.toLocalDate())){
                upcomingShows.add(show);
            }
        }


        try {
            FileWriter myWriter = new FileWriter(this.UpcomingShowCsv);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
            // [show id],[Movie Name][classification],[release date],[director],[length],
            //[movie price],[date time],[screen size],[Location],[show price],[how many seats available],[how many seats booked]
             
            for(int i = 0; i < upcomingShows.size(); i++){
                Showing s = upcomingShows.get(i);
                
                int sessionHour = s.getSession().getHour();
                int sessionMinute = s.getSession().getMinute();
                
                String cH = Integer.toString(sessionHour);
                String sm = Integer.toString(sessionMinute);

                if (sessionHour < 10){ 
                    cH = '0'+cH;
                }
                
                if (sessionMinute < 10){ 
                    sm = '0'+sm;
                }


                String line = s.getShowID() +","+ s.getMovieName()+","+s.getMovie().getClassification()+","+dateFormat.format(s.getMovie().getReleaseDate())+","
                                            +s.getMovie().getDirector()+","+s.getMovie().getDuration()+","+s.getMovie().getPrice()+","    
                                            +cH+":"+sm+","+dateFormat.format(s.getDate())
                                            +","+s.getRoomType()+","+s.getLocation()+","+s.getPrice()+","+s.getAvailableSeats()+","+s.getBookedSeats()
                                            +"\n";
                myWriter.write(line);          
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }  
        
    }

}
