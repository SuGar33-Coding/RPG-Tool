package backEnd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    public Map<String, ArrayList<Item>> items = new HashMap<>();

    public Inventory(File inventoryFile) {
        //set up inventory
        items.put("Weapon", new ArrayList<>());
        items.put("Armor", new ArrayList<>());
        items.put("Misc", new ArrayList<>());
        items.put("Currency", new ArrayList<>());

        try {
            BufferedReader br = new BufferedReader(new FileReader(inventoryFile));
            String item;
            while ((item = br.readLine()) != null)
                addItem(item, items);
        } catch (IOException e) {
            System.out.println("Error loading inventory");
            e.printStackTrace();
        }
    }

    private void addItem(String infoLine, Map<String, ArrayList<Item>> inventory) {
        String[] stringData = infoLine.split(" ");
        switch (stringData[0]) {
            case "Weapon":
                inventory.get(stringData[0]).add(new Item(stringData[0], stringData[1], Integer.parseInt(stringData[2]), stringData[3], stringData[4]));
                break;
            case "Armor":
                inventory.get(stringData[0]).add(new Item(stringData[0], stringData[1], Integer.parseInt(stringData[2]), stringData[3]));
                break;
            case "Currency":
                inventory.get(stringData[0]).add(new Item(stringData[0], stringData[1], Integer.parseInt(stringData[2])));
                break;
            case "Misc":
                inventory.get(stringData[0]).add(new Item(stringData[0], stringData[1]));
        }
    }
}
