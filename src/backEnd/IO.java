package backEnd;

import GUI.MainFrame;
import org.json.JSONObject;

import java.io.*;

public class IO {

    private static String filepath = MainFrame.getFilepath();

    /**
     * Main write to character JSON file
     * @param charData
     */
    public static void writeCharJSON(JSONObject charData,String f) {
        String sep = System.getProperty("file.separator");
        File dir = new File(filepath +  sep + charData.get("char name"));
        dir.mkdir();
        try (Writer charWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath +  sep + charData.get("char name") + sep + charData.get("char name") + "_data.json"), "utf-8"));) {
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
        String path = filepath + sep + charName;//.replace(" ","_");  TODO: Decide whether we want under scores(does function without)

        /* Read from file into array of stats */
        String json = "";
        File statsFile = new File(path + sep + charName + "_data.json");
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
}
