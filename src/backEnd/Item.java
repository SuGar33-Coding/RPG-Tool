package backEnd;

public class Item implements Comparable<Item>{
    /* priority in Items list sorting */
    int priority = 2;

    String name;

    public Item() {
        this.name = "VERY UNIQUE ITEM THAT DOES THINGS";
    }

    public Item(String name) {
        this.name = name;
    }

    /* Getters and setters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Item i) {
        return this.priority - i.getPriority();
    }
}
