package backEnd;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    public HashMap<String, ArrayList<Item>> inv = new HashMap<>();

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

    public void writeInvFile(String charName) {
        String sep = System.getProperty("file.separator");
        File dir = new File("Characters" + sep + charName);
        try {
            Writer inventoryWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Characters" + sep + charName + sep + "inventory.txt"), "utf-8"));
            for (ArrayList<Item> itemType : this.inv.values()){
                for (Item i : itemType) {
                    inventoryWriter.write(i.toString());
                    inventoryWriter.write("\n");
                }
            }
            inventoryWriter.close();
        } catch (IOException ex) {
            System.out.println("Something went wrong while writing inventory file.");
            ex.printStackTrace();
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
