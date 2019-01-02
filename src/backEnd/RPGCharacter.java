package backEnd;

import org.json.JSONObject;

import java.io.*;

public class RPGCharacter {

    public static int calculateActionBonus(int rawStat) {
        return (rawStat - 10) / 2;
    }

    /**
     * Main write to character JSON file
     * @param charData
     */
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

    /**
     * Main loading from JSON
     * @param charName
     * @return
     */
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
