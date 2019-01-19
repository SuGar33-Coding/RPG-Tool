package GUI;

import backEnd.Inventory;
import com.sun.tools.javac.Main;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JPanel equipDeleteButtons;
    private JPanel armorDeleteButtons;
    private JPanel weaponDeleteButtons;
    private Inventory inventoryClass;
    private String[] types = {"weapon","armor","misc"};
    private JTextArea[] tabs = {weaponArea,armorArea,equipmentArea};
    private JPanel[] deletePanels = {weaponDeleteButtons, armorDeleteButtons, equipDeleteButtons};
    private CharForm parentCharForm;



    public InvForm(CharForm c) {
        parentCharForm = c;
        for(JTextArea tab : tabs)
            tab.setFont(tab.getFont().deriveFont(20f));
        updateFormData(MainFrame.inventory);
    }

    private void updateFormData(Inventory inventory){
        for(int count = 0; count < types.length; count++){
            tabs[count].setText("");
            deletePanels[count].removeAll();
           int length = inventory.inv.get(types[count]).size();
           for(int i = 0; i < length; i++) {
               tabs[count].append(inventory.inv.get(types[count]).get(i).getName() + "\n");
               addDeleteButton(count, i, inventory);
           }
           deletePanels[count].revalidate();
        }
        String[] pieces = {"Copper","Silver","Gold","Electrum","Platinum"};
        JTextField[] pieceFields = {copperField,silverField,goldField,electrumField,platinumField};
       // for(int i = 0; i < pieces.length; i++)
            //inventory.inv.get("currency").
    }

    private void addDeleteButton(int c, int index, Inventory inventory){
        int count = c;
        int i = index;
        JButton delButton = new JButton("Delete");
        delButton.setHorizontalAlignment(SwingConstants.LEFT);
        delButton.setVerticalAlignment(SwingConstants.BOTTOM);
        JPanel delPanel = deletePanels[count];
        delPanel.setLayout(new BoxLayout(delPanel, BoxLayout.PAGE_AXIS));
        delPanel.add(delButton);

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventory.inv.get(types[count]).remove(i);
                updateFormData(MainFrame.inventory);
            }
        });
    }
}