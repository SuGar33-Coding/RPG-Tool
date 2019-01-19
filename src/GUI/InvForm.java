package GUI;

import backEnd.Inventory;
import backEnd.Item;
import com.sun.tools.javac.Main;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
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
    private JPanel equipButtons;
    private JPanel armorButtons;
    private JPanel weaponButtons;
    private JLabel addEquip;
    private JLabel eNameLabel;
    private JTextField eNameField;
    private JButton eAddButton;
    private JLabel eAmountLab;
    private JTextField amountTextField;
    private JLabel addArmorLab;
    private Inventory inventoryClass;
    private String[] types = {"weapon","armor","misc"};
    private JTextArea[] tabs = {weaponArea,armorArea,equipmentArea};
    private JPanel[] buttonPanels = {weaponButtons, armorButtons,equipButtons};
    private CharForm parentCharForm;
    private Dimension itemPanelDim = new Dimension(110,48);
    private String sep;



    public InvForm(CharForm c) {
        sep = MainFrame.inventory.getSep();
        parentCharForm = c;
        for(JTextArea tab : tabs)
            tab.setFont(tab.getFont().deriveFont(12f));
        for(JPanel panel : buttonPanels)
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        updateFormData(MainFrame.inventory);
        eAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.inventory.addItem("misc"+sep+eNameField.getText()+sep+amountTextField.getText()+sep+"false");
                updateFormData(MainFrame.inventory);
            }
        });
    }

    private void updateFormData(Inventory inventory){
        for(int count = 0; count < types.length; count++){
            tabs[count].setText("");
            buttonPanels[count].removeAll();
           int length = inventory.inv.get(types[count]).size();
           for(int i = 0; i < length; i++) {
               if(types[count].equals("misc")) {  // If the type of the object is misc, we will include the amount of the item in front and not include a delete button.
                   tabs[count].append(inventory.inv.get(types[count]).get(i).getAmount() + " ");  // Instead of deleting, misc items will be removed when amount goes below 0.
                   addPlusMinus(count,i,inventory);
               }
               else
                   addDeleteButton(count,i,inventory);

               tabs[count].append(inventory.inv.get(types[count]).get(i).getName() + "\n"+ inventory.inv.get(types[count]).get(i).getDescription()+"\n\n");
           }
           buttonPanels[count].revalidate();
        }
        String[] pieces = {"Copper","Silver","Gold","Electrum","Platinum"};
        JTextField[] pieceFields = {copperField,silverField,goldField,electrumField,platinumField};
       // for(int i = 0; i < pieces.length; i++)
            //inventory.inv.get("currency").
    }

    private void addPlusMinus(int c, int index, Inventory inventory){
        int count = c;
        int i = index;
        JButton plusButton = new JButton("+");
        JButton minButton = new JButton("-");
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new FlowLayout());
        itemPanel.add(plusButton);
        itemPanel.add(minButton);
        itemPanel.setMaximumSize(itemPanelDim);
        buttonPanels[count].add(itemPanel);
        buttonPanels[count].revalidate();
        buttonPanels[count].repaint();

        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = inventory.inv.get(types[count]).get(i);
                if((e.getModifiers() & InputEvent.SHIFT_MASK) != 0)  // Allows users to shift click to add 100, ctrl click to add 10
                    item.setAmount(item.getAmount()+100);
                else if ((e.getModifiers() & InputEvent.CTRL_MASK) != 0)
                    item.setAmount(item.getAmount()+10);
                else
                    item.setAmount(item.getAmount()+1);
                updateFormData(MainFrame.inventory);
            }
        });

        minButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = inventory.inv.get(types[count]).get(i);
                if((e.getModifiers() & InputEvent.SHIFT_MASK) != 0)
                    item.setAmount(item.getAmount()-100);
                else if ((e.getModifiers() & InputEvent.CTRL_MASK) != 0)
                    item.setAmount(item.getAmount()-10);
                else
                    item.setAmount(item.getAmount()-1);

                if(item.getAmount() < 0)
                    inventory.inv.get(types[count]).remove(i);
                updateFormData(MainFrame.inventory);
            }
        });
    }

    private void addDeleteButton(int c, int index, Inventory inventory){
        int count = c;
        int i = index;
        JButton delButton = new JButton("Delete");
        delButton.setHorizontalAlignment(SwingConstants.LEFT);
        delButton.setVerticalAlignment(SwingConstants.BOTTOM);
        JPanel delPanel = buttonPanels[count];
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new FlowLayout());
        itemPanel.add(delButton);
        itemPanel.setMaximumSize(itemPanelDim);
        delPanel.add(itemPanel);
        buttonPanels[count].revalidate();
        buttonPanels[count].repaint();

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventory.inv.get(types[count]).remove(i);
                updateFormData(MainFrame.inventory);
            }
        });
    }
}