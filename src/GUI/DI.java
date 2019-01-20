package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import backEnd.Dicey;
import backEnd.Inventory;
import backEnd.Item;

public class DI {
    public JPanel DicePanel;
    private JTextField numField;
    private JTextField sideField;
    private JTextField buffField;
    private JLabel d;
    private JLabel bufLab;
    private JLabel resLab;
    private JButton backButton;
    private JButton rollButton;
    private JScrollPane resultsScrollPane;
    private JTextArea resultTextArea;
    private JPanel equippedItemsPane;
    private JPanel equippedItemsButtonPane;
    private Dimension itemButtonsDim = new Dimension(175,48);
    private Dimension itemPaneDim = new Dimension(1,18);

    public DI() {  // If roll is accessed from the front screen
        addRollListener();
    }

    public DI(Inventory inventory){  // If roll is accessed from character sheet, equipped items will also appear.
        addRollListener();
        equippedItemsPane.setLayout(new BoxLayout(equippedItemsPane, BoxLayout.PAGE_AXIS));
        equippedItemsButtonPane.setLayout(new BoxLayout(equippedItemsButtonPane, BoxLayout.PAGE_AXIS));
        String[] types = inventory.getTypes();
        for(String type : types)
            if(type == "weapon")
                for(Item weapon : inventory.inv.get("weapon"))
                    if(weapon.isEquipped())
                        addEquippedItemRolls(weapon);

    }

    private void addEquippedItemRolls(Item weapon){
        JLabel weaponName = new JLabel(weapon.getName());
        equippedItemsPane.add(Box.createRigidArea(itemPaneDim));
        equippedItemsPane.add(weaponName);

        JButton attackRollButton = new JButton("Attack Roll");
        JButton damageRollButton = new JButton("Damage Roll");

        JPanel itemButtons = new JPanel();
        itemButtons.setLayout(new FlowLayout());
        itemButtons.setPreferredSize(itemButtonsDim);

        itemButtons.add(attackRollButton);
        itemButtons.add(damageRollButton);

        equippedItemsButtonPane.add(itemButtons);
    }

    private void addRollListener(){
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numf = numField.getText();
                String sidef = sideField.getText();
                String bufff = buffField.getText();
                if(!(numf.isEmpty()) && !(sidef.isEmpty())){  //If fields left empty, do nothing
                    int buff;
                    int num;
                    int sides;
                    try {buff = Integer.parseInt(bufff);} catch (NumberFormatException ex) {buff = 0;}
                    try {num = Integer.parseInt(numf);} catch (NumberFormatException ex) {num = 0;}
                    try {sides = Integer.parseInt(sidef);} catch (NumberFormatException ex) {sides = 0;}

                    int rolls[] = Dicey.Roll(num,sides,buff);
                    String rollString = Dicey.rollToString(rolls,buff);
                    resultTextArea.append("\nResult: " + rollString);
                }
            }
        });
    }


}
