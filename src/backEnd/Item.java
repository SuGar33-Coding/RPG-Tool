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
    String armorType;

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
     */
    public Item(String type, String name, int attackBonus, String damageDice, String damageType) {
        this.type = type;
        this.name = name;
        this.attackBonus = attackBonus;
        this.damageDice = damageDice;
        this.damageType = damageType;
    }

    /**
     * For Armor type
     * @param type
     * @param name
     * @param bonus
     * @param armorType
     */
    public Item(String type, String name, int bonus, String armorType) {
        this.type = type;
        this.name = name;
        this.bonus = bonus;
        this.armorType = armorType;
    }

    /**
     * For Misc type
     * @param type
     * @param name
     */
    /*public Item(String type, String name) {
        this.type = type;
        this.name = name;
        this.amount = amount;
    }*/

    /**
     * For Misc(Currency?) type
     * @param type
     * @param name
     * @param amount
     */
    public Item(String type, String name, int amount) {
        this.type = type;
        this.name = name;
        this.amount = amount;
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
                        + " " + this.damageType;
            case "armor":
                return this.type
                        + " " + this.name
                        + " " + this.bonus
                        + " " + this.armorType;
            case "misc":
                return this.type
                        + " " + this.name;
            case "currency":
                return this.type
                        + " " + this.name
                        + " " + this.amount;
        }

        return this.type + this.name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int n) {
        amount = n;
    }
}
