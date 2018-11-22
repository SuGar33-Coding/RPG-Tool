package backEnd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    public Map<String, ArrayList<Item>> inv = new HashMap<>();

    public Inventory(File inventoryFile) {
        //set up inventory
        inv.put("Weapon", new ArrayList<>());
        inv.put("Armor", new ArrayList<>());
        inv.put("Misc", new ArrayList<>());
        inv.put("Currency", new ArrayList<>());

        try {
            BufferedReader br = new BufferedReader(new FileReader(inventoryFile));
            String item;
            while ((item = br.readLine()) != null)
                addItem(item);
        } catch (IOException e) {
            System.out.println("Error loading inventory");
            e.printStackTrace();
        }
    }

    public void addItem(String infoLine) {
        String[] stringData = infoLine.split(" ");
        switch (stringData[0]) {
            case "Weapon":
                this.inv.get(stringData[0]).add(new Item(stringData[0], stringData[1], Integer.parseInt(stringData[2]), stringData[3], stringData[4]));
                break;
            case "Armor":
                this.inv.get(stringData[0]).add(new Item(stringData[0], stringData[1], Integer.parseInt(stringData[2]), stringData[3]));
                break;
            case "Currency":
                this.inv.get(stringData[0]).add(new Item(stringData[0], stringData[1], Integer.parseInt(stringData[2])));
                break;
            case "Misc":
                this.inv.get(stringData[0]).add(new Item(stringData[0], stringData[1]));
        }
    }
}
