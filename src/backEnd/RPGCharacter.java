package backEnd;

import java.io.*;
import java.util.*;

public class RPGCharacter {
    private int proficiencyBonus;
    /* Basic character stats */
    private String playerName;
    private String name;
    private String characterClass;
    private String race;
    private int level;
    private String alignment;
    private int xp;
    private boolean inspiration;
    private int ac;
    private int speed;
    private int maxHP;
    private int currentHP;
    private int hitDiceSides;
    private int hitDiceAmount;
    private int currentHitDiceAmount;

    public boolean[][] skills = {new boolean[2], new boolean[4], new boolean[1], new boolean[6], new boolean[6], new boolean[5]};
    /*
    Strength Skills: Str Save, Athletics
    Dex Skills: Dex Save, Acrobatics, Sleight of Hand, Stealth
    Constitution Skills: Cons Save
    Intelligence Skills: Int Save, Arcana, History, Investigation, Nature, Religion
    Wisdom Skills: Wis Save, Animal handling, Insight, Medicine, Perception, Survival
    Charisma Skills: Cha Save, Deception, Intimidation, Performance, Persuasion
     */

    /* Raw skill stats */
    private int rawStrength;
    private int rawDexterity;
    private int rawConstitution;
    private int rawIntelligence;
    private int rawWisdom;
    private int rawCharisma;

    /* Skill stats */
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    /* deprecated version of inventory data structure
    public LinkedList<Item> items = new LinkedList<>() {

        public boolean add(Item i) {
            super.add(i);
            Collections.sort(items);
            return true;
        }
    };
    */

    Inventory inventory;

    public RPGCharacter(String charName) {  //character's name


        // Changed so that this method handles creating file path so as to not be redundant in other files.
        ArrayList<String> rawStats = new ArrayList<>();
        String sep = System.getProperty("file.separator");
        String filePath = "Characters" + sep + charName;//.replace(" ","_");  TODO: Decide whether we want under scores(does function without)

        /* Read from file into array of stats */
        try {
            File statsFile = new File(filePath + sep + "stats.txt");
            BufferedReader br = new BufferedReader(new FileReader(statsFile));
            String stat;
            while ((stat = br.readLine()) != null)
                rawStats.add(stat);
            br.close();
        } catch (IOException e) {
            System.out.println("Error loading character");
            e.printStackTrace();
        }

        File inventoryFile = new File(filePath + sep + "inventory.txt");
        inventory = new Inventory(inventoryFile);

        int counter = 0; //to avoid hardcoding the indexes for reading from file
        this.playerName = rawStats.get(counter); counter ++;
        this.name = rawStats.get(counter); counter ++;
        this.characterClass = rawStats.get(counter); counter ++;
        this.race = rawStats.get(counter); counter ++;
        this.level = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.alignment = rawStats.get(counter); counter ++;
        this.xp = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.inspiration = Boolean.parseBoolean(rawStats.get(counter)); counter ++;
        this.ac = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.speed = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.maxHP = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.currentHP = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.hitDiceSides = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.hitDiceAmount = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.currentHitDiceAmount = Integer.parseInt(rawStats.get(counter)); counter ++;

        this.rawStrength = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.rawDexterity = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.rawConstitution = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.rawIntelligence = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.rawWisdom = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.rawCharisma = Integer.parseInt(rawStats.get(counter)); counter ++;


        for (int i = 0; i < this.skills.length; i++) {
            for (int j = 0; j < this.skills[i].length; j++) {
                skills[i][j] = Boolean.parseBoolean(rawStats.get(counter));
                counter ++;
            }
        }

        calculateRealStats();
        this.proficiencyBonus = calculateProficiencyBonus(this.level);
        // TODO: calculate relevant stats
    }

