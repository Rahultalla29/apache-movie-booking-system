package SoftA2Movie;

public class CreditCard extends Card {

    public String name;

    public CreditCard(String name, String cardNo) {
        super(cardNo);
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
