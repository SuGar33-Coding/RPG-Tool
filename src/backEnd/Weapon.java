package backEnd;

public class Weapon extends Item {
    /* priority in Items list sorting */
    int priority = 0;

    int attackBonus;
    String damageDice;
    String damageType;

    public Weapon(String name, int attackBonus, String damageDice, String damageType) {
        super.name = name;
        this.attackBonus = attackBonus;
        this.damageDice = damageDice;
        this.damageType = damageType;
    }

    /* Getters and setters */
    public int getAttackBonus() {
        return attackBonus;
    }

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }

    public String getDamageDice() {
        return damageDice;
    }

    public void setDamageDice(String damageDice) {
        this.damageDice = damageDice;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public int getPriority() {
        return priority;
    }
}
