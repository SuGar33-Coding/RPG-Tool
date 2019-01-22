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
    private JTextField aNameField;
    private JTextField aBonusField;
    private JButton aAddButton;
    private JTextField aDescriptionField;
    private JTextField wNameField;
    private JTextField wBonusField;
    private JTextField wDamageField;
    private JTextField wDescriptionField;
    private JButton wAddButton;
    private JPanel weaponTab;
    private JPanel armorTab;
    private JPanel equipmentTab;
    private JPanel currencyTab;
    private Inventory inventoryClass;
    private String[] types = MainFrame.inventory.getTypes();
    private JPanel[] tabs = {weaponTab,armorTab,equipmentTab,currencyTab};
    private JTextArea[] areas = {weaponArea,armorArea,equipmentArea,currencyArea};
    private JPanel[] buttonPanels = {weaponButtons, armorButtons,equipButtons,currencyButtons};
    private int WEAPON_POS = 0;
    private int ARMOR_POS = 1;
    private int MISC_POS = 2;
    private int CURRENCY_POS = 3;
    private CharForm parentCharForm;
    private int buttonPanelWidth = 275;
    private Dimension itemPanelDim = new Dimension(buttonPanelWidth,48);
    private String sep;



    public InvForm(CharForm c) {
        sep = MainFrame.inventory.getSep();
        parentCharForm = c;
        Color background = invPanel.getBackground().brighter();

        for(JTextArea area : areas) {
            area.setFont(area.getFont().deriveFont(20f));
            area.setBackground(background);
        }
        for(JPanel tab : tabs)
            tab.setBackground(background);
        for(JPanel panel : buttonPanels) {
            panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
            Dimension d = panel.getMinimumSize();
            d.width = buttonPanelWidth;
            panel.setMinimumSize(d);
            panel.setBackground(background);
        }

        updateFormData(MainFrame.inventory);


        eAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] buttonFields = {eNameField,eAmountTextField,eDescriptionTextField};
                String infoLine = "misc"+sep;
                for(JTextField field : buttonFields) {
                    infoLine += field.getText() + sep;
                    field.setText("");
                }
                infoLine += "false";
                MainFrame.inventory.addItem(infoLine);
                updateMiscCurrency(MainFrame.inventory);
            }
        });
        cAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] buttonFields = {cNameField,cAmountField,cDescriptionField};
                String infoLine = "misc"+sep;
                for(JTextField field : buttonFields) {
                    infoLine += field.getText() + sep;
                    field.setText("");
                }
                infoLine += "true";
                MainFrame.inventory.addItem(infoLine);
                updateMiscCurrency(MainFrame.inventory);
            }
        });

        aAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] buttonFields = {aNameField,aBonusField,aDescriptionField};
                String infoLine = "armor"+sep;
                for(JTextField field : buttonFields) {
                    infoLine += field.getText() + sep;
                    field.setText("");
                }
                infoLine += "false";
                MainFrame.inventory.addItem(infoLine);
                updateWeaponArmor(ARMOR_POS,MainFrame.inventory);
            }
        });

        wAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] buttonFields = {wNameField,wBonusField,wDamageField,wDescriptionField};
                String infoLine = "weapon"+sep;
                for(JTextField field : buttonFields) {
                    infoLine += field.getText() + sep;
                    field.setText("");
                }
                infoLine += "false";
                MainFrame.inventory.addItem(infoLine);
                updateWeaponArmor(WEAPON_POS,MainFrame.inventory);
            }
        });
    }

    private void updateFormData(Inventory inventory){
        for(int count = 0; count < types.length; count++){
            areas[count].setText("");
           if(!types[count].equals("misc"))
               updateWeaponArmor(count,inventory);
           }
         updateMiscCurrency(inventory);  // For some reason currency is not displaying at first unless called a second time
    }

    public void updateMiscCurrency(Inventory inventory){
        int count = MISC_POS;
        int length = inventory.inv.get(types[count]).size();

        buttonPanels[MISC_POS].removeAll();  // Clears everything on button panels and text areas
        buttonPanels[CURRENCY_POS].removeAll();
        areas[MISC_POS].setText("");
        areas[CURRENCY_POS].setText("");

        for(int i = 0; i < length; i++) {
            Item item = inventory.inv.get(types[MISC_POS]).get(i);

            if (item.isCurrency())
                count = CURRENCY_POS;
            else
                count = MISC_POS;

            JTextArea area = areas[count];

            area.append("\n" + item.getAmount() + " ");

            addPlusMinus(count, i, inventory);
            area.append(item.getName() + "\n");
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
        areas[count].setText("");

        for(int i = 0; i < length; i++) {
            Item item = inventory.inv.get(types[count]).get(i);
            areas[count].append("\n" + item.getName() + "\n");
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
        itemPanel.setBackground(invPanel.getBackground().brighter());

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
        int rigidSpace = 10;
        Item item = inventory.inv.get(types[count]).get(i);
        JButton delButton = new JButton("Delete");
        JButton detailButton = new JButton("Details");
        final JButton equipButton = new JButton("Equip");
        if(item.isEquipped())
            equipButton.setText("Unequip");

        /*Dimension buttonDim = new Dimension(15,20);
        delButton.setMaximumSize(buttonDim);
        detailButton.setMaximumSize(buttonDim);
        equipButton.setMaximumSize(buttonDim);*/

        Font font = new Font("STLiti",Font.BOLD,14);
        delButton.setFont(font);
        detailButton.setFont(font);
        equipButton.setFont(font);


        JPanel delPanel = buttonPanels[count];
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel,BoxLayout.LINE_AXIS));
        itemPanel.add(equipButton);
        itemPanel.add(Box.createRigidArea(new Dimension(rigidSpace,5)));
        itemPanel.add(detailButton);
        itemPanel.add(Box.createRigidArea(new Dimension(rigidSpace,5)));
        itemPanel.add(delButton);
        itemPanel.setMaximumSize(itemPanelDim);
        itemPanel.setBackground(invPanel.getBackground().brighter());
        delPanel.add(itemPanel);

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventory.inv.get(types[count]).remove(i);
                updateFormData(MainFrame.inventory);
            }
        });

        equipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(item.isWeapon()) {
                    if (item.isEquipped()) {  // Button at this point will say 'Unequip'
                        // If the item is equipped, change button back to saying 'Equip'
                        int x = 1;
                        item.setEquipped(false);
                        equipButton.setText("Equip");
                    } else {  // Equips the weapon
                        item.setEquipped(true);
                        equipButton.setText("Unequip");
                    }
                }
                else{
                    if (item.isEquipped()) {  // Button at this point will say 'Unequip'
                        // If the item is equipped, change button back to saying 'Equip' and subtract ac bonus from ac
                        parentCharForm.setAC(parentCharForm.getAC() - item.getBonus());
                        item.setEquipped(false);
                        equipButton.setText("Equip");
                    } else {  // Equips the item and adds the bonus AC
                        parentCharForm.setAC(parentCharForm.getAC() + item.getBonus());
                        item.setEquipped(true);
                        equipButton.setText("Unequip");
                    }
                }

            }
        });

        detailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame detailFrame = new JFrame("Details");
                detailFrame.setContentPane(new detailForm(item).detailPanel);
                detailFrame.setPreferredSize(new Dimension(450, 300));
                detailFrame.pack();
                detailFrame.setVisible(true);
                JFrame frame = parentCharForm.getFrame();
                detailFrame.setLocation(frame.getLocation().x+frame.getWidth(),frame.getLocation().y);
            }
        });
    }
}