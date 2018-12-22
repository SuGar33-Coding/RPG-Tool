package backEnd;

import org.json.JSONObject;

import java.io.*;
import java.util.*;

public class RPGCharacter {

    public static int calculateActionBonus(int rawStat) {
        return (rawStat - 10) / 2;
    }

    public static ArrayList<String> loadCharFile(String charName) {
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

        return rawStats;
    }

    public static JSONObject loadCharJSON(String charName) {
        String sep = System.getProperty("file.separator");
        String filePath = "Characters" + sep + charName;//.replace(" ","_");  TODO: Decide whether we want under scores(does function without)

        /* Read from file into array of stats */
        String json = "";
        File statsFile = new File(filePath + sep + charName + "_data.json");
        try {
            BufferedReader br = new BufferedReader(new FileReader(statsFile));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            json = sb.toString();
        } catch (IOException e) {
            System.out.println("Error loading character");
            e.printStackTrace();
        }

        return new JSONObject(json);
    }


    public static File getInventoryFile(String charName) {
        String sep = System.getProperty("file.separator");
        String filePath = "Characters" + sep + charName;//.replace(" ","_");
        return new File(filePath + sep + "inventory.txt");
    }

    public static void writeCharFile(ArrayList<String> charData) {
        String sep = System.getProperty("file.separator");
        File dir = new File("Characters" + sep + charData.get(1));
        dir.mkdir();
        try {
            Writer charWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Characters" + sep + charData.get(1) + sep + "stats.txt"), "utf-8"));
            for (int i = 0; i < charData.size(); i++) {
                charWriter.write(charData.get(i));
                charWriter.write("\n");
            }
            charWriter.close();
        } catch (IOException ex) {
            System.out.println("Something went wrong while writing stats file.");
            ex.printStackTrace();
        }
    }

    public static void writeCharJSON(JSONObject charData) {
        String sep = System.getProperty("file.separator");
        File dir = new File("Characters" + sep + charData.get("char name"));
        dir.mkdir();
        try (Writer charWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Characters" + sep + charData.get("char name") + sep + charData.get("char name") + "_data.json"), "utf-8"));) {
            charWriter.write(charData.toString(4));
        } catch (IOException ex) {
            System.out.println("Something went wrong while writing data file.");
            ex.printStackTrace();
        }
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
}
