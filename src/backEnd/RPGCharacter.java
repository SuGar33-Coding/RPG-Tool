package backEnd;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class RPGCharacter {
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

    /* Skill stats */
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    /* Items list */
    LinkedList<Item> items = new LinkedList<>();

    public RPGCharacter(String filePath) {
        ArrayList<String> rawStats = new ArrayList<String>();

        /* Read from file into array of stats */
        try {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String stat;
            while ((stat = br.readLine()) != null)
                rawStats.add(stat);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        this.strength = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.dexterity = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.constitution = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.intelligence = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.wisdom = Integer.parseInt(rawStats.get(counter)); counter ++;
        this.charisma = Integer.parseInt(rawStats.get(counter)); counter ++;


        for (int i = 0; i < this.skills.length; i++) {
            for (int j = 0; j < this.skills[i].length; j++) {
                skills[i][j] = Boolean.parseBoolean(rawStats.get(counter));
                counter ++;
            }
        }

        /* (hopefully) Garbage collect that shit */
        rawStats = null;

        calculateRealStats();
        // TODO: calculate relevant stats
    }

    /**
     * Get's the REAL stats
     */
    private void calculateRealStats() {
        this.strength = (this.strength - 10)/2;
        this.dexterity = (this.dexterity - 10)/2;
        this.constitution = (this.constitution - 10)/2;
        this.intelligence = (this.intelligence - 10)/2;
        this.wisdom = (this.wisdom - 10)/2;
        this.charisma = (this.charisma - 10)/2;
    }

    public static void createNewCharFiles() {
        String charName = "Ikilian";
        File dir = new File("Characters/" + charName);
        dir.mkdir();
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Characters/" + charName + "/stats.txt"), "utf-8"));
            writer.write("Something");
        } catch (IOException ex) {
            // Report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }
    }

    // TODO: Usse ENUMS and arrays cause its more sugar33
    /* Getters */
    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getCharisma() {
        return charisma;
    }
}
