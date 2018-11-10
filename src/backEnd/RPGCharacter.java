package backEnd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

    public boolean[][] skills = {new boolean[2], new boolean[4], new boolean[1], new boolean[6], new boolean[6], new boolean[5]};

    /* Skill stats */
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    public RPGCharacter(String filePath) {
        ArrayList<String> rawStats = new ArrayList<String>();

        try {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String stat;
            while ((stat = br.readLine()) != null)
                rawStats.add(stat);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.playerName = rawStats.remove(0);
        this.name = rawStats.remove(0);
        this.characterClass = rawStats.remove(0);
        this.race = rawStats.remove(0);
        this.level = Integer.parseInt(rawStats.remove(0));
        this.alignment = rawStats.remove(0);
        this.xp = Integer.parseInt(rawStats.remove(0));
        this.inspiration = Boolean.parseBoolean(rawStats.remove(0));
        this.ac = Integer.parseInt(rawStats.remove(0));
        this.speed = Integer.parseInt(rawStats.remove(0));
        this.maxHP = Integer.parseInt(rawStats.remove(0));
        this.currentHP = Integer.parseInt(rawStats.remove(0));

        this.strength = Integer.parseInt(rawStats.remove(0));
        this.dexterity = Integer.parseInt(rawStats.remove(0));
        this.constitution = Integer.parseInt(rawStats.remove(0));
        this.intelligence = Integer.parseInt(rawStats.remove(0));
        this.wisdom = Integer.parseInt(rawStats.remove(0));
        this.charisma = Integer.parseInt(rawStats.remove(0));

        for (int i = 0; i < this.skills.length; i++) {
            for (int j = 0; j < this.skills[i].length; j++) {
                skills[i][j] = Boolean.parseBoolean(rawStats.remove(0));
            }
        }


        // TODO: calculate relevant stats
    }
}
