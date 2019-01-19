package GUI;

import backEnd.Inventory;
import org.json.JSONObject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvForm {
    public JPanel invPanel;
    private JLabel copperLabel;
    private JTextField copperField;
    private JTextField silverField;
    private JTextField goldField;
    private JTextField electrumField;
    private JTextField platinumField;
    private JLabel silverLabel;
    private JLabel goldLabel;
    private JLabel electrumLabel;
    private JLabel platinumLabel;
    private JTabbedPane fullInvPane;
    private JTextArea weaponArea;
    private JTextArea armorArea;
    private JTextArea equipmentArea;
    private JLabel addLabel;
    private Inventory inventoryClass;
    private JTextArea[] tabs = {weaponArea,armorArea,equipmentArea};



    public InvForm() {
        updateFormData(MainFrame.inventory);
    }

    private void updateFormData(Inventory inventory){
        String[] types = {"weapon","armor","misc"};
        for(int count = 0; count < types.length; count++){
           int length = inventory.inv.get(types[count]).size();
           for(int i = 0; i < length; i++)
               tabs[count].append(inventory.inv.get(types[count]).get(i).getName()+"\n");
        }
        String[] pieces = {"Copper","Silver","Gold","Electrum","Platinum"};
        JTextField[] pieceFields = {copperField,silverField,goldField,electrumField,platinumField};
       // for(int i = 0; i < pieces.length; i++)
            //inventory.inv.get("currency").
    }
}