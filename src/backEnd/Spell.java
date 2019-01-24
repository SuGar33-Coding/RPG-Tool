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
    private boolean isPrepared;
    //private String[] parameters = {name,level,castingTime,range,duration,damageDice,description,Boolean.toString(isPrepared)};
    private String sep = "@";

    public Spell(String name, String level, String castingTime, String range, String duration, String damageDice, String description, boolean isPrepared){

        this.name = name;
        this.level = level;
        this.castingTime = castingTime;
        this.range = range;
        this.duration = duration;
        this.damageDice = damageDice;
        this.description = description;
        this.isPrepared = isPrepared;

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
                description +sep+
                Boolean.toString(isPrepared);

        return rhet;
    }

    public String getName(){return name;}

    public String getDamageDice(){return damageDice;}

    public boolean isPrepared(){return isPrepared;}

    public void setPrepared(boolean b){isPrepared = b;}

    public String getLevel() {
        return level;
    }

    public String getCastingTime() {
        return castingTime;
    }

    public String getRange() {
        return range;
    }

    public String getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setCastingTime(String castingTime) {
        this.castingTime = castingTime;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setDamageDice(String damageDice) {
        this.damageDice = damageDice;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
