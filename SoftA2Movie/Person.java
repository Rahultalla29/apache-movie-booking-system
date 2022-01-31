package SoftA2Movie;

public class Person {

    public String username;
    public String name;
    public String password;
    
    public Person(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }
    public void setUsername(String name){
        this.username = name;

    }
    public void setName(String name){
        this.name = name;

    }
    public void setPassword(String password){
        this.password = password;

    }
    public String getUsername(){
        return this.username;
    }
    public String getName(){
        return this.name;
    }
    public String getPassword(){
        return this.password;
    }
}
