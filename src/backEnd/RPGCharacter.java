package backEnd;

import org.json.JSONObject;

public class RPGCharacter {
    private String playerName;
    private String charName;
    private String charClass;
    private String race;
    private String level;
    private String alignment;
    private String xp;
    private boolean inspiration;
    private String ac;
    private String speed;
    private String maxHP;
    private String currentHP;
    private String hitDiceSize;
    private String currentHitDice;
    private String strength;
    private String dexterity;
    private String constitution;
    private String intelligence;
    private String wisdom;
    private String charisma;

    /* sorted skills (unimplemented except using skillsAlphabetical) */
    boolean[][] skills = {
            new boolean[2],
            new boolean[4],
            new boolean[1],
            new boolean[6],
            new boolean[6],
            new boolean[5]};

    /* alphabetical skills */
    boolean[] skills1D;

    private String SB;
    private String DB;
    private String CB;
    private String IB;
    private String WB;
    private String ChB;
    private String proficiency;
    private String maxHitDiceAmount;

    public RPGCharacter() {
        this.playerName = "_DEFAULT";
        this.charName = "_DEFAULT";
        this.charClass = "_DEFAULT";
        this.race = "_DEFAULT";
        this.level = "_DEFAULT";
        this.alignment = "_DEFAULT";
        this.xp = "_DEFAULT";
        this.inspiration = false;
        this.ac = "_DEFAULT";
        this.speed = "_DEFAULT";
        this.maxHP = "_DEFAULT";
        this.currentHP = "_DEFAULT";
        this.hitDiceSize = "_DEFAULT";
        this.currentHitDice = "_DEFAULT";
        this.strength = "_DEFAULT";
        this.dexterity = "_DEFAULT";
        this.constitution = "_DEFAULT";
        this.intelligence = "_DEFAULT";
        this.wisdom = "_DEFAULT";
        this.charisma = "_DEFAULT";

        this.skills1D = new boolean[10];

        this.SB = "_DEFAULT";
        this.DB = "_DEFAULT";
        this.CB = "_DEFAULT";
        this.IB = "_DEFAULT";
        this.WB = "_DEFAULT";
        this.ChB = "_DEFAULT";
        this.proficiency = "_DEFAULT";
        this.maxHitDiceAmount = this.level;
    }

    public RPGCharacter(JSONObject charStats) {
        this.playerName = charStats.getString("player name");
        this.charName = charStats.getString("char name");
        this.charClass = charStats.getString("class");
        this.race = charStats.getString("race");
        this.level = charStats.getString("level");
        this.alignment = charStats.getString("alignment");
        this.xp = charStats.getString("xp");
        this.inspiration = charStats.getBoolean("inspiration");
        this.ac = charStats.getString("ac");
        this.speed = charStats.getString("speed");
        this.maxHP = charStats.getString("max HP");
        this.currentHP = charStats.getString("current HP");
        this.hitDiceSize = charStats.getString("hit dice size");
        this.currentHitDice = charStats.getString("current hit dice");
        this.strength = charStats.getString("strength");
        this.dexterity = charStats.getString("dexterity");
        this.constitution = charStats.getString("constitution");
        this.intelligence = charStats.getString("intelligence");
        this.wisdom = charStats.getString("wisdom");
        this.charisma = charStats.getString("charisma");

        boolean[][] skills = {new boolean[2],
                new boolean[4],
                new boolean[1],
                new boolean[6],
                new boolean[6],
                new boolean[5]};
        int k = 0;
        for (int i = 0; i < skills.length; i++) {
            for (int j = 0; j < skills[i].length; j++) {
                skills[i][j] = charStats.getJSONArray("skill bonuses").getBoolean(k);
                k++;
            }
        }
        this.skills = skills;

        this.proficiency = Integer.toString(calculateProficiencyBonus(Integer.parseInt(this.level)));
    }

    public static int calculateActionBonus(String rawStat) {
        return (Integer.parseInt(rawStat) - 10) / 2;
    }

    public static int calculateProficiencyBonus(int lvl) {
        int bonus = 2;
        if (lvl >= 5) {
            bonus = 3;
            if (lvl >= 9) {
                bonus = 4;
                if (lvl >= 13) {
                    bonus = 5;
                    if (lvl >= 17)
                        bonus = 6;
                }
            }
        }

        return bonus;
    }

    /* Look at all these getters and setters */
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getCharClass() {
        return charClass;
    }

    public void setCharClass(String charClass) {
        this.charClass = charClass;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
        this.proficiency = Integer.toString(calculateProficiencyBonus(Integer.parseInt(level)));
        this.maxHitDiceAmount = level;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getXp() {
        return xp;
    }

    public void setXp(String xp) {
        this.xp = xp;
    }

    public boolean isInspiration() {
        return inspiration;
    }

    public void setInspiration(boolean inspiration) {
        this.inspiration = inspiration;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(String maxHP) {
        this.maxHP = maxHP;
    }

    public String getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(String currentHP) {
        this.currentHP = currentHP;
    }

    public String getHitDiceSize() {
        return hitDiceSize;
    }

    public void setHitDiceSize(String hitDiceSize) {
        this.hitDiceSize = hitDiceSize;
    }

    public String getCurrentHitDice() {
        return currentHitDice;
    }

    public void setCurrentHitDice(String currentHitDice) {
        this.currentHitDice = currentHitDice;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
        this.SB = Integer.toString(calculateActionBonus(strength));
    }

    public String getDexterity() {
        return dexterity;
    }

    public void setDexterity(String dexterity) {
        this.dexterity = dexterity;
        this.DB = Integer.toString(calculateActionBonus(dexterity));
    }

    public String getConstitution() {
        return constitution;
    }

    public void setConstitution(String constitution) {
        this.constitution = constitution;
        this.CB = Integer.toString(calculateActionBonus(constitution));
    }

    public String getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
        this.SB = Integer.toString(calculateActionBonus(intelligence));
    }

    public String getWisdom() {
        return wisdom;
    }

    public void setWisdom(String wisdom) {
        this.wisdom = wisdom;
        this.WB = Integer.toString(calculateActionBonus(wisdom));
    }

    public String getCharisma() {
        return charisma;
    }

    public void setCharisma(String charisma) {
        this.charisma = charisma;
        this.ChB = Integer.toString(calculateActionBonus(charisma));
    }

    public boolean[][] getSkills() {
        return skills;
    }

    public void setSkills(boolean[][] skills) {
        this.skills = skills;
    }

    public boolean[] getSkills1D() {
        return skills1D;
    }

    public void setSkills1D(boolean[] skills1D) {
        this.skills1D = skills1D;

        int k = 0;
        for (int i = 0; i < this.skills.length; i++) {
            for (int j = 0; j < this.skills[i].length; j++) {
                this.skills[i][j] = skills1D[k];
                k++;
            }
        }
    }

    public String getSB() {
        return SB;
    }

    public void setSB(String SB) {
        this.SB = SB;
    }

    public String getDB() {
        return DB;
    }

    public void setDB(String DB) {
        this.DB = DB;
    }

    public String getCB() {
        return CB;
    }

    public void setCB(String CB) {
        this.CB = CB;
    }

    public String getIB() {
        return IB;
    }

    public void setIB(String IB) {
        this.IB = IB;
    }

    public String getWB() {
        return WB;
    }

    public void setWB(String WB) {
        this.WB = WB;
    }

    public String getChB() {
        return ChB;
    }

    public void setChB(String chB) {
        ChB = chB;
    }

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }
}
