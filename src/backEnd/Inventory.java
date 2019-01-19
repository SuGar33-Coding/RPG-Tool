package backEnd;

import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    public Map<String, ArrayList<Item>> inv;

    /**
     * Default constructor
     * Sets up the Map struct
     */
    public Inventory() {
        inv = new HashMap<>();
        inv.put("weapon", new ArrayList<>());
        inv.put("armor", new ArrayList<>());
        inv.put("misc", new ArrayList<>());
        inv.put("currency", new ArrayList<>());
    }

    /**
     * Create Inventory object from string
     * DEPRECATED, use JSON
     * @param inventoryFile .txt file with inventory data
     */
    public Inventory(File inventoryFile) {
        //set up inventory
        inv = new HashMap<>();
        inv.put("weapon", new ArrayList<>());
        inv.put("armor", new ArrayList<>());
        inv.put("misc", new ArrayList<>());
        inv.put("currency", new ArrayList<>());

        try (BufferedReader br = new BufferedReader(new FileReader(inventoryFile))) {
            String item;
            while ((item = br.readLine()) != null)
                addItem(item);
        } catch (IOException e) {
            System.out.println("Error loading inventory");
            e.printStackTrace();
        }
    }

    /**
     * Create Inventory object from JSON file
     * @param dataFile full character JSON file
     */
    public Inventory(JSONObject dataFile) {
        //set up inv structure
        inv = new HashMap<>();
        inv.put("weapon", new ArrayList<>());
        inv.put("armor", new ArrayList<>());
        inv.put("misc", new ArrayList<>());
        inv.put("currency", new ArrayList<>());

        for (String key : dataFile.getJSONObject("inventory").keySet()) {
            if (!dataFile.getJSONObject("inventory").getJSONArray(key).isEmpty()) {
                for (int i = 0; i < dataFile.getJSONObject("inventory").getJSONArray(key).length(); i++) {
                    addItem(key + " " + dataFile.getJSONObject("inventory").getJSONArray(key).getString(i));
                }
            }
        }

    }

    public void addItem(String infoLine) {
        String[] stringData = infoLine.split(" ");
        switch (stringData[0]) {
            case "weapon":
                this.inv.get(stringData[0]).add(new Item(stringData[0], stringData[1], Integer.parseInt(stringData[2]), stringData[3], stringData[4]));
                break;
            case "armor":
                this.inv.get(stringData[0]).add(new Item(stringData[0], stringData[1], Integer.parseInt(stringData[2]), stringData[3]));
                break;
            case "misc":
                this.inv.get(stringData[0]).add(new Item(stringData[0], stringData[1], Integer.parseInt(stringData[2])));
                break;
            //case "misc":
                //this.inv.get(stringData[0]).add(new Item(stringData[0], stringData[1]));
        }
    }


    /**
     * Easy to read and use for JSON
     * @return Inventory String in pretty JSON format
     */
    public String toString() {
        String ret = "{\n";
        for (String key : inv.keySet()) {
            ret += "\t\"" + key + "\": [\n";
            for (Item item : inv.get(key)) {
                ret += "\t\t\"" + item.toString().substring(item.toString().indexOf(" ") + 1) + "\",\n";
            }
            if (!inv.get(key).isEmpty()) {
                ret = ret.substring(0, ret.length() - 2);
                ret += "\n\t],\n";
            } else {
                ret += "\t],\n";
            }
        }
        ret = ret.substring(0, ret.length() - 2);
        ret += "\n}";

        return ret;
    }
}
