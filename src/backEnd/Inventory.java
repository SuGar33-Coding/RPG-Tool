package backEnd;

import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    public Map<String, ArrayList<Item>> inv;
    private String sep = "@";

    private String[] types = {"weapon","armor","misc","currency"};

    public String getSep() {
        return sep;
    }

    /**
     * Default constructor
     * Sets up the Map struct
     */
    public Inventory() {
        inv = new HashMap<>();
        for(String type : types)
            inv.put(type, new  ArrayList<>());
    }

    /**
     * Create Inventory object from string
     * DEPRECATED, use JSON
     * @param inventoryFile .txt file with inventory data
     */
    /*public Inventory(File inventoryFile) {
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
    }*/

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
                    addItem(key + sep + dataFile.getJSONObject("inventory").getJSONArray(key).getString(i));
                }
            }
        }

    }

    public void addItem(String infoLine) {
        String[] stringData = infoLine.split(sep);
        switch (stringData[0]) {
            case "weapon":  // weapons must be added in the form "weapon name atkBonus damageDice description isEquipped"
                this.inv.get(stringData[0]).add(new Item(stringData[0], stringData[1], Integer.parseInt(stringData[2]), stringData[3], stringData[4], Boolean.parseBoolean(stringData[5])));
                break;
            case "armor": // armor must be added in the form "armor name isEquipped defBonus description isEquipped"
                this.inv.get(stringData[0]).add(new Item(stringData[0], stringData[1], Integer.parseInt(stringData[2]),Boolean.parseBoolean(stringData[4]), stringData[3]));
                break;
            case "misc": // armor must be added in the form "misc name amnt description isCurrency isEquipped"
                this.inv.get(stringData[0]).add(new Item(stringData[0], stringData[1], Integer.parseInt(stringData[2]), stringData[3], Boolean.parseBoolean(stringData[4])));
                break;
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
                ret += "\t\t\"" + item.toString().substring(item.toString().indexOf(sep) + 1) + "\",\n";
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

    public String[] getTypes() {
        return types;
    }
}
