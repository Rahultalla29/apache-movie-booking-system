package SoftA2Movie;

public class Customer extends Person {

    public boolean detailsSaved;    
    public String creditCardNo;

    public Customer(String username, String name, String password) {
        super(username, name, password);
        this.detailsSaved = false;
        this.creditCardNo = null;
    }
    public void saveDetails(String cardNo) {
        this.detailsSaved = true;
        this.creditCardNo = cardNo;       
    }

}
