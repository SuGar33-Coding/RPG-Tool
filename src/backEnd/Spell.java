package backEnd;

public class Spell {

    private String name;
    private String level;
    private String castingTime;
    private String range;
    private String duration;
    private String damageDice;
    //private String skillUsed;   Turning this into something selected in spellbook, where you can set what magic skill you're using
    private String description;
    private String sep = "@";

    public Spell(String name, String level, String castingTime, String range, String duration, String damageDice, String description){

        this.name = name;
        this.level = level;
        this.castingTime = castingTime;
        this.range = range;
        this.duration = duration;
        this.damageDice = damageDice;
        this.description = description;

    }

    public String toString(){
        String rhet = "";
        rhet +=
                name +sep+
                level +sep+
                castingTime +sep+
                range +sep+
                duration +sep+
                damageDice +sep+
                description;

        return rhet;
    }
}
