package GUI;

import backEnd.Inventory;
import backEnd.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

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
    private JTextField eAmountTextField;
    private JLabel addArmorLab;
    private JTextArea currencyArea;
    private JPanel currencyButtons;
    private JTextField cNameField;
    private JTextField cAmountField;
    private JButton cAddButton;
    private JLabel addCurrency;
    private JLabel cNameLabel;
    private JTextField eDescriptionTextField;
    private JTextField cDescriptionField;
    private Inventory inventoryClass;
    private String[] types = {"weapon","armor","misc"};
    private JTextArea[] tabs = {weaponArea,armorArea,equipmentArea,currencyArea};
    private JPanel[] buttonPanels = {weaponButtons, armorButtons,equipButtons,currencyButtons};
    private int MISC_POS = 2;
    private int CURRENCY_POS = 3;
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
                MainFrame.inventory.addItem("misc"+sep+eNameField.getText()+sep+ eAmountTextField.getText()+sep+eDescriptionTextField.getText()+sep+"false");
                updateMiscCurrency(MainFrame.inventory);
            }
        });
        cAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.inventory.addItem("misc"+sep+cNameField.getText()+sep+ cAmountField.getText()+sep+cDescriptionField.getText()+sep+"true");
                updateMiscCurrency(MainFrame.inventory);
            }
        });
    }

    private void updateFormData(Inventory inventory){
        for(int count = 0; count < types.length; count++){
            tabs[count].setText("");
           if(types[count].equals("misc"))
               updateMiscCurrency(inventory);
           else
               updateWeaponArmor(count,inventory);
           }

        //String[] pieces = {"Copper","Silver","Gold","Electrum","Platinum"};
        //JTextField[] pieceFields = {copperField,silverField,goldField,electrumField,platinumField};
       // for(int i = 0; i < pieces.length; i++)
            //inventory.inv.get("currency").
    }

    public void updateMiscCurrency(Inventory inventory){
        int count = MISC_POS;
        int length = inventory.inv.get(types[count]).size();

        buttonPanels[MISC_POS].removeAll();  // Clears everything on button panels and text areas
        buttonPanels[CURRENCY_POS].removeAll();
        tabs[MISC_POS].setText("");
        tabs[CURRENCY_POS].setText("");

        for(int i = 0; i < length; i++) {
            Item item = inventory.inv.get(types[MISC_POS]).get(i);

            if (item.isCurrency())
                count = CURRENCY_POS;
            else
                count = MISC_POS;

            JTextArea tab = tabs[count];

            tab.append(item.getAmount() + " ");
            addPlusMinus(count, i, inventory);
            tab.append(item.getName() + "\n" + item.getDescription() + "\n\n");
        }
        buttonPanels[MISC_POS].revalidate();
        buttonPanels[CURRENCY_POS].revalidate();
        buttonPanels[MISC_POS].repaint();
        buttonPanels[CURRENCY_POS].repaint();
    }

    public void updateWeaponArmor(int count, Inventory inventory) {
        int length = inventory.inv.get(types[count]).size();
        JPanel buttonPanel = buttonPanels[count];
        buttonPanel.removeAll();
        tabs[count].setText("");

        for(int i = 0; i < length; i++) {
            Item item = inventory.inv.get(types[count]).get(i);
            tabs[count].append(item.getName() + "\n" + item.getDescription() + "\n\n");
            addDeleteButton(count,i,inventory);
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void addPlusMinus(int c, int index, Inventory inventory){
        int count = c;
        int i = index;
        JPanel buttonPanel;
        JButton plusButton = new JButton("+");
        JButton minButton = new JButton("-");
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new FlowLayout());
        itemPanel.add(plusButton);
        itemPanel.add(minButton);
        itemPanel.setMaximumSize(itemPanelDim);

        buttonPanel = buttonPanels[count];
        buttonPanel.add(itemPanel);

        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = inventory.inv.get(types[MISC_POS]).get(i);
                if((e.getModifiers() & InputEvent.SHIFT_MASK) != 0)  // Allows users to shift click to add 100, ctrl click to add 10
                    item.setAmount(item.getAmount()+100);
                else if ((e.getModifiers() & InputEvent.CTRL_MASK) != 0)
                    item.setAmount(item.getAmount()+10);
                else
                    item.setAmount(item.getAmount()+1);
                updateMiscCurrency(MainFrame.inventory);
            }
        });

        minButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = inventory.inv.get(types[MISC_POS]).get(i);
                if((e.getModifiers() & InputEvent.SHIFT_MASK) != 0)
                    item.setAmount(item.getAmount()-100);
                else if ((e.getModifiers() & InputEvent.CTRL_MASK) != 0)
                    item.setAmount(item.getAmount()-10);
                else
                    item.setAmount(item.getAmount()-1);

                if(item.getAmount() < 0)
                    inventory.inv.get(types[MISC_POS]).remove(i);
                updateMiscCurrency(MainFrame.inventory);
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

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventory.inv.get(types[count]).remove(i);
                updateFormData(MainFrame.inventory);
            }
        });
    }
}