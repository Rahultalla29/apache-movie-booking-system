package SoftA2Movie;
import java.util.*;

public class GiftCard extends Card {

    public boolean redeemable;
    public double amount;

    public GiftCard(String cardNo, boolean redeemable, Double amount) {
        super(cardNo);
        this.redeemable = redeemable;
        this.amount = amount; // same for all cards
    }

    public void use() {
        this.redeemable = false;
    }
    public boolean valid(String cardNo){
        char[] ch = cardNo.toCharArray();
        if(ch.length != 16 || ch[14] != 'G' || ch[15] != 'C'){
            return false;
        }
        else{return true;}
    }
    public boolean checkAmount(double p){
        return p <= amount;
    }
}
