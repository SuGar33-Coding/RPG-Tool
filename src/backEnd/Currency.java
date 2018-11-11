package backEnd;

public class Currency extends Item {
    /* priority in Items list sorting */
    int priority = 3;
    public static final String TYPE = "Currency";

    int amount;

    public Currency(String name, int amount){
        super.name = name;
        this.amount = amount;
    }

    /* Getters and setters */
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPriority() {
        return priority;
    }
}
