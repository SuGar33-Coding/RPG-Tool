package backEnd;

public class Item{

    /* Generic type */
    String type;
    String name;
    String description;

    /* Weapon */
    //int attackBonus;  See bonus for attack bonus
    String damageDice = "0";
    private boolean isWeapon = false;

    /* Armor */
    int bonus = 0;
    //String armorType;

    boolean isCurrency = false;
    boolean isEquipped = false;

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
     * @param description
     */
    // Removed damageType as parameter, should just include in description if needed.
    public Item(String type, String name, int attackBonus, String damageDice, String description, boolean isEquipped) {
        this.type = type;
        this.name = name;
        this.bonus = attackBonus;
        this.damageDice = damageDice;
        this.description = description;
        this.isEquipped = isEquipped;
        this.isWeapon = true;
    }

    /**
     * For Armor type
     * @param type
     * @param name
     * @param bonus
     * @param description
     */
    // removed armorType because it does not really have any application.
    public Item(String type, String name, int bonus,boolean isEquipped,String description) {
        this.type = type;
        this.name = name;
        this.bonus = bonus;
        this.description = description;
        this.isEquipped = isEquipped;
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
        String sep = "@";
        switch (this.type) {
            case "weapon":
                return this.type
                        + sep + this.name
                        + sep + this.bonus
                        + sep + this.damageDice
                        + sep + this.description
                        + sep + this.isEquipped;
            case "armor":
                return this.type
                        + sep + this.name
                        + sep + this.bonus
                        + sep + this.description
                        + sep + this.isEquipped;
            case "misc":
                return this.type
                        + sep + this.name
                        + sep + this.amount
                        + sep + this.description
                        + sep + this.isCurrency;
        }

        return this.type + this.name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int n) {
        amount = n;
    }

    public boolean isCurrency(){return isCurrency;}

    public String getDescription() {return description;}

    public void setDescription(String s){description = s;}

    public int getBonus(){return bonus;}

    public void setBonus(int n){bonus = n;}

    public boolean isEquipped(){return isEquipped;}

    public void setEquipped(boolean equip){isEquipped = equip;}

    public boolean isWeapon(){return isWeapon;}

    public String getDamageDice(){return damageDice;}

    public void setDamageDice(String s){damageDice = s;}

    public String getType(){return type;}
}
