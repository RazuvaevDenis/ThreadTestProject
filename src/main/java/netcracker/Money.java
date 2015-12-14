package netcracker;

/**
 * Created by Denis on 13.12.2015.
 */
public class Money {
    private int count;
    private String currency;

    public Money(int count, String currency){
        this.count=count;
        this.currency=currency;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
