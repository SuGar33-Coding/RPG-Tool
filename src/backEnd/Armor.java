package backEnd;

public class Armor extends Item {
    /* priority in Items list sorting */
    int priority = 1;
    public static final String TYPE = "Armor";

    int bonus;
    String armorType;

    public Armor(String name, int bonus, String armorType) {
        super.name = name;
        this.bonus = bonus;
        this.armorType = armorType;
    }

    /* Getters and setters */
    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public String getArmorType() {
        return armorType;
    }

    public void setArmorType(String armorType) {
        this.armorType = armorType;
    }

    public int getPriority() {
        return priority;
    }
}
