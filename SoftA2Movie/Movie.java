package SoftA2Movie;

import java.util.Date;

public class Movie {

    private String title;
    private String synopsis;
    private String classification;
    private Date releaseDate;
    private String director;
    private String cast;
    private int duration;
    private Double price;

    public Movie( String title, String synopsis, String classification, Date releaseDate, String director, String cast, int duration, Double price) {
        this.title = title;
        this.synopsis = synopsis;
        this.classification = classification;
        this.releaseDate = releaseDate;
        this.director = director;
        this.cast = cast;
        this.duration = duration;
        this.price = price;
    }

    public String getTitle(){ 
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getSynopsis(){ 
        return this.synopsis;
    }
    
    public void setSynopsis(String s){
        this.synopsis = s;
    }

    public String getClassification(){ 
        return classification;
    }
    public void setClassification(String c){
        this.classification = c;
    }

    // Should we use String for date as no date comparisons are required ??
    public Date getReleaseDate(){ 
        return this.releaseDate;
    }
    public void setReleaseDate(Date d){
        this.releaseDate = d;
    }

    public String getDirector(){ 
        return director;
    }
    public void setDirector(String direct){
        this.director = direct;
    }

    public String getCast(){ 
        return cast;
    }
    public void setCast(String c){
        this.cast = c;
    }

    public int getDuration(){ 
        return duration;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }

    public Double getPrice(){ 
        return price;
    }
    public void setPrice(double p){
        this.price = p;
    }

    
    
    
}