    /**
     * Get's the REAL stats
     */
    private void calculateRealStats() {
        this.strength = (this.rawStrength - 10)/2;
        this.dexterity = (this.rawDexterity - 10)/2;
        this.constitution = (this.rawConstitution - 10)/2;
        this.intelligence = (this.rawIntelligence - 10)/2;
        this.wisdom = (this.rawWisdom - 10)/2;
        this.charisma = (this.rawCharisma - 10)/2;
    }

    /* deprecated
    private void addItem(String infoLine, Map<String, ArrayList<Item>> inventory) {
        String[] stringData = infoLine.split(" ");
        for (int i = 0; i < stringData.length; i++) {
            inventory.get(stringData[0]).add(new Item());
        }
    }
    */

    public static void updateCharFiles(ArrayList<String> data) {
        String charName = data.get(1);//.replace(" ","_"); have to decide whether we want underscores, does function without
        String sep = System.getProperty("file.separator");
        File dir = new File("Characters" + sep + charName);
        dir.mkdir();
        try {
            Writer charWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Characters" + sep + charName + sep + "stats.txt"), "utf-8"));
            for(int i = 0; i < data.size();i++){
                charWriter.write(data.get(i));
                charWriter.write("\n");
            }
            charWriter.close();
        } catch (IOException ex) {
            System.out.println("Something went wrong while writing stats file.");
        }
        try {
            Writer inventoryWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Characters" + sep + charName + sep + "inventory.txt"), "utf-8"));
            inventoryWriter.close();
        } catch (IOException ex) {
            System.out.println("Something went wrong while writing inventory file.");
        }
    }

    // TODO: Use ENUMS and arrays cause its more sugar33
    /* Getters & Setters*/

    public int getProficiencyBonus() {
        return proficiencyBonus;
    }

    public void setProficiencyBonus(int proficiencyBonus) {
        this.proficiencyBonus = proficiencyBonus;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public boolean isInspiration() {
        return inspiration;
    }

    public void setInspiration(boolean inspiration) {
        this.inspiration = inspiration;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getHitDiceSides() {
        return hitDiceSides;
    }

    public void setHitDiceSides(int hitDiceSides) {
        this.hitDiceSides = hitDiceSides;
    }

    public int getHitDiceAmount() {
        return hitDiceAmount;
    }

    public void setHitDiceAmount(int hitDiceAmount) {
        this.hitDiceAmount = hitDiceAmount;
    }

    public int getCurrentHitDiceAmount() {
        return currentHitDiceAmount;
    }

    public void setCurrentHitDiceAmount(int currentHitDiceAmount) {
        this.currentHitDiceAmount = currentHitDiceAmount;
    }

    public int getRawStrength() {
        return rawStrength;
    }

    public void setRawStrength(int rawStrength) {
        this.rawStrength = rawStrength;
    }

    public int getRawDexterity() {
        return rawDexterity;
    }

    public void setRawDexterity(int rawDexterity) {
        this.rawDexterity = rawDexterity;
    }

    public int getRawConstitution() {
        return rawConstitution;
    }

    public void setRawConstitution(int rawConstitution) {
        this.rawConstitution = rawConstitution;
    }

    public int getRawIntelligence() {
        return rawIntelligence;
    }

    public void setRawIntelligence(int rawIntelligence) {
        this.rawIntelligence = rawIntelligence;
    }

    public int getRawWisdom() {
        return rawWisdom;
    }

    public void setRawWisdom(int rawWisdom) {
        this.rawWisdom = rawWisdom;
    }

    public int getRawCharisma() {
        return rawCharisma;
    }

    public void setRawCharisma(int rawCharisma) {
        this.rawCharisma = rawCharisma;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public static int calculateProficiencyBonus(int lvl){
        int bonus = 2;
        if(lvl >= 5){
            bonus = 3;
            if(lvl >= 9){
                bonus = 4;
                if(lvl >= 13){
                    bonus = 5;
                    if(lvl >= 17)
                        bonus = 6;
                }
            }
        }

        return bonus;
    }
}
