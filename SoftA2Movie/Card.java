package SoftA2Movie;

public class Card {

    private String cardNo;

    public Card(String cardNo) {
        this.cardNo = cardNo;
    }

    public void setCardNo(String newCardNo){
        this.cardNo = newCardNo;
    }
    
    public String getCardNo(){
        return this.cardNo;
    }
}
