package backEnd;

public class Item{

    /* Generic type */
    String type;
    String name;
    String description;

    /* Weapon */
    int attackBonus;
    String damageDice;
    String damageType;

    /* Armor */
    int bonus;
    //String armorType;

    boolean isCurrency;

    /* Currency */
    int amount = 0;

    public Item() {
        this.type = "CODE-BREAKING_ERROR";
        this.name = "VERY UNIQUE ITEM THAT DOES THINGS";
    }

    /**
     * For Weapon type
     * @param type
     * @param name
     * @param attackBonus
     * @param damageDice
     * @param damageType
     * @param description
     */
    public Item(String type, String name, int attackBonus, String damageDice, String damageType, String description) {
        this.type = type;
        this.name = name;
        this.attackBonus = attackBonus;
        this.damageDice = damageDice;
        this.damageType = damageType;
        this.description = description;
    }

    /**
     * For Armor type
     * @param type
     * @param name
     * @param bonus
     * @param description
     */
    // removed armorType because it does not really have any application.
    public Item(String type, String name, int bonus,String description) {
        this.type = type;
        this.name = name;
        this.bonus = bonus;
        this.description = description;
    }

    /**
     * For Misc type
     * @param type
     * @param name
     * @param description
     */
    /*public Item(String type, String name, String description) {
        this.type = type;
        this.name = name;
        this.amount = amount;
    }*/

    /**
     * For Misc(Currency?) type
     * @param type
     * @param name
     * @param amount
     * @param description
     * @param isCurrency
     */
    public Item(String type, String name, int amount, String description, boolean isCurrency) {
        this.type = type;
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.isCurrency = isCurrency;
    }

    /* Getters and setters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        switch (this.type) {
            case "weapon":
                return this.type
                        + " " + this.name
                        + " " + this.attackBonus
                        + " " + this.damageDice
                        + " " + this.damageType
                        + " " + this.description;
            case "armor":
                return this.type
                        + " " + this.name
                        + " " + this.bonus
                        + " " + this.description;
            case "misc":
                return this.type
                        + " " + this.name
                        + " " + this.amount
                        + " " + this.description;
        }

        return this.type + this.name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int n) {
        amount = n;
    }

    public String getDescription() {return description; }
}
